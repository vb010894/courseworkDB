package root.Masters;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;

import java.sql.ResultSet;

public class MasterModel
{
    private static final SqlWork sql = new SqlWork();

    private String fio;

    private int teamLead;

    public int getTeamLead()
    {
        return teamLead;
    }

    public void setTeamLead(int teamLead)
    {
        this.teamLead = teamLead;
    }

    public String getFio()
    {
        return fio;
    }

    public void setFio(String fio)
    {
        this.fio = fio;
    }

    public void setMaster() throws Exception
    {
        sql.openConnection();
        String query = (this.teamLead == 0) ? "insert into master (fio) values('" + this.fio + "')" : "insert into master(fio, teamLeads) values('" + this.fio + "', " +  this.teamLead + ")";
        sql.updateQuery(query);
        sql.closeConnection();
    }

    public static String getMaster() throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from master");
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("fio", result.getString("fio"));
            res.put("teamLead", result.getInt("teamLeads"));
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

    public static String getMasterById(String id) throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from master where id = " + id);
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("fio", result.getString("FIO"));
            res.put("teamLead", result.getInt("teamLeads"));
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

    public void updateMaster(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("update master set fio = '" + this.fio + "', teamLeads = " + this.teamLead + " where id = " + id);
        sql.closeConnection();
    }

    public static void deleteMaster(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from master where id = " + id);
        sql.closeConnection();
    }
}
