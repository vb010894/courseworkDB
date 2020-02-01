package com.vdzinovev.BuildCompany.Core.Sql.Queries;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import com.vdzinovev.BuildCompany.Core.Sql.Queries.Models.TestModel;
import org.junit.jupiter.api.Test;

/**
 * Класс тестирования SQL запросов.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class TestQuery {

    /**
     * Тестирование setter.
     */
    @Test
    public void testSetter() {
        try {
            TestModel model = new TestModel(1,
                                "string",
                                "01.08.1994",
                                         1,
                                        10/2);
            model.set();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testGetter() {
        try {
            TestModel model = new TestModel(1,
                    "string",
                    "01.08.1994",
                    1,
                    10/2);
            model.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testUpdater() {
        try {
            TestModel model = new TestModel(1,
                    "string",
                    "01.08.1994",
                    1,
                    10/2);
            model.update();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
