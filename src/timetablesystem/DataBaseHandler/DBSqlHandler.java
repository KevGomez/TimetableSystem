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


    public void createTables(){
        try {

//            st.executeUpdate(CreateTables.timeSolts) ;
//
//            st.executeUpdate(CreateTables.building) ;
//            st.executeUpdate(CreateTables.lecturers) ;
//            st.executeUpdate(CreateTables.room) ;
//            st.executeUpdate(CreateTables.subject) ;
//            st.executeUpdate(CreateTables.student) ;
            
            //Azure SQL Credate table

            st.executeUpdate(CreateTables.workingday);
            st.executeUpdate(CreateTables.timeslot);
            st.executeUpdate(CreateTables.lecturer);
            st.executeUpdate(CreateTables.subjects);
            st.executeUpdate(CreateTables.tag);
            st.executeUpdate(CreateTables.buildings);
            st.executeUpdate(CreateTables.students_grp);
            st.executeUpdate(CreateTables.room);
            st.executeUpdate(CreateTables.sessions);
            st.executeUpdate(CreateTables.workingday_has_timeslot);
            st.executeUpdate(CreateTables.room_notReserved);
            st.executeUpdate(CreateTables.room_has_tag);
            st.executeUpdate(CreateTables.room_has_lecturer);
            st.executeUpdate(CreateTables.room_has_students_grp);






            System.out.println("Table Created SuccessFully");
        }
        catch (Exception e) {
            System.out.println(e);

        }
    }

    public String DbInsert(String query){
        String r;
        try {
            st.executeUpdate(query) ;
            r="Success";
        }
        catch (Exception e) {
            r= e.toString();
        }
        return r;
    }

    public  ResultSet DbGet(String query){
        ResultSet r=null;

        try {
            r= st.executeQuery(query);

        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }


}
