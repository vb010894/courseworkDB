package com.vdzinovev.BuildCompany.Core.Sql.Annotation;

import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для методов запросов.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlQuery {

    /**
     * Хранит табицу для запросов.
     * @return Имя таблицы
     */
    String table();

    /**
     * Хранит тип запроса.
     * @return Тип(по умолчанию getter)
     */
    QueryType type() default QueryType.getter;
}
