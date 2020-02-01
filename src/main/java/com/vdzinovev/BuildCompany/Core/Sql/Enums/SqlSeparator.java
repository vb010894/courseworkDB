package com.vdzinovev.BuildCompany.Core.Sql.Enums;

/**
 * Оператор соединения фильтров запроса.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public enum SqlSeparator {

    /**
     * Оператор ИЛИ.
     */
    or("OR"),

    /**
     * Оператор И.
     */
    and("AND"),

    /**
     * Без оператора.
     */
    empty("");

    /**
     * Хранит текущее значение оператора.
     */
    private String operator;

    /**
     * Конструктор пречисления.
     * @param operator Оператор
     */
    SqlSeparator(final String operator) {
        this.operator = operator;
    }

    /**
     * Возвращает значение оператора.
     * @return Значение оператора
     */
    public String value() {
        return operator;
    }
}
