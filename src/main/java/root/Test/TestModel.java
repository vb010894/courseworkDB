package root.Test;

import root.core.Annotation.SqlField;
import root.core.Annotation.SqlQuery;
import root.core.Enums.SqlQueryEnum;
import root.core.SqlWork;

public class TestModel
{

    @SqlField("id")
    public  int id;

    @SqlField("name")
    public  String name;

    @SqlField("planCount")
    public  int planCount;

    @SqlField("factCount")
    public  int factCount;

    @SqlField("cost")
    public  float cost;

    @SqlField("estimate")
    public  int estimate;

    public  int getId() {
        return this.id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return this.name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  int getPlanCount() {
        return this.planCount;
    }

    public  void setPlanCount(int planCount) {
        this.planCount = planCount;
    }

    public  int getFactCount() {
        return this.factCount;
    }

    public  void setFactCount(int factCount) {
        this.factCount = factCount;
    }

    public  float getCost() {
        return this.cost;
    }

    public  void setCost(float cost) {
        this.cost = cost;
    }

    public  int getEstimate() {
        return this.estimate;
    }

    public  void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    private static final SqlWork sql = new SqlWork();

    @SqlQuery(table = "materials", type = SqlQueryEnum.getter)
    public static String get() throws Exception
    {
        sql.openConnection();
        String result = SqlWork.result(null, "");
        sql.closeConnection();
        return result;
    }

    @SqlQuery(table = "materials", type = SqlQueryEnum.setter)
    public String set() throws Exception
    {
        sql.openConnection();
        String result = SqlWork.result(this, "");
        sql.closeConnection();
        return result;
    }

    @SqlQuery(table = "materials", type = SqlQueryEnum.updater)
    public String updater(String id) throws Exception
    {
        sql.openConnection();
        String result = SqlWork.result(this, id);
        sql.closeConnection();
        return result;
    }
}
