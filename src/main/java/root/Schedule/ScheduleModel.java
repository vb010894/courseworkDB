package root.Schedule;

import org.json.JSONArray;
import org.json.JSONObject;
import root.core.SqlWork;

import java.sql.ResultSet;

public class ScheduleModel
{
    private static final SqlWork sql = new SqlWork();
    private String startFact;
    private String endFact;
    private String startPlan;
    private String endPlan;

    public String getStartFact() {
        return startFact;
    }

    public void setStartFact(String startFact) {
        this.startFact = startFact;
    }

    public String getEndFact() {
        return endFact;
    }

    public void setEndFact(String endFact) {
        this.endFact = endFact;
    }

    public String getStartPlan() {
        return startPlan;
    }

    public void setStartPlan(String startPlan) {
        this.startPlan = startPlan;
    }

    public String getEndPlan() {
        return endPlan;
    }

    public void setEndPlan(String endPlan) {
        this.endPlan = endPlan;
    }

    public void set() throws Exception
    {
        sql.openConnection();
        String query = "INSERT INTO building.schedule(startFact, endFact, planDate, endPlan) values (STR_TO_DATE('" + this.startFact  + "', '%d.%m.%Y'), STR_TO_DATE('" + this.endFact + "', '%d.%m.%Y'), STR_TO_DATE('" + this.startPlan + "', '%d.%m.%Y'), STR_TO_DATE('" + this.endPlan + "', '%d.%m.%Y'))";
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
        ResultSet result = sql.getResult("select * from building.schedule");
        while (result.next())
        {
            count = result.getRow();
            res = new JSONObject();
            res.put("id", result.getInt("id"));
            res.put("startFact", result.getString("startFact"));
            res.put("planDate", result.getString("planDate"));
            res.put("endPlan", result.getString("endPlan"));
            res.put("endFact", result.getString("endFact"));
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
            res.put("startFact", result.getDate("startFact"));
            res.put("planDate", result.getDate("planDate"));
            res.put("endPlan", result.getDate("endPlan"));
            res.put("endFact", result.getDate("endFact"));
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
        sql.updateQuery("update building.schedule set startFact = STR_TO_DATE('" + this.startFact  + "', '%d.%m.%Y'), endFact = STR_TO_DATE('" + this.endFact + "', '%d.%m.%Y'), planDate = STR_TO_DATE('" + this.startPlan + "', '%d.%m.%Y'), endPlan = STR_TO_DATE('" + this.endPlan + "', '%d.%m.%Y')  where id = " + id);
        sql.closeConnection();
    }

    public static void delete(String id) throws Exception
    {
        sql.openConnection();
        sql.updateQuery("delete from building.schedule where id = " + id);
        sql.closeConnection();
    }
}
