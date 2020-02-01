package com.vdzinovev.BuildCompany;

import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Стартовый класс.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@SpringBootApplication
public class App {

    /**
     * Метод запуска программы.
     * @param args Аргументы
     */
    public static void main(final String[] args) throws Exception {
        DbLib.connectionOpen();
            SpringApplication.run(App.class, args);
    }
}
