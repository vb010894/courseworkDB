package com.vdzinovev.BuildCompany.Core.Sql;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.*;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.SqlSeparator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Класс для работы с SQL.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@Scope("prototype")
public class DbLib {

    /**
     * URL БД.
     */
    private static final String URL = "jdbc:mysql://"
            + "localhost/building?"
            + "useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";

    /**
     * Пользователь БД.
     */
    private static final String USER = "siteUser";

    /**
     * Пароль пользователя БД.
     */
    private static final String PASSWORD = "1234098sam";

    /**
     * URL для подключения к др. БД.
     */
    private String otherURL;

    /**
     * Пользователь для др. БД.
     */
    private String otherUser;

    /**
     * Пароль для др. БД.
     */
    private String otherPassword;

    /**
     * Сессия в БД.
     */
    private static Connection connection = null;

    /**
     * Оператор БД.
     */
    private static Statement statement = null;

    /**
     * Поля класса.
     */
    private static LinkedList<Field> classFields;

    /**
     * Хранит значения полей в модели.
     */
    private static JSONArray fieldsValues;

    /**
     * Для проверки соединения.
     */
    private static boolean connectionStatus = false;

    /**
     * Конструктор для подключения к
     * другим БД.
     * @param url URL БД.
     * @param user Пользователь БД.
     * @param password Пароль БД.
     */
    public DbLib(final String url,
                 final String user,
                 final String password) {
        this.otherUser = user;
        this.otherURL = url;
        this.otherPassword = password;
    }

    @Override
    public final String toString() {
        return "Константы: \r\n"
                + "URL: " + URL + "\r\n"
                + "USER: " + USER + "\r\n"
                + "PASSWORD " + PASSWORD + "\r\n"
                + "Поля: \r\n"
                + "url: " + this.otherURL + "\r\n"
                + "user: " + this.otherUser + "\r\n"
                + "password: " + this.otherPassword;

    }

    /**
     * Открывает соединения с БД.
     * @throws Exception Пропуск исключений
     */
    public static void connectionOpen() throws Exception {
        connection = DriverManager.getConnection(URL,
                                                 USER,
                                                 PASSWORD);
        statement = connection.createStatement();
    }

    /**
     * Открывает подключение к другим БД.
     * @throws Exception Пропуск исключений
     */
    public final void openConnection()
            throws Exception {
        connection = DriverManager.getConnection(this.otherURL,
                                                            this.otherUser,
                                                            this.otherPassword);
        statement = connection.createStatement();
    }

    /**
     * Проверка статуса Сессии.
     * @return Статус
     * @throws Exception Пропуск исключений
     */
    public static boolean checkConnectionStatus()
                                    throws Exception {
        connectionStatus = !connection.isClosed();
        return !connection.isClosed();
    }

    /**
     * Закрывает соединение с БД.
     * @throws Exception Пропуск исключений
     */
    public static void connectionClose()
                        throws Exception {
        statement.close();
        connection.close();
    }

    /**
     * Получает результаты запросов.
     * @param object Экземпляр класса модели
     * @return Результат запроса
     * @throws Exception Пропуск исключений
     */
    public static JSONObject getResult(final Object object)
                                throws Exception {

        if(connection == null) {
            connectionOpen();
        }
        JSONObject result = new JSONObject();
        StackTraceElement[] stack
                = Thread.currentThread().getStackTrace();
        Method targetMethod = object.getClass()
                                .getMethod(stack[2]
                                .getMethodName());
        classFields = new LinkedList<>();
        int id = getIdField(object);
        if (targetMethod.getAnnotation(SqlQuery.class) != null) {
            SqlQuery annotation = targetMethod
                                    .getAnnotation(SqlQuery.class);
            String table = annotation.table();

            switch (annotation.type()) {
                case setter:
                    fieldsValues = new JSONArray();
                    addFieldsValues(object, table);
                    result = set(table);
                    break;
                case getter:
                   result = get(object,
                            annotation.table());
                break;
                case updater:
                    fieldsValues = new JSONArray();
                    addFieldsValues(object, table);
                    result = update(annotation.table(), id);
                break;
                case counter:
                    result = count(object, table);
                break;
                case deleter:
                    result = deleter(table, id);
                break;
                case procedure:
                    result = callProcedure(object, table);
                break;
            }
        }
        return result;
    }

