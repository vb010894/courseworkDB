package com.vdzinovev.BuildCompany.Core.Sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;

/**
 * Проверка SQL.
 */
public class TestSql {

    /**
     * URL БД.
     */
    private static final String URL = "jdbc:mysql://"
            + "localhost/building?"
            + "useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";

    /**
     * Пользователь БД.
     */
    private static final String USER = "siteUser";

    /**
     * Пароль пользователя БД.
     */
    private static final String PASSWORD = "1234098sam";

    /**
     * Открытие соединения.
     */
    @BeforeAll
    public static void openConnection() {
        try {
            DbLib.connectionOpen();
        } catch (SQLException sql) {
            sql.printStackTrace();
            Assertions.fail("Ошибка SQL\r\n"
                    + "Код ошибки: " + sql.getErrorCode()
                    + "\r\nСообщение: " + sql.getMessage());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Ошибка \r\n"
                    + "Сообщение: " + ex.getMessage());
        }
    }

    @AfterAll
    public static void closeConnection() {
        try {
            DbLib.connectionClose();
        } catch (SQLException sql) {
            sql.printStackTrace();
            Assertions.fail("Ошибка SQL\r\n"
                    + "Код ошибки: " + sql.getErrorCode()
                    + "\r\nСообщение: " + sql.getMessage());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Ошибка \r\n"
                    + "Сообщение: " + ex.getMessage());
        }
    }

    /**
     * Проверка соединения.
     */
    @Test
    @DisplayName("Check Connection")
    public void checkConnection() {
        try {
            Assertions.assertTrue(DbLib.checkConnectionStatus());
        } catch (SQLException sql) {
            sql.printStackTrace();
            Assertions.fail("Ошибка SQL\r\n"
                    + "Код ошибки: " + sql.getErrorCode()
                    + "\r\nСообщение: " + sql.getMessage());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Ошибка \r\n"
                    + "Сообщение: " + ex.getMessage());
        }
    }

}
