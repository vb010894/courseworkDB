package com.vdzinovev.BuildCompany.Core.Sql.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Определяет связанные поля.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlAlias {

    /**
     * Наименование столбца в БД.
     * @return Имя столбца
     */
    String value();

    /**
     * Запрашиваемая таблица.
     * @return Имя таблицы
     */
    String table();

    /**
     * Псевдоним столбца.
     * @return Псевдоним
     */
    String nickname();

    /**
     * Столбец для связи.
     * @return Имя столбца
     */
    String fieldAlias() default "";
}
