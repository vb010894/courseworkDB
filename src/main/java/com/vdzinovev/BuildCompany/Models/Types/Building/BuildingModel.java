package com.vdzinovev.BuildCompany.Models.Types.Building;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import org.json.JSONObject;

/**
 * Модель Тип Здание.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class BuildingModel {

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
     * Поле FLOORS.
     */
    @SqlField("floors")
    private int floors;

    /**
     * Поле FLAT.
     */
    @SqlField("flat")
    private int flat;

    /**
     * Поле ROOFTYPE.
     */
    @SqlField("roofType")
    private String roofType;

    /**
     * Поле FOUNDATIONTYPE.
     */
    @SqlField("foundationType")
    private String foundationType;

    /**
     * Поле COVERTYPE.
     */
    @SqlField("coverType")
    private String coverType;

    /**
     * Поле SQUARE.
     */
    @SqlField("square")
    private int square;

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
     * Возвращает значение FLOORS.
     * @return FLOORS
     */
    public int getFloors() {
        return floors;
    }

    /**
     * Получает значение FLOORS.
     * @param floors FLOORS
     */
    public void setFloors(final int floors) {
        this.floors = floors;
    }

    /**
     * Возвращает значение FLAT.
     * @return FLAT
     */
    public int getFlat() {
        return flat;
    }

    /**
     * Получает значение FLAT.
     * @param flat FLAT
     */
    public void setFlat(final int flat) {
        this.flat = flat;
    }

    /**
     * Возвращает значение ROOF TYPE.
     * @return ROOF TYPE
     */
    public String getRoofType() {
        return roofType;
    }

    /**
     * Получает значение ROOF TYPE.
     * @param roofType ROOF TYPE
     */
    public void setRoofType(final String roofType) {
        this.roofType = roofType;
    }

    /**
     * Возвращает значение FOUNDATION TYPE.
     * @return FOUNDATION TYPE
     */
    public String getFoundationType() {
        return foundationType;
    }

    /**
     * Получает значение FOUNDATION TYPE.
     * @param foundationType FOUNDATION TYPE
     */
    public void setFoundationType(final String foundationType) {
        this.foundationType = foundationType;
    }

    /**
     * Возвращает значение COVER TYPE.
     * @return COVER TYPE
     */
    public String getCoverType() {
        return coverType;
    }

    /**
     * Получает значение COVER TYPE.
     * @param coverType COVER TYPE
     */
    public void setCoverType(final String coverType) {
        this.coverType = coverType;
    }

    /**
     * Возвращает значение SQUARE.
     * @return SQUARE
     */
    public int getSquare() {
        return square;
    }

    /**
     * Получает значение SQUARE.
     * @param square SQUARE
     */
    public void setSquare(final int square) {
        this.square = square;
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "typebuilding",
            type = QueryType.setter)
    public JSONObject set() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Получает записи из таблицы.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "typebuilding",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "typebuilding",
            type = QueryType.updater)
    public JSONObject update() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Удаляет записи в таблицы ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "typebuilding",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
