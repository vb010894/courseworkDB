package com.vdzinovev.BuildCompany.Core.Tools.ExceptionCallback;

import com.vdzinovev.BuildCompany.Core.Tools.ExceptionEvent;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Класс тестирования обработчика исключений.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class TestExceptionEvent {

    @Test
    public void testArithmeticException() {
        try {
            System.out.println(30/0);
        } catch (Exception ex) {
            JSONObject jsObject = ExceptionEvent
                    .throwNewException(ex);
                        Assertions.assertEquals(jsObject.getString("messeage"),
                                "Ошибка в вычислениях. Возможно произошло деление на 0");
                    System.out.println(jsObject);
        }
    }

    @Test
    public void testIndexOutOfBoundsException() {
        try {
            throw new IndexOutOfBoundsException("Индекс за границами");
        } catch (Exception ex) {
            JSONObject jsObject = ExceptionEvent
                    .throwNewException(ex);
            Assertions.assertEquals(jsObject.getString("messeage"),
                    "Ошибка в массиве данных. Индекс вышел за пределы массива.");
            System.out.println(jsObject);
        }
    }
}
