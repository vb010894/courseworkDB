package com.vdzinovev.BuildCompany.Models.Reports;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlFunctionParams;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import com.vdzinovev.BuildCompany.Models.Group.GroupModel;
import org.json.JSONObject;

/**
 * Модель Отчетов.
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public class ReportsModel {

    private static final String dateFormat  = "%e.%m.%Y";

    /**
     * Поле ID.
     */
    @SqlField(value = "id",
            id = true)
    private int id;

    /**
     * Поле DESCRIPTION.
     */
    @SqlField("description")
    private String description;

    /**
     * Поле IdWorks.
     */
    @SqlField("idWorks")
    private int idWorks;

    /**
     * Поле TYPE.
     */
    @SqlField("type")
    private String type;

    /**
     * Пол PlanDate.
     */
    @SqlField(value = "planDate",
            date = true,
            dateFormat = dateFormat)
    private String planDate;

    /**
     * Поле endDate.
     */
    @SqlField(value = "endPlan",
            date = true,
            dateFormat = dateFormat)
    private String endPlan;

    /**
     * Поле startFact.
     */
    @SqlField(value = "startFact",
            date = true,
            dateFormat = dateFormat)
    private String startFact;

    /**
     * Поле endFact.
     */
    @SqlField(value = "endFact",
            date = true,
            dateFormat = dateFormat)
    private String endFact;

    /**
     * Поле ID SCHEDULE.
     */
    @SqlField(value = "idSced")
    private int idSced;

    /**
     * Поле ID ESTIMATE.
     */
    @SqlField("idEst")
    private int idEst;

    /**
     * Поле ID DESCRIPTION.
     */
    @SqlField("estDescr")
    private String estDescr;

    /**
     * Поле CAST ALL.
     */
    @SqlField("costAll")
    private float costAll;

    /**
     * Поле ID GROUP.
     */
    @SqlField("idGroup")
    private int idGroup;

    /**
     * Поле SHIFT GROUP.
     */
    @SqlField("shift")
    private String shift;

    /**
     * Поле STATUS.
     */
    @SqlField("status")
    private String status;

    /**
     * Возвращает STATUS.
     * @return STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * Получает значение STATUS.
     * @param status STATUS
     */
    public void setStatus(final String status) {
        switch (status) {
            case "Активный":
                this.status = "1";
            break;
            case "Остановлено":
                this.status = "2";
            break;
            case "Новый":
                this.status = "3";
            break;
            default:
                this.status = "4";
            break;
        }
    }

    /**
     * Возвращает ID GROUP.
     * @return ID GROUP
     */
    public int getIdGroup() {
        return idGroup;
    }

    /**
     * Получает значение ID GROUP.
     * @param idGroup ID GROUP
     */
    public void setIdGroup(final int idGroup) {
        this.idGroup = idGroup;
    }

    /**
     * Возвращает GROUP SHIFT.
     * @return GROUP SHIFT
     */
    public String getShift() {
        return shift;
    }

    /**
     * Получает значение GROUP SHIFT.
     * @param shift GROUP SHIFT
     */
    public void setShift(final String shift) {
        this.shift = shift;
    }

    /**
     * Возвращает значение формат даты.
     * @return Формат
     */
    public static String getDateFormat() {
        return dateFormat;
    }

    /**
     * Возвращает значение ID WORK.
     * @return ID WORK
     */
    public int getIdWorks() {
        return idWorks;
    }

    /**
     * Получает значение ID WORK.
     * @param idWorks ID WORK
     */
    public void setIdWorks(final int idWorks) {
        this.idWorks = idWorks;
    }

    /**
     * Возвращает значение TYPE.
     * @return TYPE
     */
    public String getType() {
        return type;
    }

    /**
     * Получает значение TYPE.
     * @param type TYPE
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Возвращает значение PLAN DATE.
     * @return PLAN DATE
     */
    public String getPlanDate() {
        return planDate;
    }

    /**
     * Получает значение PLAN DATE.
     * @param planDate PLAN DATE
     */
    public void setPlanDate(final String planDate) {
        this.planDate = planDate;
    }

    /**
     * Возвращает значение END PLAN DATE.
     * @return END PLAN DATE
     */
    public String getEndPlan() {
        return endPlan;
    }

    /**
     * Получает значение END PLAN DATE.
     * @param endPlan END PLAN DATE
     */
    public void setEndPlan(final String endPlan) {
        this.endPlan = endPlan;
    }

    /**
     * Возвращает значение START FACT.
     * @return START FACT
     */
    public String getStartFact() {
        return startFact;
    }

    /**
     * Получает значение START FACT.
     * @param startFact  START FACT
     */
    public void setStartFact(final String startFact) {
        this.startFact = startFact;
    }

    /**
     * Возвращает значение END FACT.
     * @return END FACT
     */
    public String getEndFact() {
        return endFact;
    }

    /**
     * Получает значение END FACT.
     * @param endFact END FACT
     */
    public void setEndFact(final String endFact) {
        this.endFact = endFact;
    }

    /**
     * Возвращает значение ID SCHEDULE.
     * @return ID SCHEDULE
     */
    public int getIdSced() {
        return idSced;
    }

    /**
     * Получает значение ID SCHEDULE.
     * @param idSced ID SCHEDULE
     */
    public void setIdSced(final int idSced) {
        this.idSced = idSced;
    }

    /**
     * Возвращает значение ID ESTIMATE.
     * @return ID ESTIMATE
     */
    public int getIdEst() {
        return idEst;
    }

    /**
     * Получает значение ID ESTIMATE.
     * @param idEst ID ESTIMATE
     */
    public void setIdEst(final int idEst) {
        this.idEst = idEst;
    }

    /**
     * Возвращает значение ESTIMATE DESCRIPTION.
     * @return ESTIMATE DESCRIPTION
     */
    public String getEstDescr() {
        return estDescr;
    }

    /**
     * Получает значение ESTIMATE DESCRIPTION.
     * @param estDescr ESTIMATE DESCRIPTION
     */
    public void setEstDescr(final String estDescr) {
        this.estDescr = estDescr;
    }

    /**
     * Возвращает значение COST ALL.
     * @return COST ALL
     */
    public float getCostAll() {
        return costAll;
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
     * Возвращает значение DESCRIPTION.
     * @return DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * Получает значение DESCRIPTION.
     * @param description DESCRIPTION
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    @SqlFunctionParams(functionName = "AddReport")
    private String params;

    /**
     * Получает ID поля в связанной таблице.
     * @return ID
     * @throws Exception Пропуск исключений
     */
    private void getIDAliasModel() throws Exception {
        GroupModel model = new GroupModel();
        model.setShiftFilter(this.shift);
        JSONObject idGroup =  model.get();
        for (Object json: idGroup
                .getJSONArray("items")) {
            JSONObject temp = (JSONObject) json;
            if(temp.getString("shift").equals(this.shift)) {
                this.setIdGroup(temp.getInt("id"));
            }
        }
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "AddReport",
            type = QueryType.procedure)
    public JSONObject set() throws Exception {
        getIDAliasModel();
        StringBuilder builder = new StringBuilder();
        builder.append("\'")
                .append(type)
                .append("\'")
                .append(", \'")
                .append(description)
                .append("\'")
                .append(", \'")
                .append(status)
                .append("\'")
                .append(", ")
                .append("STR_TO_DATE('")
                .append(planDate)
                .append("', '%e.%m.%Y'), ")
                .append("STR_TO_DATE('")
                .append(endPlan)
                .append("', '%e.%m.%Y'), ")
                .append("STR_TO_DATE('")
                .append(startFact)
                .append("', '%e.%m.%Y'), ")
                .append("STR_TO_DATE('")
                .append(endFact)
                .append("', '%e.%m.%Y'), ")
                .append(idGroup);

        this.params = builder.toString();
        return DbLib.getResult(this);
    }

    /**
     * Получает записи из таблицы.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "building.getreport",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице GROUP.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "reports",
            type = QueryType.updater)
    public JSONObject update() throws Exception {
        getIDAliasModel();
        return DbLib.getResult(this);
    }

    /**
     * Удаляет записи в таблицы GROUP.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "reports",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
