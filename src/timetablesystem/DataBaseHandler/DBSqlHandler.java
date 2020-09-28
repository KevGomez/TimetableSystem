package timetablesystem.DataBaseHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSqlHandler {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String url,user,pass;

    public  DBSqlHandler(){

        String connectionUrl = "jdbc:sqlserver://spmservercode4.database.windows.net:1433;database=SPM_TIMETABLE;user=spmcode4@spmservercode4;password=code4@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";

        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement st = con.createStatement();) {
            System.out.println("Connected to azure db");


        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
