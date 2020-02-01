package com.vdzinovev.BuildCompany.Core.Sql.Enums;

/**
 * Тип запроса.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public enum QueryType {

    /**
     * Получает данные.
     */
    getter(),

    /**
     * Записывает новую строку.
     */
    setter,

    /**
     * Обновляет данные.
     */
    updater,

    /**
     * Удаляет данные.
     */
    deleter,

    /**
     * Получает количество записей.
     */
    counter,

    /**
     * Функция.
     */
    function,

    /**
     * Процедура.
     */
    procedure;
}

