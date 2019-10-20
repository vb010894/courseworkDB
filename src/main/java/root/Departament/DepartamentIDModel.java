package root.Departament;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;
import java.sql.ResultSet;


public class DepartamentIDModel
{
    private static final SqlWork sql = new SqlWork();

    private String name;

    private String adress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setDepartament(int org) throws Exception
    {
        sql.openConnection();
        sql.updateQuery
                (
                        "insert into departament" +
                                "(" +
                                "name," +
                                "adress," +
                                "org" +
                                ") values " +
                                "(" +
                                "'" + this.name + "'," +
                                "'" + this.adress + "'," +
                                "" + org +"" +
                                ")"
                );
        sql.closeConnection();
    }

    public void updateDepartament(String idDepartament, String idOrg) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("update departament set name = '" + this.name + "', adress = '" + this.adress + "' where (id = " + idDepartament + " and org = " + idOrg +")");
        sql.closeConnection();
    }

    public static String getDepartaments(String idDepartamnet, String idOrg) throws Exception
    {
        JSONObject temp;
        JSONArray tempArray = new JSONArray();
        int resultCount = 0;
        sql.openConnection();
        String query = (idDepartamnet != null) ? "select * from departament where (id = " + idDepartamnet + " and org = " + idOrg + ")" : "select * from departament where org = " + idOrg;

        ResultSet result = sql.getResult(query);

        while (result.next())
        {
            resultCount = result.getRow();
            temp = new JSONObject();
            temp.put("id", result.getInt("id"));
            temp.put("name", result.getString("name"));
            temp.put("adress", result.getString("adress"));
            tempArray.put(temp);
        }
        sql.closeConnection();

        if(resultCount == 0)
        {
            temp = new JSONObject();
            temp.put("state", false);
            temp.put("items", "[]");
            temp.put("count", 0);
            return temp.toString();
        }
        else
        {
            temp = new JSONObject();
            temp.put("state", true);
            temp.put("items", tempArray);
            temp.put("count", resultCount);
            return temp.toString();
        }
    }

    public static void deleteDepartament(String idDepartament, String idOrg) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from departament where (id = " + idDepartament + " and org = " + idOrg + ")");
        sql.closeConnection();
    }

}
