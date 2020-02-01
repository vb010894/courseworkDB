package com.vdzinovev.BuildCompany.Models.Types.Bridge;

import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlField;
import com.vdzinovev.BuildCompany.Core.Sql.Annotation.SqlQuery;
import com.vdzinovev.BuildCompany.Core.Sql.DbLib;
import com.vdzinovev.BuildCompany.Core.Sql.Enums.QueryType;
import org.json.JSONObject;

/**
 * Класс модели Тип Мост.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
public class Bridge {

    /**
     * Поле ID.
     */
    @SqlField(value = "id",
            id = true)
    private int id;

    /**
     * Поле Name.
     */
    @SqlField("name")
    private String name;

    /**
     * Поле LENGHT.
     */
    @SqlField("lenght")
    private int length;

    /**
     * Поле PARTTYPE.
     */
    @SqlField("partType")
    private String partType;

    /**
     * Поле PARTCOUNT.
     */
    @SqlField("partCount")
    private int partCount;

    /**
     * Поле DRIVELINECOUNT.
     */
    @SqlField("driverLineCount")
    private int driveLineCount;

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
     * Возвращает значение LENGHT.
     * @return LENGHT
     */
    public int getLength() {
        return length;
    }

    /**
     * Получает значение LENGHT.
     * @param length LENGHT
     */
    public void setLength(final int length) {
        this.length = length;
    }

    /**
     * Возвращает значение PARTTYPE.
     * @return PARTTYPE
     */
    public String getPartType() {
        return partType;
    }

    /**
     * Получает значение PARTTYPE.
     * @param partType PARTTYPE
     */
    public void setPartType(final String partType) {
        this.partType = partType;
    }

    /**
     * Возвращает значение PARTCOUNT.
     * @return PARTCOUNT
     */
    public int getPartCount() {
        return partCount;
    }

    /**
     * Получает значение PARTCOUNT.
     * @param partCount PARTCOUNT
     */
    public void setPartCount(final int partCount) {
        this.partCount = partCount;
    }

    /**
     * Возвращает значение DRIVELINECOUNT.
     * @return DRIVELINECOUNT
     */
    public int getDriveLineCount() {
        return driveLineCount;
    }

    /**
     * Получает значение DRIVELINECOUNT.
     * @param driveLineCount DRIVELINECOUNT
     */
    public void setDriveLineCount(final int driveLineCount) {
        this.driveLineCount = driveLineCount;
    }

    /**
     * Делает запись в таблицу.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "typebradge",
            type = QueryType.setter)
    public JSONObject set() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Получает записи из таблицы.
     * @return Результат
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "typebradge",
            type = QueryType.getter)
    public JSONObject get() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Обновление записи в таблице ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "typebradge",
            type = QueryType.updater)
    public JSONObject update() throws Exception {
        return DbLib.getResult(this);
    }

    /**
     * Удаляет записи в таблицы ORGANIZATION.
     * @return Ответ клиенту
     * @throws Exception Пропуск исключений
     */
    @SqlQuery(table = "typebradge",
            type = QueryType.deleter)
    public JSONObject delete() throws Exception {
        return DbLib.getResult(this);
    }
}
