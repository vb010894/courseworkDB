package com.vdzinovev.BuildCompany.Models.TeamLeads;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlAlias;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlFilter;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import com.vdzinovev.BuildCompany.Models.Departament.DepartmentModel;
import org.json.JSONObject;

/**
 * Модель начальников управлений.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class TeamLeadsModel {

    /**
     * Поле ID.
     */
    @SqlField(value = "id",
            id = true)
    private int id;

    /**
     * Поле FIO.
     */
    @SqlField("fio")
    private String FIO = "";

    /**
     * Поле DEPARTMENT ID.
     */
    @SqlField("departament")
    private int department;

    /**
     * Связанное поле DEPARTMENT NAME.
     */
    @SqlAlias(value = "name",
            table = "departament",
            nickname = "depName",
            fieldAlias = "teamleads.departament")
    private String depName;

    /**
     * Фильтр по FIO.
     */
    @SqlFilter("fio")
    private String fioFilter = "";


    /**
     * Фильтр по ID.
     */
    @SqlFilter("id")
    private int idFilter = -11111;

    /**
     * Возвращает фильтр ID.
     * @return Фильтр ID
     */
    public int getIdFilter() {
        return idFilter;
    }

    /**
     * Получает фильтр ID.
     * @param idFilter Фильтр ID
     */
    public void setIdFilter(final int idFilter) {
        this.idFilter = idFilter;
    }

    /**
     * Возвращает фильтр FIO.
     * @return Фильтр FIO
     */
    public String getFioFilter() {
        return fioFilter;
    }

    /**
     * Получает фильтр FIO.
     * @param fioFilter Фильтр FIO
     */
    public void setFioFilter(final String fioFilter) {
        this.fioFilter = fioFilter;
    }

    /**
     * Возвращает NAME DEPARTMENT.
     * @return NAME
     */
    public String getDepName() {
        return depName;
    }

    /**
     * Получает NAME DEPARTMENT.
     * @param depName NAME
     */
    public void setDepName(final String depName) {
        this.depName = depName;
    }

    /**
     * Возвращает значение ID.
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Получает значение ID.
     * @param id ID
     */
    public void setId(final int id) {
        this.id = id;
    }


    /**
     * Получает значение FIO.
     * @return
     */
    public String getFIO() {
        return FIO;
    }

    /**
     * Задает значение FIO.
     * @param FIO FIO
     */
    public void setFIO(final String FIO) {
        this.FIO = FIO;
    }

    /**
     * Возвращает значение DEPARTMENT.
     * @return DEPARTMENT
     */
    public int getDepartment() {
        return department;
    }

    /**
     *  Получает значение DEPARTMENT.
     * @param department DEPARTMENT.
     */
    public void setDepartment(final int department) {
        this.department = department;
    }

    /**
     * Получает ID поля в связанной таблице.
     * @return ID
     * @throws Exception Пропуск исключений
     */
    private void getIDAliasModel() throws Exception {
        DepartmentModel model = new DepartmentModel();
        model.setNameFilter(this.depName);
        JSONObject idDepartment =  model.get();
        for (Object json: idDepartment
                .getJSONArray("items")) {
            JSONObject temp = (JSONObject) json;
            if(temp.getString("name").equals(depName)) {
                this.department = temp.getInt("id");
            }
        }
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "teamleads",
            type = QueryType.setter)
    public JSONObject set() throws Exception {
        this.getIDAliasModel();
        return DbLib.getResult(this);
    }

    /**
     * Получает записи из таблицы.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "teamleads",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "teamleads",
            type = QueryType.updater)
    public JSONObject update() throws Exception {
        this.getIDAliasModel();
        return DbLib.getResult(this);
    }

    /**
     * Удаляет записи в таблицы ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "teamleads",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
