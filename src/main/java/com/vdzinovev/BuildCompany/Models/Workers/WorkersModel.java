package com.vdzinovev.BuildCompany.Models.Workers;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.*;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import com.vdzinovev.BuildCompany.Models.Group.GroupModel;
import org.json.JSONObject;

/**
 * Модель работников
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public class WorkersModel {

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
     * Поле GROUP.
     */
    @SqlField("group")
    private int group;

    /**
     * Поле LEADER.
     */
    @SqlField("leader")
    private int leader;

    /**
     * Связанное поле GROUP SHIFT.
     */
    @SqlAlias(value = "shift",
            table = "`group`",
            nickname = "groupName",
            fieldAlias = "workeers.group")
    private String groupName;

    /**
     * Фильтр по смене.
     */
    @SqlFilter("leader")
    private int leaderFilter = -11111;

    @SqlFunctionParams(functionName = "createled")
    private  String paramsLeader;

    /**
     * Возвращает параметры процедуры.
     * @return Параметры
     */
    public String getParamsLeader() {
        return paramsLeader;
    }

    /**
     * Получает параметры процедуры.
     * @param paramsLeader Параметры
     */
    public void setParamsLeader(final String paramsLeader) {
        this.paramsLeader = paramsLeader;
    }

    /**
     * Возвращает значение фильтра.
     * @return фильтр LEADER
     */
    public int getLeaderFilter() {
        return leaderFilter;
    }

    /**
     * Получает значение фильтра.
     * @param leaderFilter LEADER
     */
    public void setLeaderFilter(final int leaderFilter) {
        this.leaderFilter = leaderFilter;
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
     * Возвращает значение FIO.
     * @return FIO
     */
    public String getFio() {
        return fio;
    }

    /**
     * Получает значение FIO.
     * @param fio FIO
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
     * Возвращает значение LEADER.
     * @return LEADER
     */
    public int getLeader() {
        return leader;
    }

    /**
     * Получает значение LEADER.
     * @param leader LEADER
     */
    public void setLeader(final String leader) {
        if(leader
                .toUpperCase()
                .equals("ДА")
        || leader
                .toUpperCase()
                .equals("Д")) {
            this.leader = 1;
        } else {
            this.leader = 0;
        }
    }

    /**
     * Возвращает значение GROUP SHIFT.
     * @return GROUP SHIFT
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Получает значение GROUP SHIFT.
     * @param groupName GROUP SHIFT
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
    @SqlQuery(table = "workeers",
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
    @SqlQuery(table = "workeers",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице GROUP.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "workeers",
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
    @SqlQuery(table = "workeers",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Выполняет процедуры назначения Бригадиром.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "createled",
            type = QueryType.procedure)
    public JSONObject callCreateLeader() throws Exception {
        return DbLib.getResult(this);
    }
}
