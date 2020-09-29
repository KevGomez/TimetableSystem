package Controller.LocationPrefernceDAO;

import timetablesystem.DataBaseHandler.DBSqlHandler;

public class LecturerRoomDAO {
    public LecturerRoomDAO(){

    }

    public void InsertData(String room_idroom,String lecturer_idemployee){

        String insertQuery="INSERT INTO room_has_lecturer (room_idroom,lecturer_idemployee ) VALUES( "+room_idroom+","+lecturer_idemployee+" )";
        DBSqlHandler dbSqlHandler =new DBSqlHandler();
        dbSqlHandler.DbInsert(insertQuery);
        System.out.println("data added to room_has_lecture ");

    }
}
