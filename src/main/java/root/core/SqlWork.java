package root.core;

import java.sql.*;

public class SqlWork
{
    private static final String connectionUrl = "jdbc:mysql://localhost/building?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "siteUser";
    private static final String password = "1234098sam";

    private static Connection con;
    private static Statement stmt;

    public void updateQuery(String Query)
    {
        try
        {
            stmt.executeUpdate(Query);
        }
        catch (SQLException sql)
        {
            System.out.println();
            System.out.println("------ОШИБКА------");
            System.out.println("Сообщение - " + sql);
            System.out.println("StackTrace - " + sql.getStackTrace());
        }
    }

    public ResultSet getResult(String Query) {
        ResultSet result = null;
        try
        {
            result = stmt.executeQuery(Query);
        }
        catch (SQLException sql)
        {
            System.out.println();
            System.out.println("------ОШИБКА------");
            System.out.println("Сообщение - " + sql);
            System.out.println("StackTrace - " + sql.getStackTrace());
        }

        return result;
    }

    public void openConnection()
    {
        try
        {
            this.con = DriverManager.getConnection(connectionUrl, user, password);
            this.stmt  = con.createStatement();
        }
        catch (SQLException sql)
        {
            System.out.println();
            System.out.println("------ОШИБКА------");
            System.out.println("Сообщение - " + sql);
            System.out.println("StackTrace - " + sql.getStackTrace());
        }
    }

    public void closeConnection()
    {
        try
        {
            this.con.close();
        }
        catch (SQLException sql)
        {
            System.out.println();
            System.out.println("------ОШИБКА------");
            System.out.println("Сообщение - " + sql);
            System.out.println("StackTrace - " + sql.getStackTrace());
        }

    }
}

