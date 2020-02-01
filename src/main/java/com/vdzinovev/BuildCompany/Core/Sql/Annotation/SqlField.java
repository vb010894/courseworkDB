package com.vdzinovev.BuildCompany.Core.Sql.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация SQL поля.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlField {

    /**
     * Имя поля в БД.
     * @return Имя поля
     */
    String value();

    /**
     * Помечает ID.
     * @return ID
     */
    boolean id() default false;

    /**
     * Помечает строку как дату.
     * @return Флаг
     */
    boolean date() default false;

    /**
     * Формат даты SQL.
     * @return Формат
     */
    String dateFormat() default "DD.MM.YYYY";
}
