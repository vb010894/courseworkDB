package com.vdzinovev.BuildCompany.Models.Objects;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import org.json.JSONObject;

/**
 * Модель Объектов.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class ObjectsModel {

    @SqlField(value = "id",
            id = true)
    private int id;

    @SqlField("name")
    private String name;

    @SqlField("adress")
    private String adress;

    @SqlField("typeDescr")
    private String typeDescr;

    @SqlField("suit")
    private int suit;

    @SqlField("type")
    private int type;

    @SqlField("reports")
    private int reports;

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "objects",
            type = QueryType.setter)
    public JSONObject set() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Получает записи из таблицы.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "objects",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "objects",
            type = QueryType.updater)
    public JSONObject update() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Удаляет записи в таблицы ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "objects",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
