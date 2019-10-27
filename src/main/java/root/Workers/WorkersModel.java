package root.Workers;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;

import java.sql.ResultSet;

public class WorkersModel
{
    private static final SqlWork sql = new SqlWork();
    private String fio;
    private String position;
    private int group;

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

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void set() throws Exception
    {
        sql.openConnection();
        String query = (this.group == 0) ? "INSERT INTO building.workeers(fio,position) values ('" + this.fio + "', '" + this.position + "')" : "INSERT INTO building.workeers(fio,position, workeers.group) values ('" + this.fio + "', '" + this.position + "', " + this.group + ")";
        System.out.println(query);
        sql.updateQuery(query);
        sql.closeConnection();
    }

    public static String get() throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from building.workeers");
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("fio", result.getString("fio"));
            res.put("position", result.getString("position"));
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

    public static String getById(String id) throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from building.workeers where id = " + id);
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("fio", result.getString("fio"));
            res.put("position", result.getString("position"));
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

    public void update(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("update building.workeers set fio = '" + this.fio + "', position = '" + this.position + "',  workeers.group = " + this.group + " where id = " + id);
        sql.closeConnection();
    }

    public static void delete(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from building.workeers where id = " + id);
        sql.closeConnection();
    }
}
