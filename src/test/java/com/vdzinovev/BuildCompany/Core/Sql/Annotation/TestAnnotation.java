package com.vdzinovev.BuildCompany.Core.Sql.Annotation;

import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * Тестирование аннотаций.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class TestAnnotation {

    /**
     * Тестовое поля.
     */
    @SqlField(value = "TestField", id = true)
    private String testField;

    /**
     * Тестовое поля для фильтрации.
     */
    @SqlFilter("TestField")
    private String testFieldFilter;

    /**
     * Тестовый метод.
     */
    @SqlQuery(table = "TestTable",
              type = QueryType.counter)
    private void testMethod() {
        // Do nothing
    }

    @Test
    @DisplayName("Тест SqlQuery")
    public void testSqlQuery() {
        try {
            SqlQuery annotation = this.getClass()
                    .getDeclaredMethod("testMethod")
                    .getAnnotation(SqlQuery.class);

            Assertions.assertEquals(annotation.table(),
                    "TestTable");

            Assertions.assertEquals(annotation.type(),
                                    QueryType.counter);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Ошибка: " + ex.getMessage());
        }
    }

    /**
     * Проверка SqlFilter.
     */
    @Test
    @DisplayName("Тест SqlFilter")
    public void testSqlFilter() {
        try {
            Field field = this.getClass()
                    .getDeclaredField("testFieldFilter");
            field.setAccessible(true);
            SqlFilter annotation = field.getAnnotation(SqlFilter.class);
            Assertions.assertEquals(annotation.value(),
                    "TestField");
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Ошибка: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Тест SqlField")
    public void testSqlField() {
        try {
            Field field = this.getClass()
                    .getDeclaredField("testField");
            field.setAccessible(true);
            SqlField annotation = field.getAnnotation(SqlField.class);
            Assertions.assertTrue(annotation.id());
            Assertions.assertEquals(annotation.value(),
                    "TestField");
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Ошибка: " + ex.getMessage());
        }
    }
}