    /**
     * Выполняет хранимую процедуру.
     * @param object Экземпляр модели
     * @param functionName Наименование функции
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    private static JSONObject callProcedure(final Object object,
                                            final String functionName)
            throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        String params = "";
        for (Field field: fields) {
            if(field
                    .getAnnotation(SqlFunctionParams.class) != null
                    && field
                    .getAnnotation(SqlFunctionParams.class)
                    .functionName()
                    .equals(functionName)) {
                field.setAccessible(true);
                params = field.get(object).toString();
            }
        }

        String query = "CALL " + functionName + "(" + params + ")";
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);
        JSONObject response = new JSONObject();
        response.put("count", result);

        if(result != 0 ) {
            response.put("state", true);
            return response;
        } else {
            response.put("state", false);
            response.put("message", "Нет записей для отображения");
        }

        return response;
    }

    /**
     * Получает зависимые поля.
     * @param object Экземпляр модели
     * @return Связанные поля
     */
    private static String getAlias(final Object object,
                                   final String table) {
        StringBuilder tables = new StringBuilder();
        StringBuilder fields = new StringBuilder();
        tables.append(table);

        for (Field field: object
                .getClass()
                .getDeclaredFields()) {
            if(field.getAnnotation(SqlAlias.class) != null) {
                SqlAlias annotation = field.getAnnotation(SqlAlias.class);
                if(!tables.toString().contains(annotation.table())) {
                    tables.append(" JOIN ")
                            .append(annotation.table())
                            .append(" ON ")
                            .append(annotation.table())
                            .append(".id")
                            .append(" = ")
                            .append(annotation.fieldAlias());

                }

                String fieldName =
                        annotation.table() + "." + annotation.value();
                if(!fields.toString().contains(fieldName)) {
                    fields.append(fieldName)
                            .append(" AS ")
                            .append(annotation.nickname())
                            .append(", ");
                }
            }

            if(field.getAnnotation(SqlField.class) != null) {
                SqlField sqlField = field.getAnnotation(SqlField.class);
                fields.append(table)
                        .append(".")
                        .append(sqlField.value())
                        .append(", ");
            }

        }

        fields.setLength(fields.length() - 2);
        return fields.toString()
              + " FROM "
              + tables.toString();
    }

