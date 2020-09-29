package Controller.LocationPrefernceDAO;

import Model.StudentSubgroup;
import Model.TagHasRooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubgroupRoomDAO {
    public  SubgroupRoomDAO(){

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

    public static ObservableList<StudentSubgroup> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<StudentSubgroup> subgroupList = FXCollections.observableArrayList();

        while (resultSet.next()){
            subgroupList.add(new StudentSubgroup(resultSet.getInt("idstudents_grp"),resultSet.getString("year_sem"),resultSet.getString("programme"),resultSet.getInt("grp_no"),resultSet.getInt("sgrp_no"),resultSet.getString("grp_id"),resultSet.getString("sgrp_id")));
            System.out.println(resultSet.getString("programme"));
        }

        return  subgroupList;
    }
}
