package com.vdzinovev.BuildCompany.Core.Tools;

import org.json.JSONObject;

/**
 * Класс обработки исключений.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public final class ExceptionEvent {

    /**
     * Скрытый конструктор класса.
     */
    private ExceptionEvent() {

    }

    /**
     * Метод ответа клиенту, при возникновении исключения
     * @param ex Исключение
     * @return Ответ
     */
    public static JSONObject throwNewException(Exception ex) {
        JSONObject result;

        System.out.println("******ОШИБКА******");
        System.out.println(ex.getMessage());
        ex.printStackTrace();
        System.out.println("******КОНЕЦ ОШИБКИ******");

        String clazz = ex.getClass().getCanonicalName();
        switch (clazz) {
            case "java.lang.ArithmeticException":
                result = throwArithmeticException();
            break;
            case "java.lang.IndexOutOfBoundsException":
                result = throwIndexBoundException();
            break;
            case "java.lang.IllegalArgumentException":
                result = throwIllegalArgumentException();
            break;
            case "java.lang.NullPointerException":
                result = throwNullPointerException();
            break;
            case "java.lang.NumberFormatException":
                result = throwNumberFormatException();
            break;
            case "java.lang.ArrayIndexOutOfBoundsException":
                result = throwArrayIndexOutOfBoundException();
            break;
            case "java.lang.ClassNotFoundException":
                result = throwClassNotFoundException();
            break;
            case "java.io.IOException":
                result = throwIOException();
            break;
            case "java.io.FileNotFoundException":
                result = throwFileNotFoundException();
            break;
            case "java.io.InterruptedIOException":
                result = throwInterruptedIOException();
            break;
            case "java.lang.InterruptedException":
                result = throwInterruptedException();
            break;
            case "java.lang.NoSuchFieldException":
                result = throwNoSuchFieldException();
            break;
            case "java.lang.NoSuchMethodException ":
                result = throwNoSuchMethodException();
            break;
            case "java.sql.SQLTimeoutException":
                result = throwSQLTimeoutException();
            break;
            case "java.sql.SQLException":
                result = throwSQLException();
            break;
            default:
                result = buildJson("Необработанное исключение");
            break;
        }

        return result;
    }

    /**
     * Строит ответ в Json.
     * @param message Сообщение
     * @return Ответ клиенту
     */
    private static JSONObject buildJson(String message) {
        JSONObject result = new JSONObject();
        result.put("state", false);
        result.put("items", 0);
        result.put("messeage", message);
        return result;
    }

    /**
     * Арифметическое исключение.
     * @return Ошибка арифметических вычислений
     */
    private static JSONObject throwArithmeticException() {
        return buildJson("Ошибка в вычислениях. "
                + "Возможно произошло "
                + "деление на 0");
    }

    /**
     * Ошибка массива данных.
     * @return Ошибка массива
     */
    private static JSONObject throwIndexBoundException() {
        return buildJson("Ошибка в массиве данных. "
                + "Индекс вышел за пределы массива.");
    }

    /**
     * Ошибка неверного аргумента функции.
     * @return Ошибка аргумента
     */
    private static JSONObject throwIllegalArgumentException() {
        return buildJson("Ошибка Аргумента метода. "
                + "Проверьте вызов метода.");
    }


    /**
     * Ошибка использования пустой ссылки.
     * @return Ошибка ссылки
     */
    private static JSONObject throwNullPointerException() {
        return buildJson("Ошибка ссылки переменной. "
                + "Возможно переменная не найдена или неиницилизированна.");
    }

    /**
     * Ошибка преобразования числа.
     * @return Ошибка преобразования числа
     */
    private static JSONObject throwNumberFormatException() {
        return buildJson("Ошибка преобразования числа. "
                + "Возможно введено неверное число.");
    }

    /**
     * Ошибка индекса в массиве.
     * @return Ошибка индекса
     */
    private static JSONObject throwArrayIndexOutOfBoundException() {
        return buildJson("Ошибка обращения к массиву данных. "
                + "Произошло обращение к несуществующему индексу.");
    }

    /**
     * Ошибка поиска класса(рефлексия).
     * @return Ошибка класса
     */
    private static JSONObject throwClassNotFoundException() {
        return buildJson("Ошибка поиска класса. "
                + "При работе с рефлексией не найден класс.");
    }

    /**
     * Ошибка Ввода - вывода.
     * @return Ошибка ввода - вывода
     */
    private static JSONObject throwIOException() {
        return buildJson("Ошибка ввода - вывода. "
                + "Проверьте права доступа.");
    }

    /**
     * Ошибка поиска файла.
     * @return Ошибка поиска или открытия файла.
     */
    private static JSONObject throwFileNotFoundException() {
        return buildJson("Ошибка файла. "
                + "Файл не найден или не открывается.");
    }

    /**
     * Ошибка потока(спит, занят, обрабатывает и потом прерывается ).
     * @return Ошибка поиска или открытия файла.
     */
    private static JSONObject throwInterruptedException() {
        return buildJson("Ошибка потока выполнения. "
                + "Поток выполнения прерван");
    }

    /**
     * Ошибка потока(спит, занят, обрабатывает и потом прерывается ).
     * @return Ошибка поиска или открытия файла.
     */
    private static JSONObject throwInterruptedIOException() {
        return buildJson("Ошибка потока чтения файла. "
                + "При чтении файла возникла ошибка");
    }

    /**
     * Ошибка поиска полей в классе(рефлексия).
     * @return Ошибка поиска полей.
     */
    private static JSONObject throwNoSuchFieldException() {
        return buildJson("Ошибка поиска поля. "
                + "При работе с рефлексией искомое поле не найденно в классе");
    }

    /**
     * Ошибка поиска методов в классе(рефлексия).
     * @return Ошибка поиска методов.
     */
    private static JSONObject throwNoSuchMethodException() {
        return buildJson("Ошибка поиска метода. "
                + "При работе с рефлексией искомый метод не найден в классе");
    }

    /**
     * Ошибка работы с SQL.
     * @return Ошибка SQL.
     */
    private static JSONObject throwSQLException() {
        return buildJson("Ошибка SQL. "
                + "При c sql произошла ошибка");
    }

    /**
     * Ошибка ожидания ответа от БД.
     * @return Ошибка ожидания SQL.
     */
    private static JSONObject throwSQLTimeoutException() {
        return buildJson("Ошибка SQL. "
                + "Привышен лимит ожидания запроса к БД");
    }
}
