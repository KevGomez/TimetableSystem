package Controller.LocationPrefernceDAO;

import Model.Session;
import Model.SessionHasRoom;
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

        String insertQuery="UPDATE sessions SET room_idroom = "+room_idroom+"WHERE idsessions = "+idsessions;
        DBSqlHandler dbSqlHandler =new DBSqlHandler();
        dbSqlHandler.DbInsert(insertQuery);
        System.out.println("sessions updated");

            consecutive(idsessions,room_idroom);



    }

    public void consecutive(String idsession,String room_idroom){
//        String alocateconsecutivesession= "UPDATE sessions SET room_idroom = "+room_idroom+" , consecutive = "+idsession+
//                                         " WHERE students_grp_idstudents_grp = (SELECT students_grp_idstudents_grp FROM sessions WHERE idsessions = "+idsession+")" +
//                                         " AND subjects_idsubjects = (SELECT subjects_idsubjects FROM sessions WHERE idsessions = "+idsession+")" +
//                                         " AND tag_idtag = 2" ;

//        String alocateconsecutivesession= "UPDATE sessions SET room_idroom = "+room_idroom+" , consecutive = "+idsession+
//                " WHERE students_grp_idstudents_grp = (SELECT students_grp_idstudents_grp FROM sessions WHERE idsessions = "+idsession+")" +
//                " AND subjects_idsubjects = (SELECT subjects_idsubjects FROM sessions WHERE idsessions = "+idsession+")" +
//                " OR tag_idtag = 2 OR tag_idtag=1" ;

        String updateConsecutiveSessions= "UPDATE sessions SET room_idroom = "+room_idroom+
                                          "WHERE idsessions = (select consecutive from sessions where idsessions = "+idsession+")";

        DBSqlHandler dbSqlHandler =new DBSqlHandler();
        dbSqlHandler.DbInsert(updateConsecutiveSessions);
        System.out.println("consecutive updated");



    }

    public static ResultSet GetTagUseSessionID(String id){
        String SelectQuery="SELECT s.idsessions,r.idroom,r.roomName,t.name,sb.subject,sb.semester ,sb.year\n" +
                            "FROM  sessions s,room r,tag t,subject sb\n" +
                             "WHERE s.tag_idtag=t.idtag AND s.subjects_idsubjects = sb.idsubjects AND s.room_idroom =r.idroom AND s.idsessions ="+id;

        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        ResultSet resultSet=   dbSqlHandler.DbGet(SelectQuery);
        return resultSet;

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
            sessionsList.add(new Session(resultSet.getInt("idsessions"),resultSet.getInt("numberofstudents"),resultSet.getString("duration"),resultSet.getString("consecutive"),resultSet.getString("notavailble"),resultSet.getInt("tag_idtag"),resultSet.getInt("lecturer_idemployee"),resultSet.getInt("subjects_idsubjects"),resultSet.getInt("students_grp_idstudents_grp"),resultSet.getInt("room_idroom"), resultSet.getInt("porder")));
            System.out.println(resultSet.getString("idsessions"));
        }

        return  sessionsList;
    }

    public static ResultSet GetAllSessionsAndRooms(){
        String getDataQuery="SELECT sessions.idsessions,room.idroom,room.roomName,tag.name,subject.subject,subject.semester ,subject.year\n" +
                "FROM  sessions  \n" +
                "INNER JOIN tag ON sessions.tag_idtag=tag.idtag \n" +
                "INNER JOIN room ON sessions.room_idroom=room.idroom \n" +
                "INNER JOIN subject ON  sessions.subjects_idsubjects=subject.idsubjects ";

        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        ResultSet resultSet=   dbSqlHandler.DbGet(getDataQuery);
        return resultSet;
    }

    public static ObservableList<SessionHasRoom> getObservebleSessionHasRoomList(ResultSet resultSet) throws SQLException {
        ObservableList<SessionHasRoom> sessionsHasRoomsList = FXCollections.observableArrayList();

        while (resultSet.next()){
            sessionsHasRoomsList.add(new SessionHasRoom(resultSet.getInt("idsessions"),resultSet.getInt("idroom"),resultSet.getString("semester"),resultSet.getString("roomName"),resultSet.getString("name"),resultSet.getString("subject"),resultSet.getString("year") ));
            System.out.println(resultSet.getString("idsessions"));
        }

        return  sessionsHasRoomsList;
    }

    public static ObservableList<SessionHasRoom> getObservebleSessionHasRoomWithoutRoomList(ResultSet resultSet) throws SQLException {
        ObservableList<SessionHasRoom> sessionsHasRoomsList = FXCollections.observableArrayList();

        while (resultSet.next()){
            sessionsHasRoomsList.add(new SessionHasRoom(resultSet.getInt("idsessions"),0,resultSet.getString("semester"),null,resultSet.getString("name"),resultSet.getString("subject"),resultSet.getString("year") ));
            System.out.println(resultSet.getString("idsessions"));
        }

        return  sessionsHasRoomsList;
    }




    public String DeleteData(String room_idroom, String sessionID) {
        String insertQuery="UPDATE sessions SET room_idroom = NULL WHERE idsessions = "+sessionID;

        DBSqlHandler dbSqlHandler =new DBSqlHandler();
       String e= dbSqlHandler.DbInsert(insertQuery);
        System.out.println("room remove form session"+e);
        return  e;
    }

    public static ResultSet GetAllSessionsAndRoomsWithoutRoomID(){


        String getSessionWithoutRoom="SELECT sessions.idsessions,tag.name,subject.subject,subject.semester ,subject.year\n" +
                                     "FROM  sessions  \n" +
                                     "INNER JOIN tag ON sessions.tag_idtag=tag.idtag \n" +
                                     "INNER JOIN subject ON  sessions.subjects_idsubjects=subject.idsubjects\n";

        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        ResultSet resultSet=   dbSqlHandler.DbGet(getSessionWithoutRoom);
        return resultSet;
    }
}
