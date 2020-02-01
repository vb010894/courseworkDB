package com.vdzinovev.BuildCompany.Models.Departament;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlAlias;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlFilter;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import org.json.JSONObject;

public class DepartmentModel {

    /**
     * Поле ID.
     */
    @SqlField(value = "id",
            id = true)
    private int id;

    /**
     * Поле NAME.
     */
    @SqlField("name")
    private String name;

    /**
     * Поле ADRESS.
     */
    @SqlField("adress")
    private String address;

    /**
     * Вторичный ключ организация.
     */
    @SqlField("org")
    private int org;

    @SqlAlias(value = "name",
            table = "organization",
            nickname = "orgName",
            fieldAlias = "departament.org")
    private String orgName;

    @SqlFilter("departament.name")
    private String nameFilter = "";

    /**
     * Задает фильтарцию по имени.
     * @param nameFilter
     */
    public void setNameFilter(final String nameFilter) {
        this.nameFilter = nameFilter;
    }

    /**
     * Возвращает значение ID.
     * @return ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Получает значение ID.
     * @param id ID.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Возвращает значение NAME.
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * Получает значение NAME.
     * @param name NAME
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Возвращает ADRESS.
     * @return ADRESS.
     */
    public String getAddress() {
        return address;
    }

    /**
     *Получает ADRESS.
     * @param address ADRESS
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * Возвращает ORG.
     * @return ORG
     */
    public int getOrg() {
        return org;
    }

    /**
     *  Получает ADRESS.
     * @param org ADRESS
     */
    public void setOrg(final int org) {
        this.org = org;
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "departament",
            type = QueryType.setter)
    public JSONObject set() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Получает записи из таблицы.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "departament",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "departament",
            type = QueryType.updater)
    public JSONObject update() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Удаляет записи в таблицы ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "departament",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
