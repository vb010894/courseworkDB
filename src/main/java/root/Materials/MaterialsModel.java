package root.Materials;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;

import java.sql.ResultSet;

public class MaterialsModel
{
    private static final SqlWork sql = new SqlWork();
    private String name;
    private int planCount;
    private int factCount;
    private float cost;
    private int estimate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlanCount() {
        return planCount;
    }

    public void setPlanCount(int planCount) {
        this.planCount = planCount;
    }

    public int getFactCount() {
        return factCount;
    }

    public void setFactCount(int factCount) {
        this.factCount = factCount;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }


    public void set() throws Exception
    {
        sql.openConnection();
        String query = "INSERT INTO building.materials(name, planCount, factCount, cost, estimate) values ('" + this.name + "', " + this.planCount + ", " + this.factCount + ", " + this.cost + ", " + this.estimate + ")";
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
        ResultSet result = sql.getResult("select * from building.materials");
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("name", result.getString("name"));
            res.put("planCount", result.getInt("planCount"));
            res.put("factCount", result.getInt("factCount"));
            res.put("cost", result.getFloat("cost"));
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
        ResultSet result = sql.getResult("select * from building.materials where id = " + id);
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("name", result.getString("name"));
            res.put("planCount", result.getInt("planCount"));
            res.put("factCount", result.getInt("factCount"));
            res.put("cost", result.getFloat("cost"));
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
        sql.updateQuery("update building.materials set name = '" + this.name + "', planCount = " + this.planCount + ", factCount = " + this.factCount + ", cost = " + this.cost + ", estimate = " + this.estimate + " where id = " + id);
        sql.closeConnection();
    }

    public static void delete(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from building.materials where id = " + id);
        sql.closeConnection();
    }
}
