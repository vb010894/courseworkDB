package com.vdzinovev.BuildCompany.Core.Sql.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Хранит параметры процедуры.
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlFunctionParams {

    /**
     * Имя функции.
     * @return Имя
     */
    String functionName();
}
