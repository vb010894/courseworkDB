package root.ObjectTypes.TypeRoad;

import root.core.Annotation.SqlField;
import root.core.Annotation.SqlQuery;
import root.core.Enums.SqlQueryEnum;
import root.core.SqlWork;

public class TypeRoadModel
{
    @SqlField("id")
    public String id;

    @SqlField("name")
    public String name;

    @SqlField("cover")
    public String cover;

    @SqlField("lenght")
    public int lenght;

    @SqlField("driveLineCount")
    public int driveLineCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getDriveLineCount() {
        return driveLineCount;
    }

    public void setDriveLineCount(int driveLineCount) {
        this.driveLineCount = driveLineCount;
    }

    @SqlQuery(table = "typeroad", type = SqlQueryEnum.getter)
    public static String get() throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, "");
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typeroad", type = SqlQueryEnum.getter)
    public static String getById(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, id);
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typeroad", type = SqlQueryEnum.setter)
    public String set() throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(this, "");
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typeroad", type = SqlQueryEnum.updater)
    public String updater(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(this, id);
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "typeroad", type = SqlQueryEnum.deleter)
    public static String delete(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, id);
        SqlWork.ConnectionClose();
        return result;
    }
}
