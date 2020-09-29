package Controller.LocationPrefernceDAO;

import Model.Session;
import Model.StudentSubgroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionRoomDAO {

    public SessionRoomDAO() {
    }

    public void InsertData(String room_idroom,String idsessions){

        String insertQuery="UPDATE sessions room_idroom = "+room_idroom+"WHERE idsessions = "+idsessions;
        DBSqlHandler dbSqlHandler =new DBSqlHandler();
        dbSqlHandler.DbInsert(insertQuery);
        System.out.println("sessions updated");

    }

    public static ResultSet GetAllSessions(){
        String getDataQuery="select * from sessions";
        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        ResultSet resultSet=   dbSqlHandler.DbGet(getDataQuery);
        return resultSet;
    }

    public static ObservableList<Session> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<Session> sessionsList = FXCollections.observableArrayList();

        while (resultSet.next()){
            sessionsList.add(new Session(resultSet.getInt("idsessions"),resultSet.getInt("numberofstudents"),resultSet.getString("duration"),resultSet.getString("consecutive"),resultSet.getString("notavailble"),resultSet.getInt("tag_idtag"),resultSet.getInt("lecturer_idemployee"),resultSet.getInt("subjects_idsubjects"),resultSet.getInt("students_grp_idstudents_grp"),resultSet.getInt("room_idroom")));
            System.out.println(resultSet.getString("programme"));
        }

        return  sessionsList;
    }
}
