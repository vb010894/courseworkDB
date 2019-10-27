package root.Engeneers;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;

import java.sql.ResultSet;

public class EngeneersModel
{
    private static final SqlWork sql = new SqlWork();
    private String fio;
    private String position;
    private String office;
    private int  group;

    public void setGroup(int group) {
        this.group = group;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setEngeneer() throws Exception
    {
        sql.openConnection();
        String query = (this.group == 0) ? "INSERT INTO building.engeneers(fio,position,office) values ('" + this.fio + "', '" + this.position + "', '" + this.office + "')" : "INSERT INTO building.engeneers(fio,position,office, group) values ('" + this.fio + "', '" + this.position + "', '" + this.office + "', " + this.group + ")";
        System.out.println(query);
        sql.updateQuery(query);
        sql.closeConnection();
    }

    public static String getEngeneer() throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from building.engeneers");
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("fio", result.getString("fio"));
            res.put("position", result.getString("position"));
            res.put("office", result.getString("office"));
            res.put("group", result.getInt("group"));
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

    public static String getEngeneerById(String id) throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from building.engeneers where id = " + id);
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("fio", result.getString("fio"));
            res.put("position", result.getString("position"));
            res.put("office", result.getString("office"));
            res.put("group", result.getInt("group"));
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

    public void updateEngeneer(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("update building.engeneers set fio = '" + this.fio + "', position = '" + this.position + "', office = '" + this.office + "', engeneers.group = " + this.group + " where id = " + id);
        sql.closeConnection();
    }

    public static void deleteEngeneer(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from building.engeneers where id = " + id);
        sql.closeConnection();
    }
}
