package root.core;


import org.json.JSONArray;
import org.json.JSONObject;
import root.core.Annotation.SqlField;
import root.core.Annotation.SqlQuery;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;

public class SqlWork
{
    private static final String connectionUrl = "jdbc:mysql://localhost/building?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "siteUser";
    private static final String password = "1234098sam";

    private static Connection con;
    private static Statement stmt;

    public void updateQuery(String Query) throws Exception
    {
        stmt.executeUpdate(Query);
    }

    public ResultSet getResult(String Query) throws Exception
    {

        return stmt.executeQuery(Query);
    }

    public void openConnection()
    {
        try
        {
            this.con = DriverManager.getConnection(connectionUrl, user, password);
            this.stmt  = con.createStatement();
        }
        catch (SQLException sql)
        {
            System.out.println();
            System.out.println("------ОШИБКА------");
            System.out.println("Сообщение - " + sql);
            System.out.println("StackTrace - " + sql.getStackTrace());
        }

    }

    public static void ConnectionOpen() throws Exception
    {
        con = DriverManager.getConnection(connectionUrl, user, password);
        stmt  = con.createStatement();
    }

    public static void ConnectionClose() throws Exception
    {
        stmt.close();
        con.close();
    }

    public void closeConnection()
    {
        try
        {
            this.con.close();
        }
        catch (SQLException sql)
        {
            System.out.println();
            System.out.println("------ОШИБКА------");
            System.out.println("Сообщение - " + sql);
            System.out.println("StackTrace - " + sql.getStackTrace());
        }

    }

    public static String result(Object object, String id )throws Exception
    {
        String result;
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String TargetClass = stack[2].getClassName();
        String callMethod = stack[2].getMethodName();
        Class clazz = Class.forName(TargetClass);
        Method[] met = clazz.getMethods();
        Method TargetMethod = (id.length() == 0) ? clazz.getMethod(callMethod) : clazz.getMethod(callMethod, String.class);
        System.out.println("Test");

        switch (TargetMethod.getAnnotation(SqlQuery.class).type())
        {
            case getter:
                result = get(clazz, TargetMethod, id);
            break;
            case updater:
                result = update(object, TargetMethod, id);
            break;
            case deleter:
                result = delete(TargetMethod, id);
            break;
            case setter:
                result = set(TargetMethod, object);
            break;
            default: result = "getter"; break;
        }

        return result;
    }

    private static String delete(Method method, String id) throws Exception
    {
        String table = method.getAnnotation(SqlQuery.class).table();
        String query = "delete from " + table + " where id = " + id;
        stmt.executeUpdate(query);
        return "{count:1, state:true, items:[]}";
    }

    private static String update(Object object, Method method, String id) throws Exception
    {
        String table = method.getAnnotation(SqlQuery.class).table();
        String values = "";
        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(SqlField.class) != null && field.getName() != "id") {
                String key = field.getAnnotation(SqlField.class).value();
                switch (field.getType().toString()) {
                    case "java.lang.String":
                        values += table + "." + key + " = " + "'" + field.get(object) + "', ";
                    break;
                    case "int":
                        values += table + "." + key + " = "  + field.getInt(object) + ", ";
                    break;
                    case "float":
                        values += table + "." + key + " = " + field.getFloat(object) + ", ";
                    break;
                    default:
                        values += table + "." + key + " = " + "'" + field.get(object) + "', ";
                    break;
                }
            }
        }

        String query = "update " + table + " set " + values.substring(0, values.length() - 2) + " where id = " + id;
        stmt.executeUpdate(query);
        return "{count:1, state:true, items:[]}";
    }

    private static String get(Class clazz, Method method, String id) throws Exception
    {
        JSONObject result;
        int count = 0;
        JSONArray items = new JSONArray();
        String table = method.getAnnotation(SqlQuery.class).table();
        String query = (id.length() == 0) ? "select * from " + table : "select * from " + table + " where id = " + id;
        ResultSet resultSet = stmt.executeQuery(query);
        Field[] fields = clazz.getDeclaredFields();

        while (resultSet.next())
        {
            count = resultSet.getRow();
            result = new JSONObject();
            for (Field field : fields) {
                if (field.getAnnotation(SqlField.class) != null ) {
                    String key = field.getAnnotation(SqlField.class).value();
                    switch (field.getType().toString()) {
                        case "java.lang.String":

                            result.put(key, resultSet.getString(key));
                            break;
                        case "int":
                            result.put(key, resultSet.getInt(key));
                            break;
                        case "float":
                            result.put(key, resultSet.getFloat(key));
                            break;
                        default:
                            result.put(key, resultSet.getString(key));
                            break;
                    }
                }
            }
            items.put(result);
        }

        result = new JSONObject();

        if(count == 0)
        {
            result.put("state", false);
            result.put("count", 0);
            result.put("items", "[]");
        }
        else
        {
            result.put("state", true);
            result.put("count", count);
            result.put("items", items);
        }

        return result.toString();
    }

    private static String set(Method method, Object object) throws Exception
    {
        String table = method.getAnnotation(SqlQuery.class).table();
        String values = "";
        String columns = "";

        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(SqlField.class) != null && field.getName() != "id") {
                String key = field.getAnnotation(SqlField.class).value();
                columns += key + ", ";
                switch (field.getType().toString()) {
                    case "java.lang.String":
                        values += "'" + field.get(object) + "', ";
                    break;
                    case "int":
                        values += field.getInt(object) + ", ";
                    break;
                    case "float":
                        values += field.getFloat(object) + ", ";
                    break;
                    default:
                        values += "'" + field.get(object) + "', ";
                    break;
                }
            }
        }
        String query = "insert into " + table + "(" + columns.substring(0, columns.length() - 2) + ") values(" + values.substring(0, values.length() - 2) + ")";
        stmt.executeUpdate(query);
        return "{count:1, state:true, items:[]}";

    }
}

