package root.Group;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;

import java.sql.ResultSet;

public class GroupModel
{
    private static final SqlWork sql = new SqlWork();

    private String shift;

    private int master;

    public int getMaster()
    {
        return master;
    }

    public void setMaster(int master)
    {
        this.master = master;
    }

    public String getShift()
    {
        return shift;
    }

    public void setShift(String shift)
    {
        this.shift = shift;
    }

    public void setGroup() throws Exception
    {
        sql.openConnection();
        String query = (this.master == 0) ? "INSERT INTO building.groups(shift) values ('" + this.shift + "')" : "INSERT INTO building.groups(shift, maste) values('" + this.shift + "', " +  this.master + ")";
        System.out.println(query);
        sql.updateQuery(query);
        sql.closeConnection();
    }

    public static String getGroup() throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from building.groups");
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("shift", result.getString("shift"));
            res.put("master", result.getInt("maste"));
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

    public static String getGroupById(String id) throws  Exception
    {
        JSONArray items = new JSONArray();
        JSONObject res;
        int count = 0;
        sql.openConnection();
        ResultSet result = sql.getResult("select * from building.groups where id = " + id);
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("shift", result.getString("shift"));
            res.put("master", result.getInt("maste"));
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

    public void updateGroup(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("update building.groups set shift = '" + this.shift + "', maste = " + this.master + " where id = " + id);
        sql.closeConnection();
    }

    public static void deleteGroup(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from building.groups where id = " + id);
        sql.closeConnection();
    }
}
