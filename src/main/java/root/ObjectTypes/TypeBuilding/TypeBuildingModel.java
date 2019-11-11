package root.ObjectTypes.TypeBuilding;

import root.core.Annotation.SqlField;
import root.core.Annotation.SqlQuery;
import root.core.Enums.SqlQueryEnum;
import root.core.SqlWork;

public class TypeBuildingModel
{
    @SqlField("id")
    public int id;
    @SqlField("name")
    public String name;
    @SqlField("floors")
    public int floors;
    @SqlField("flat")
    public int flat;
    @SqlField("foundationType")
    public String foundationType;
    @SqlField("roofType")
    public String roofType;
    @SqlField("coverType")
    public String coverType;
    @SqlField("square")
    public int square;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getFoundationType() {
        return foundationType;
    }

    public void setFoundationType(String foundationType) {
        this.foundationType = foundationType;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    @SqlQuery(table = "typebuilding", type = SqlQueryEnum.getter)
    public static String get() throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, "");
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typebuilding", type = SqlQueryEnum.getter)
    public static String getById(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, id);
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typebuilding", type = SqlQueryEnum.setter)
    public String set() throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(this, "");
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typebuilding", type = SqlQueryEnum.updater)
    public String updater(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(this, id);
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typebuilding", type = SqlQueryEnum.deleter)
    public static String delete(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, id);
        SqlWork.ConnectionClose();
        return result;
    }
}
