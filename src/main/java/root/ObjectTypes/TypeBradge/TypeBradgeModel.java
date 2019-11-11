package root.ObjectTypes.TypeBradge;

import root.core.Annotation.SqlField;
import root.core.Annotation.SqlQuery;
import root.core.Enums.SqlQueryEnum;
import root.core.SqlWork;

public class TypeBradgeModel
{
    @SqlField("id")
    public int id;
    @SqlField("name")
    public String name;
    @SqlField("lenght")
    public int lenght;
    @SqlField("partType")
    public String partType;
    @SqlField("partCount")
    public int partCount;
    @SqlField("driverLineCount")
    public int driverLineCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public int getPartCount() {
        return partCount;
    }

    public void setPartCount(int partCount) {
        this.partCount = partCount;
    }

    public int getDriverLineCount() {
        return driverLineCount;
    }

    public void setDriverLineCount(int driverLineCount) {
        this.driverLineCount = driverLineCount;
    }

    @SqlQuery(table = "typebradge", type = SqlQueryEnum.getter)
    public static String get() throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, "");
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typebradge", type = SqlQueryEnum.getter)
    public static String getById(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, id);
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typebradge", type = SqlQueryEnum.setter)
    public String set() throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(this, "");
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typebradge", type = SqlQueryEnum.updater)
    public String updater(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(this, id);
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typebradge", type = SqlQueryEnum.deleter)
    public static String delete(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, id);
        SqlWork.ConnectionClose();
        return result;
    }
}
