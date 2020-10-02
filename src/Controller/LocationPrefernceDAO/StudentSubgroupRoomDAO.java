package Controller.LocationPrefernceDAO;

import Model.StudentGroupHasRoom;
import Model.StudentSubgroup;
import Model.TagHasRooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSubgroupRoomDAO {


    public StudentSubgroupRoomDAO(){
    }

    public void InsertData(String room_idroom,String students_grp_idstudents_grp){

        String insertQuery="INSERT INTO room_has_students_grp (room_idroom ,students_grp_idstudents_grp ) VALUES( "+room_idroom+","+students_grp_idstudents_grp+" )";
        DBSqlHandler dbSqlHandler =new DBSqlHandler();
        dbSqlHandler.DbInsert(insertQuery);
        System.out.println("room_has_students_grp updated");

    }

    public static ResultSet GetAllSubGroup(){
        String getDataQuery="select * from students_grp";
        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        ResultSet resultSet=   dbSqlHandler.DbGet(getDataQuery);
        return resultSet;
    }

    public static  ResultSet GetAllSubGroupAndRoom(){
        String getDataQuery="SELECT s.idstudents_grp, s.year, s.pro, s.grp_id, s.sgrp_id, r.idroom,r.roomName,b.name " +
                             "FROM students_grp s,room r,buildings b,room_has_students_grp rs " +
                             "WHERE s.idstudents_grp = rs.students_grp_idstudents_grp AND " +
                             " r.idroom = rs.room_idroom AND " +
                             "r.buildings_idbuildings = b.idbuildings";
        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        ResultSet resultSet=  dbSqlHandler.DbGet(getDataQuery);
        return resultSet;
    }

    public static ObservableList<StudentSubgroup> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<StudentSubgroup> subgroupList = FXCollections.observableArrayList();

        while (resultSet.next()){
            subgroupList.add(new StudentSubgroup(resultSet.getInt("idstudents_grp"),resultSet.getString("year"),resultSet.getString("pro"),resultSet.getString("grp_no"),resultSet.getString("sgrp_no"),resultSet.getString("grp_id"),resultSet.getString("sgrp_id")));
            System.out.println(resultSet.getString("pro"));
        }

        return  subgroupList;
    }


    public static ObservableList<StudentGroupHasRoom> getObservebleStudentSubGroupAndRoomList(ResultSet resultSet) throws SQLException {
        ObservableList<StudentGroupHasRoom> subgroupHasRoomList = FXCollections.observableArrayList();

        while (resultSet.next()){
            subgroupHasRoomList.add(new StudentGroupHasRoom(resultSet.getInt("idstudents_grp"),resultSet.getString("pro"),resultSet.getString("year"),resultSet.getString("grp_id"),resultSet.getString("sgrp_id"),resultSet.getInt("idroom"),resultSet.getString("roomName"),resultSet.getString("name")));
            System.out.println(resultSet.getString("pro"));
        }

        return  subgroupHasRoomList;
    }

    public void DeleteData(String room_idroom, String students_grp_idstudents_grp) {
        String DeleteQuery="DELETE FROM room_has_students_grp WHERE room_idroom = "+room_idroom+" AND students_grp_idstudents_grp = "+students_grp_idstudents_grp;
        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        dbSqlHandler.DbInsert(DeleteQuery);
    }
}
