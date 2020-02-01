package com.vdzinovev.BuildCompany.Core.Sql.Annotation;

import com.vdzinovev.BuildCompany.Core.Sql.Enums.SqlOperator;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.SqlSeparator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация SQL фильтров.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlFilter {

    /**
     * Имя столбца для фильтрации.
     * @return Имя столбца
     */
    String value();

    /**
     * Логический оператор.
     * <p>По умолчанию пусто</>
     * @return Логический оператор
     */
    SqlSeparator separator() default SqlSeparator.empty;

    /**
     * Оператор.
     * <p>По умолчанию равно</>
     * @return Оператор
     */
    SqlOperator operator() default SqlOperator.equal;
}
