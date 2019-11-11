package root.Organization;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.Annotation.SqlField;
import root.core.Annotation.SqlQuery;
import root.core.Enums.SqlQueryEnum;
import root.core.SqlWork;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationModel
{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    @SqlField("id")
    public int id;
    @SqlField("name")
    public String name;
    @SqlField("adress")
    public String ogrn;
    @SqlField("inn")
    public String inn;
    @SqlField("kpp")
    public String kpp;


    @SqlQuery(table = "organization", type = SqlQueryEnum.getter)
    public static String get() throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, "");
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "organization", type = SqlQueryEnum.getter)
    public static String getById(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, id);
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "organization", type = SqlQueryEnum.setter)
    public String set() throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(this, "");
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "organization", type = SqlQueryEnum.updater)
    public String updater(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(this, id);
        SqlWork.ConnectionClose();
        return result;
    }

    @SqlQuery(table = "organization", type = SqlQueryEnum.deleter)
    public static String delete(String id) throws Exception
    {
        SqlWork.ConnectionOpen();
        String result = SqlWork.result(null, id);
        SqlWork.ConnectionClose();
        return result;
    }
}
