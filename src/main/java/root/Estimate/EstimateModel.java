package root.Estimate;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;

import java.sql.ResultSet;

public class EstimateModel
{
    private static final SqlWork sql = new SqlWork();
    private String description;
    private float costAll;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCostAll() {
        return costAll;
    }

    public void setCostAll(float costAll) {
        this.costAll = costAll;
    }

    public void set() throws Exception
    {
        sql.openConnection();
        String query = "INSERT INTO building.estimate(description, costAll) values ('" + this.description + "', " + this.costAll + ")";
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
        ResultSet result = sql.getResult("select * from building.estimate");
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("description", result.getString("description"));
            res.put("costAll", result.getFloat("costAll"));
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
        ResultSet result = sql.getResult("select * from building.estimate where id = " + id);
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("description", result.getString("description"));
            res.put("costAll", result.getFloat("costAll"));
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
        sql.updateQuery("update building.estimate set description = '" + this.description + "' where id = " + id);
        sql.closeConnection();
    }

    public static void delete(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from building.estimate where id = " + id);
        sql.closeConnection();
    }
}
