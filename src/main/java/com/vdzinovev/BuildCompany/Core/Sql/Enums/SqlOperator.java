package com.vdzinovev.BuildCompany.Core.Sql.Enums;

/**
 * Оператор SQL.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public enum SqlOperator {

    /**
     * Равно.
     */
    equal(" = "),

    /**
     * Не равно.
     */
    notEqual(" <> "),

    /**
     * Подобно.
     */
    like(" LIKE "),

    /**
     * Не подобно.
     */
    notLike(" NOT LIKE "),

    /**
     * Меньше.
     */
    less(" < "),

    /**
     * Больше.
     */
    more(" > "),

    /**
     * Меньше или равно.
     */
    lessOrEqual(" <= "),

    /**
     * Больше или равно.
     */
    moreOrEqual(" >= ");

    private String operator;

    /**
     * Конструктор перечисления.
     * @param operator Оператор
     */
    SqlOperator(final String operator) {
        this.operator = operator;
    }

    /**
     * Возвращает значение перечисления.
     * @return Значение перечисления
     */
    public String value() {
        return this.operator;
    }
}
