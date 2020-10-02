package Controller.LocationPrefernceDAO;

import Model.RoomHasLecture;
import Model.TagHasRooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturerRoomDAO {
    public LecturerRoomDAO(){

    }

    public void InsertData(String room_idroom,String lecturer_idemployee){

        String insertQuery="INSERT INTO room_has_lecturer (room_idroom,lecturer_idemployee ) VALUES( "+room_idroom+","+lecturer_idemployee+" )";
        DBSqlHandler dbSqlHandler =new DBSqlHandler();
        dbSqlHandler.DbInsert(insertQuery);
        System.out.println("data added to room_has_lecture ");

    }

    public static ResultSet GetAllLecturerAndRooms() {

        String getDataQuery = "SELECT l.idemployee, l.name, l.level, r.idroom, r.roomName, b.name\n" +
                "FROM lecturers l,room r,room_has_lecturer rl,buildings b\n" +
                "WHERE l.idemployee = rl.lecturer_idemployee AND\n" +
                " r.idroom = rl.room_idroom AND \n" +
                " b.idbuildings = r.buildings_idbuildings";

        DBSqlHandler dbSqlHandler=new DBSqlHandler();
        ResultSet resultSet= dbSqlHandler.DbGet(getDataQuery);
        System.out.println("Get all lecture and rooms");

       return resultSet;


    }

    public static ObservableList<RoomHasLecture> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<RoomHasLecture> lecturehasroomList = FXCollections.observableArrayList();

        while (resultSet.next()){
            lecturehasroomList.add(new RoomHasLecture(resultSet.getInt("idemployee"),resultSet.getString("name"),resultSet.getInt("level"),resultSet.getInt("idroom"),resultSet.getString("roomName"),resultSet.getString(6)));
        }

        return  lecturehasroomList;
    }

    public void  DeleteData(String room_idroom ,String lecturer_idemployee){
        String deleteQuery="DELETE FROM room_has_lecturer WHERE room_idroom = "+room_idroom+" AND lecturer_idemployee = "+lecturer_idemployee;
        DBSqlHandler  dbSqlHandler =new DBSqlHandler();

        dbSqlHandler.DbInsert(deleteQuery);
        System.out.println(deleteQuery);
    }


}
