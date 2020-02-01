package com.vdzinovev.BuildCompany.Models.Master;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlAlias;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlFilter;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import com.vdzinovev.BuildCompany.Models.TeamLeads.TeamLeadsModel;
import org.json.JSONObject;

/**
 * Модель таблицы MASTER.
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public class MasterModel {

    /**
     * Поле ID.
     */
    @SqlField(value = "id", id = true)
    private int id;

    /**
     * Поле FIO.
     */
    @SqlField("fio")
    private String fio;

    /**
     * Поле TEAMLEADS.
     */
    @SqlField("teamLeads")
    private int teamLeads;

    @SqlAlias(value = "fio",
            table = "teamleads",
            nickname = "teamLeadFio",
            fieldAlias = "master.teamLeads")
    private String teamLeadFio;

    @SqlAlias(value = "name",
            table = "departament",
            nickname = "department",
            fieldAlias = "teamleads.departament")
    private String department;

    /**
     * Фильтр по FIO.
     */
    @SqlFilter("fio")
    private String fioFilter = "";

    /**
     * Фильтр по ID.
     */
    @SqlFilter("master.id")
    private int idFilter = -11111;

    /**
     * Возвращает значение фильтра ID.
     * @return ID
     */
    public int getIdFilter() {
        return idFilter;
    }

    /**
     * Получает значение фильтра ID.
     * @param idFilter ID
     */
    public void setIdFilter(final int idFilter) {
        this.idFilter = idFilter;
    }

    /**
     * Возвращает значение фильтра FIO.
     * @return FIO
     */
    public String getFioFilter() {
        return fioFilter;
    }

    /**
     * Получает значение фильтра FIO.
     * @param fioFilter FIO
     */
    public void setFioFilter(final String fioFilter) {
        this.fioFilter = fioFilter;
    }

    /**
     * Возвращает значение поля ID.
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Получает значение поля ID.
     * @param id ID
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Возвращает значение поля FIO.
     * @return FIO
     */
    public String getFio() {
        return fio;
    }

    /**
     * Получает значение поля ID.
     * @param fio ID
     */
    public void setFio(final String fio) {
        this.fio = fio;
    }

    /**
     * Возвращает значение поля TEAMLEADS ID.
     * @return TEAMLEADS ID
     */
    public int getTeamLeads() {
        return teamLeads;
    }

    /**
     * Получает значение поля TEAMLEADS ID.
     * @param teamLeads TEAMLEADS ID
     */
    public void setTeamLeads(int teamLeads) {
        this.teamLeads = teamLeads;
    }

    /**
     * Возвращает значение поля TEAMLEADS FIO.
     * @return TEAMLEADS FIO
     */
    public String getTeamLeadFio() {
        return teamLeadFio;
    }

    /**
     * Получает значение поля TEAMLEADS FIO.
     * @param teamLeadFio TEAMLEADS FIO
     */
    public void setTeamLeadFio(String teamLeadFio) {
        this.teamLeadFio = teamLeadFio;
    }

    /**
     * Возвращает значение поля DEPARTMENT.
     * @return DEPARTMENT
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Получает значение поля DEPARTMENT.
     * @param department DEPARTMENT
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Получает ID поля в связанной таблице.
     * @return ID
     * @throws Exception Пропуск исключений
     */
    private void getIDAliasModel() throws Exception {
        TeamLeadsModel model = new TeamLeadsModel();
        model.setFioFilter(this.teamLeadFio);
        JSONObject idDepartment =  model.get();
        for (Object json: idDepartment
                .getJSONArray("items")) {
            JSONObject temp = (JSONObject) json;
            if(temp.getString("fio").equals(this.teamLeadFio)) {
                int check = temp.getInt("id");
                this.setTeamLeads(temp.getInt("id"));
            }
        }
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "master",
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
    @SqlQuery(table = "master",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "master",
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
    @SqlQuery(table = "master",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
