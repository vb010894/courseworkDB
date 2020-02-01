package com.vdzinovev.BuildCompany.Models.Organization;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import org.json.JSONObject;

/**
 * Модель организации.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class OrganizationModel {

    /**
     * Поле ID.
     */
    @SqlField(value = "id",
            id = true)
    private int id;

    /**
     * Поле name.
     */
    @SqlField("name")
    private String name;

    /**
     * Поле adress.
     */
    @SqlField("adress")
    private String address;

    /**
     * Поле inn.
     */
    @SqlField("inn")
    private int inn;

    /**
     * Возвращает значение поля ID.
     * @return значение поля ID
     */
    public int getId() {
        return id;
    }

    /**
     * Получает значение ID.
     * @param id Значение поля
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает значение поля name.
     * @return Значение поля
     */
    public String getName() {
        return name;
    }

    /**
     * Получает значение поля NAME.
     * @param name Значение поля
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает значение поля ADRESS.
     * @return Значение поля
     */
    public String getAddress() {
        return address;
    }

    /**
     * Получает значение поля ADRESS.
     * @param address Значение поля
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Возвращает значение поля INN.
     * @return Значение поля
     */
    public int getInn() {
        return inn;
    }

    /**
     * Получает значение поля INN.
     * @param inn Значение поля
     */
    public void setInn(int inn) {
        this.inn = inn;
    }

    /**
     * Возвращает значение поля KPP.
     * @return Значение поля
     */
    public int getKpp() {
        return kpp;
    }

    /**
     * Получает значение KPP.
     * @param kpp Значение поля
     */
    public void setKpp(int kpp) {
        this.kpp = kpp;
    }

    /**
     * Поле kpp.
     */
    @SqlField("kpp")
    private int kpp;

    /**
     * Конструктор для Getter.
     */
    public OrganizationModel() {
        // do nothing
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "organization",
              type = QueryType.setter)
    public JSONObject set() throws Exception {
       return DbLib.getResult(this);
    }

    /**
     * Получает записи из таблицы.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "organization",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "organization",
            type = QueryType.updater)
    public JSONObject update() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Удаляет записи в таблицы ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "organization",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
