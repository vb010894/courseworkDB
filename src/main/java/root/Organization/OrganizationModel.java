package root.Organization;

import org.json.JSONArray;
import org.json.JSONObject;
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

    public String getOktmo() {
        return oktmo;
    }

    public void setOktmo(String oktmo) {
        this.oktmo = oktmo;
    }

    private String name;
    private String ogrn;
    private String inn;
    private String kpp;
    private String oktmo;


    public String getOrganization(int start, int page, int limit, String id) throws Exception
    {
        JSONObject result = new JSONObject();
        JSONArray items = new JSONArray();
        int count = 0;

        String sqlQuery = (id == "") ? "select * from organization limit " + start +", " + page * limit : "select * from organization where (id = " + id + ") limit " + start +", " + page * limit;
        System.out.println(sqlQuery);
        SqlWork sqlWork = new SqlWork();
        sqlWork.openConnection();
        ResultSet resultSet = sqlWork.getResult(sqlQuery);
       try
       {
           while (resultSet.next())
           {
               count = resultSet.getRow();
               result.put("idOrganization", resultSet.getString("id"));
               result.put("name", resultSet.getString("name"));
               result.put("inn", resultSet.getString("inn"));
               result.put("kpp", resultSet.getString("kpp"));
               result.put("oktmo", resultSet.getString("oktmo"));
               result.put("ogrn", resultSet.getString("ogrn"));
               items.put(result);
           }
       }
       catch (SQLException sql)
       {
           System.out.println();
           System.out.println("------ОШИБКА------");
           System.out.println("Сообщение - " + sql);
           System.out.println("StackTrace - " + sql.getStackTrace());
       }

       result = new JSONObject();
       if(count == 0 )
       {
           result.put("total", count);
           result.put("state", false);
           result.put("items", "[]");
       }
       else
       {
           result.put("total", count);
           result.put("state", true);
           result.put("items", items);
       }
       sqlWork.closeConnection();
        return result.toString();
    }

    public JSONObject setOrganization() throws Exception
    {
        JSONObject result = new JSONObject();
        String sqlQuery = "insert into organization" +
                "(" +
                "name," +
                "inn," +
                "kpp," +
                "oktmo," +
                "ogrn" +
                ") " +
                "values" +
                "('" + this.name + "','" +
                 this.inn + "','" +
                 this.kpp + "','" +
                 this.oktmo + "','" +
                 this.ogrn + "'" +
                ")";
        SqlWork sql = new SqlWork();
        sql.openConnection();
        sql.updateQuery(sqlQuery);
        sql.closeConnection();
        result.put("State", true);
        return result;
    }

    public JSONObject deleteOrganization(String id) throws Exception
    {
        JSONObject result = new JSONObject();
        String sqlQuery = "delete from organization " +
                "where (" +
                "id = " + id +
                ")";
        System.out.println(sqlQuery);
        SqlWork sql = new SqlWork();
        sql.openConnection();
        sql.updateQuery(sqlQuery);
        sql.closeConnection();
        result.put("State", true);
        return result;
    }

    public JSONObject updateOrganization(String id) throws Exception
    {
        JSONObject result = new JSONObject();
        String sqlQuery = "update organization set " +
                "name = '" + this.name + "'," +
                "inn = '" + this.inn + "'," +
                "kpp = '" + this.kpp + "'," +
                "oktmo = '" + this.oktmo + "'," +
                "ogrn = '" + this.ogrn + "' " +
                "where (" +
                "id = " + id +
                ")";
        System.out.println(sqlQuery);
        SqlWork sql = new SqlWork();
        sql.openConnection();
        sql.updateQuery(sqlQuery);
        sql.closeConnection();
        result.put("State", true);
        return result;
    }
}