    /**
     * Получает значения поля ID.
     * @param object Экземпляр класса
     * @return Значение ID,
     * <p></>если вернет -1,</p>
     * <p>то значение не найдено</>
     * @throws Exception Пропуск исключений
     */
    private static int getIdField(final Object object)
                                        throws Exception {
        int result = -1;
        Field[] fields = object.getClass()
                        .getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            if(field.getAnnotation(SqlField.class) != null) {
                if(field
                        .getAnnotation(SqlField.class)
                        .id()) {
                    result = field.getInt(object);
                } else {
                    classFields.add(field);
                }
            }
        }
        return result;
    }

    /**
     * Получает значение полей класса.
     * @param object Экземпляр класса модели
     * @param table Таблица
     * @throws Exception Пропуск исключений
     */
    private static void addFieldsValues(final Object object,
                                        final String table)
                                        throws Exception {
        for (Field field: classFields) {
            JSONObject temp = new JSONObject();
            SqlField annotation = field
                    .getAnnotation(SqlField.class);
            String name = annotation
                         .value();
            boolean date = annotation
                          .date();
            String format = annotation.dateFormat();
            switch (field.getType().toString()) {
                default:
                    if(!date) {
                        temp.put("name", name);
                        temp.put("type", "String");
                        temp.put("value", field.get(object));
                    } else {
                        temp.put("name", name);
                        temp.put("format", format);
                        temp.put("type", "Date");
                        temp.put("value", field.get(object));
                    }

                break;
                case "int":
                    temp.put("name", name);
                    temp.put("type", "Int");
                    temp.put("value", field.getInt(object));
                    break;
                case "float":
                    temp.put("name", name);
                    temp.put("type", "Float");
                    temp.put("value", field.getFloat(object));
                break;
            }
            fieldsValues.put(temp);
        }
    }

    /**
     * Заносит данные в таблицу.
     * @param table таблица для записи
     * @return Количество затронутых строк
     * @throws Exception пропуск исключений
     */
    private static JSONObject set(final String table)
            throws Exception {
        StringBuilder builder = new StringBuilder();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        builder.append("INSERT INTO ")
                .append(table)
                .append(" (");
        for (Object json: fieldsValues) {
            JSONObject temp = (JSONObject) json;
            columns.append(table)
                    .append(".")
                    .append(temp.getString("name"))
                    .append(", ");
            switch (temp.getString("type")) {
                case "String":
                    values.append("'")
                            .append(temp
                            .getString("value"))
                            .append("', ");
                break;
                case "Date":
                    values.append("TO_DATE('")
                            .append(temp.getString("value"))
                            .append("', '")
                            .append(temp.getString("format"))
                            .append("'), ");
                break;
                default:
                    values.append(temp.get("value"))
                          .append(", ");
            }
        }

        values.setLength(values.length() - 2);
        columns.setLength(columns.length() - 2);

        builder.append(columns.toString())
                .append(" ) VALUES( ")
                .append(values.toString())
                .append(")");

        System.out.println(builder.toString());
       int rowCount = statement
               .executeUpdate(builder.toString());

       JSONObject result = new JSONObject();

       if(rowCount > 0) {
           result.put("state", true);
           result.put("rowCount", rowCount);
       } else {
           result.put("state", false);
           result.put("rowCount", 0);
       }

       return result;
    }

    /**
     * Получает фильтры класса.
     * @param object Экземпляр модели
     * @return Строку фильтарции SQL
     * @throws Exception Пропуск исключений
     */
    private static String getFilters(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        String filters = "";
        Queue<String> result = new PriorityQueue<>();
        for (Field field: fields) {
            if(field.getAnnotation(SqlFilter.class) != null) {
                field.setAccessible(true);
                SqlFilter annotation = field.getAnnotation(SqlFilter.class);
                String temp;
                switch (field.getType().toString()) {
                    case "int":
                        temp =  (field.getInt(object) == -11111)
                                ? ""
                                : annotation.value()
                                + " = "
                                + field.getInt(object)
                                + " "
                                + annotation.separator().value();
                    break;
                    case "float":
                        temp =  (field.getFloat(object) == -11111)
                                ? ""
                                : annotation.value()
                                + annotation.operator()
                                + field.getFloat(object)
                                + " "
                                + annotation.separator().value();
                    break;
                    default:
                        temp =  (field.get(object).equals("")
                                || field.get(object)
                                .toString()
                                .length() == 0)
                                ? ""
                                : annotation.value()
                                + " = '"
                                + field.get(object)
                                + "' "
                                + annotation.separator().value();
                    break;

                }

                if(annotation.separator().value()
                        .equals(SqlSeparator.empty.value())) {
                    result.add(temp);
                } else {
                    result.offer(temp);
                }
            }
        }


        for (String fl: result) {
            filters += fl + " ";
        }

        if(filters.length() > 2) {
            return " WHERE " + filters;
        } else {
            return "";
        }
    }

    /**
     * Выполняет запрос на получение данных.
     * @param object Экземпляр модели
     * @param table Таблица в БД
     * @return результат запроса
     * @throws Exception Пропуск исключений
     */
    private static JSONObject get(Object object,
                                  String table)
        throws Exception {
        String filters = getFilters(object);
        JSONObject result = new JSONObject();
        JSONArray items = new JSONArray();
        int count = -1;
        String alias = getAlias(object, table);

        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        if(alias.length() > 0) {
            query.append(alias);
        }
        query.append(" ")
                .append(filters)
                .append(" GROUP BY ")
                .append(table)
                .append(".")
                .append("id");

        System.out.println(query.toString());

        ResultSet resultSet = null;

        Statement tempStatement = connection.createStatement();

        resultSet = tempStatement.executeQuery(query.toString());
        while (resultSet.next()) {
            count = resultSet.getRow();
            JSONObject tmp = new JSONObject();
            for (Field field: object
                    .getClass()
                    .getDeclaredFields()) {
                field.setAccessible(true);
                SqlField annotation = field
                        .getAnnotation(SqlField.class);

                SqlAlias aliasField = field
                        .getAnnotation(SqlAlias.class);

                String name = "";
                if (annotation != null) {
                    name = annotation
                            .value();
                }

                if (aliasField != null) {
                    name = aliasField.nickname();
                }
                if (annotation != null || aliasField != null) {
                    switch (field.getType().toString()) {
                        default:
                            tmp.put(name, resultSet.getString(name));
                            break;
                        case "int":
                            tmp.put(name, resultSet.getInt(name));
                            break;
                        case "float":
                            tmp.put(name, resultSet.getFloat(name));
                            break;
                    }
                }
            }
                items.put(tmp);
            }

        if(count < 0) {
            result.put("state", false);
            result.put("countRows", 0);
            result.put("items", "[]");
        } else {
            result.put("state", true);
            result.put("countRows", count);
            result.put("items", items);
        }

        tempStatement.close();

        return result;
    }

    /**
     * Выполняет обновление записей в таблице.
     * @param table Таблица
     * @param id ID строки
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    private static JSONObject update(final String table,
                                     final int id)
                                        throws Exception {
        JSONObject result = new JSONObject();
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ")
                .append(table)
                .append(" SET ");

        for (Object json: fieldsValues) {
            JSONObject temp = (JSONObject) json;
            switch (temp.getString("type")) {
                case "String":
                    query.append(table)
                            .append(".")
                            .append(temp.getString("name"))
                            .append(" = ")
                            .append("\'")
                            .append(temp.getString("value"))
                            .append("\'");
                break;
                case "Date":
                    query.append(table)
                            .append(".")
                            .append(temp.getString("name"))
                            .append(" = ")
                            .append("TO_DATE('")
                            .append(temp.getString("value"))
                            .append("', '")
                            .append(temp.getString("format"))
                            .append("')");
                    break;
                default:
                    query.append(table)
                            .append(".")
                            .append(temp.getString("name"))
                            .append(" = ")
                            .append(temp.get("value"));
            }
            query.append(", ");
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ")
                .append(id);
        System.out.println(query.toString());
        int count = statement.executeUpdate(query.toString());
        if(count > 0) {
            result.put("state", true);
            result.put("count", count);
        } else {
            result.put("state", false);
            result.put("count", 0);
            result.put("message", "Не найдено записей");
        }

        return result;
    }

    /**
     * Возвращает количество записей.
     * @param table Таблица
     * @return Количество
     * @throws Exception Пропуск исключений
     */
    private static JSONObject count(final Object object,
                                    final String table)
    throws Exception {
        String filters = getFilters(object);
        String query = "SELECT COUNT(1) AS CNT FROM " + table + filters;
        ResultSet resultSet = statement.executeQuery(query);
        int count = -1;
        JSONObject result = new JSONObject();

        while (resultSet.next()) {
            count = resultSet.getInt("CNT");
        }

        resultSet.close();

        if(count < 1) {
            result.put("state", false);
            result.put("count", 0);
            result.put("message", "Не найдено записей");
        } else {
            result.put("state", true);
            result.put("count", count);
        }

        return result;
    }

    /**
     * Удаляет записи из таблицы.
     * @param table Таблица
     * @param id ID записи
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    private static JSONObject deleter(final String table,
                                      final int id)
    throws Exception {
        String query = "DELETE FROM " + table + " WHERE ID = " + id;
        JSONObject result = new JSONObject();
        int count = statement.executeUpdate(query);

        if(count < 1) {
            result.put("state", false);
            result.put("count", 0);
            result.put("message", "Не удалено ни одной записи");
        } else {
            result.put("state", true);
            result.put("count", count);
        }

        return result;
    }
}
