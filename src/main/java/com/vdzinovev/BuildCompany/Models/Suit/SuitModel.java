package com.vdzinovev.BuildCompany.Models.Suit;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlAlias;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlFilter;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import com.vdzinovev.BuildCompany.Models.Departament.DepartmentModel;
import org.json.JSONObject;

/**
 * Модель Участков.
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
public class SuitModel {

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
     * Поле DEPARTMENT.
     */
    @SqlField("departament")
    private int department;

    /**
     * Связанное поле DEPARTMENT NAME.
     */
    @SqlAlias(value = "name",
            table = "departament",
            nickname = "depName",
            fieldAlias = "suit.departament")
    private String depName;

    /**
     * Фильтр по NAME.
     */
    @SqlFilter("name")
    private String filterName = "";

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
     * Возвращает значение DEPARTMENT.
     * @return DEPARTMENT
     */
    public int getDepartment() {
        return department;
    }

    /**
     * Получает значение DEPARTMENT.
     * @param department DEPARTMENT
     */
    public void setDepartment(final int department) {
        this.department = department;
    }

    /**
     * Возвращает значение DEPNAME.
     * @return DEPNAME
     */
    public String getDepName() {
        return depName;
    }

    /**
     * Получает значение DEPNAME.
     * @param depName DEPNAME
     */
    public void setDepName(final String depName) {
        this.depName = depName;
    }

    /**
     * Возвращает значение FILTERNAME.
     * @return FILTERNAME
     */
    public String getFilterName() {
        return filterName;
    }

    /**
     * Получает значение FILTERNAME.
     * @param filterName FILTERNAME
     */
    public void setFilterName(final String filterName) {
        this.filterName = filterName;
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
            if(temp.getString("name").equals(this.depName)) {
                this.setDepartment(temp.getInt("id"));
            }
        }
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "suit",
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
    @SqlQuery(table = "suit",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "suit",
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
    @SqlQuery(table = "suit",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
