package com.vdzinovev.BuildCompany.Models.Engeneers;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlAlias;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import com.vdzinovev.BuildCompany.Models.Group.GroupModel;
import org.json.JSONObject;

/**
 * Модель Инженеров.
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public class EngeneerModel {

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
    private String fio;

    /**
     * Поле POSITION.
     */
    @SqlField("position")
    private String position;

    /**
     * Поле OFFICE.
     */
    @SqlField("office")
    private String office;

    /**
     * Поле GROUP.
     */
    @SqlField("group")
    private int group;

    /**
     * Связанное поле GROUP SHIFT.
     */
    @SqlAlias(value = "shift",
            table = "`group`",
            nickname = "groupName",
            fieldAlias = "engeneers.group")
    private String groupName;

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
     * Возвращает значение FIO.
     * @return FIO
     */
    public String getFio() {
        return fio;
    }

    /**
     * Получает значение ID.
     * @param fio ID
     */
    public void setFio(final String fio) {
        this.fio = fio;
    }

    /**
     * Возвращает значение POSITION.
     * @return POSITION
     */
    public String getPosition() {
        return position;
    }

    /**
     * Получает значение POSITION.
     * @param position POSITION
     */
    public void setPosition(final String position) {
        this.position = position;
    }

    /**
     * Возвращает значение OFFICE.
     * @return OFFICE
     */
    public String getOffice() {
        return office;
    }

    /**
     * Получает значение OFFICE.
     * @param office OFFICE
     */
    public void setOffice(final String office) {
        this.office = office;
    }

    /**
     * Возвращает значение GROUP.
     * @return GROUP
     */
    public int getGroup() {
        return group;
    }

    /**
     * Получает значение GROUP.
     * @param group GROUP
     */
    public void setGroup(final int group) {
        this.group = group;
    }

    /**
     * Возвращает значение GROUP NAME.
     * @return GROUP NAME
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Получает значение GROUP NAME.
     * @param groupName GROUP NAME
     */
    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    /**
     * Получает ID поля в связанной таблице.
     * @return ID
     * @throws Exception Пропуск исключений
     */
    private void getIDAliasModel() throws Exception {
        GroupModel model = new GroupModel();
        model.setShiftFilter(this.groupName);
        JSONObject idMaster =  model.get();
        for (Object json: idMaster
                .getJSONArray("items")) {
            JSONObject temp = (JSONObject) json;
            if(temp.getString("shift").equals(this.groupName)) {
                this.setGroup(temp.getInt("id"));
            }
        }
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "engeneers",
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
    @SqlQuery(table = "engeneers",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице GROUP.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "engeneers",
            type = QueryType.updater)
    public JSONObject update() throws Exception {
        this.getIDAliasModel();
        return DbLib.getResult(this);
    }

    /**
     * Удаляет записи в таблицы GROUP.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "engeneers",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
