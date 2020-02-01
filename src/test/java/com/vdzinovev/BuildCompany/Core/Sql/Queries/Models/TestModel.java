package com.vdzinovev.BuildCompany.Core.Sql.Queries.Models;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;

/**
 * Модель для тестов.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class TestModel {

    @SqlField(value = "TestIDField",
            id = true)
    private int id;

    @SqlField(value = "TestStringField")
    private String testString;

    @SqlField(value = "TestDateField",
            date = true)
    private String testDate;

    @SqlField(value = "TestIntField")
    private int testInt;

    @SqlField(value = "TestFloatField")
    private float testFloat;

    @SqlQuery(table = "TestTable",
            type = QueryType.setter)
    public String set() throws Exception {
        DbLib.getResult(this);
        return null;
    }

    @SqlQuery(table = "TestTable",
            type = QueryType.getter)
    public String get() throws Exception {
        DbLib.getResult(this);
        return null;
    }

    @SqlQuery(table = "TestTable",
            type = QueryType.updater)
    public String update() throws Exception {
        DbLib.getResult(this);
        return null;
    }

    /**
     * Конструктор класса.
     * @param id Тестовое ID поле
     * @param testString Тестовая строка.
     * @param testDate Тестовая дата.
     * @param testInt Тестовое число
     * @param testFloat Тестовое число
     * <p>с плавующей точкой</>
     */
    public TestModel(final int id,
                     final String testString,
                     final String testDate,
                     final int testInt,
                     final float testFloat) {
        this.id = id;
        this.testString = testString;
        this.testDate = testDate;
        this.testInt = testInt;
        this.testFloat = testFloat;
    }
}
