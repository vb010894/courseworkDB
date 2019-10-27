package root.TeamLeads;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;

import java.sql.ResultSet;

public class TeamLeadsModel
{
    private static final SqlWork sql = new SqlWork();

    private String fio;

    private int departament;

    public int getDepartament()
    {
        return departament;
    }

    public void setDepartament(int departament)
    {
        this.departament = departament;
    }

    public String getFio()
    {
        return fio;
    }

    public void setFio(String fio)
    {
        this.fio = fio;
    }

    public void setTeamLeads() throws Exception
    {
        sql.openConnection();
        String query = (this.departament == 0) ? "insert into teamleads(FIO) values('" + this.fio + "')" : "insert into teamleads(FIO, departament) values('" + this.fio + "', " +  this.departament + ")";
        sql.updateQuery(query);
        sql.closeConnection();
    }

    public static String getTeamLeads() throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from teamleads");
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("fio", result.getString("FIO"));
            res.put("departament", result.getInt("departament"));
            items.put(res);
        }

        if(count > 0)
        {
            res = new JSONObject();
            res.put("state", true);
            res.put("count", count);
            res.put("items", items);
            return res.toString();
        }
        else
        {
            res = new JSONObject();
            res.put("state", false);
            res.put("items", "[]");
            res.put("count", 0);
            return res.toString();
        }

    }

    public static String getTeamLeadsById(String id) throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from teamleads where id = " + id);
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("fio", result.getString("FIO"));
            res.put("departament", result.getInt("departament"));
            items.put(res);
        }

        if(count > 0)
        {
            res = new JSONObject();
            res.put("state", true);
            res.put("count", count);
            res.put("items", items);
            return res.toString();
        }
        else
        {
            res = new JSONObject();
            res.put("state", false);
            res.put("items", "[]");
            res.put("count", 0);
            return res.toString();
        }

    }

    public void updateTeamLeads(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("update teamleads set FIO = '" + this.fio + "', departament = " + this.departament + " where id = " + id);
        sql.closeConnection();
    }

    public static void deleteTeamLeads(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from teamleads where id = " + id);
        sql.closeConnection();
    }
}
