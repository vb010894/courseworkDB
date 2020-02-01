package com.vdzinovev.BuildCompany.Models.Group;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlAlias;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlFilter;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import com.vdzinovev.BuildCompany.Models.Master.MasterModel;
import org.json.JSONObject;

/**
 * Модель бригады.
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public class GroupModel {

    /**
     * Поле ID.
     */
    @SqlField(value = "id",
            id = true)
    private int id;

    /**
     * Поле SHIFT.
     */
    @SqlField("shift")
    private String shift;

    /**
     * Поле MASTE.
     */
    @SqlField("maste")
    private int maste;

    /**
     * Связанное поле MASTER NAME.
     */
    @SqlAlias(value = "fio",
            table = "master",
            nickname = "masterName",
            fieldAlias = "group.maste")
    private String masterName;

    /**
     * Фильтр по смене.
     */
    @SqlFilter("shift")
    private String shiftFilter = "";

    /**
     * Возвращает значение фильтра.
     * @return Фильтр POSITION
     */
    public String getShiftFilter() {
        return shiftFilter;
    }

    /**
     * Получает значение фильтра.
     * @param shiftFilter Фильтр POSITION
     */
    public void setShiftFilter(final String shiftFilter) {
        this.shiftFilter = shiftFilter;
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
     * Возвращает значение SHIFT.
     * @return SHIFT
     */
    public String getShift() {
        return shift;
    }

    /**
     * Получает значение SHIFT.
     * @param shift SHIFT
     */
    public void setShift(final String shift) {
        this.shift = shift;
    }

    /**
     * Возвращает значение MASTER.
     * @return MASTER
     */
    public int getMaste() {
        return maste;
    }

    /**
     * Получает значение MASTER.
     * @param maste MASTER
     */
    public void setMaste(int maste) {
        this.maste = maste;
    }

    /**
     * Возвращает значение фильтра MASTER FIO.
     * @return MASTER FIO
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * Получает значение фильтра MASTER.
     * @param masterName Фильтр MASTER
     */
    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    /**
     * Получает ID поля в связанной таблице.
     * @return ID
     * @throws Exception Пропуск исключений
     */
    private void getIDAliasModel() throws Exception {
        MasterModel model = new MasterModel();
        model.setFioFilter(this.masterName);
        JSONObject idMaster =  model.get();
        for (Object json: idMaster
                .getJSONArray("items")) {
            JSONObject temp = (JSONObject) json;
            if(temp.getString("fio").equals(this.masterName)) {
                this.setMaste(temp.getInt("id"));
            }
        }
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "`group`",
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
    @SqlQuery(table = "`group`",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице GROUP.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "`group`",
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
    @SqlQuery(table = "`group`",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
