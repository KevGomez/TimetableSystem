package Controller.LocationPrefernceDAO;

import Model.Building;
import Model.TagData;
import Model.TagHasRooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaghasLocationDAO {

    public  TaghasLocationDAO(){

    }



    public void InsertData(String roomid,String tagid){

        String insertQuery="INSERT INTO room_has_tag (room_idroom,tag_idtag ) VALUES( "+roomid+","+tagid+" )";
        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        dbSqlHandler.DbInsert(insertQuery);
        System.out.println("data adde to room_has_tag ");

    }

    public void DeleteData(String roomid,String tagid){
        String deleteQuery="DELETE FROM room_has_tag WHERE room_idroom = "+roomid+" AND  tag_idtag = "+tagid;

        DBSqlHandler  dbSqlHandler =new DBSqlHandler();

        dbSqlHandler.DbInsert(deleteQuery);
        System.out.println(deleteQuery);


    }

    public static ResultSet GetAllRoomsAndTags(){
        String getDataQuery="select r.roomName ,r.idroom ,t.name ,t.idtag  from room r,room_has_tag tr,tag t where r.idroom = tr.room_idroom AND t.idtag = tr.tag_idtag";
        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        ResultSet resultSet=   dbSqlHandler.DbGet(getDataQuery);
        return resultSet;
    }

    public static ResultSet GetAllTags(){
        String getDataQuery="select * from tag";
        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
        ResultSet resultSet=   dbSqlHandler.DbGet(getDataQuery);
        return resultSet;
    }

//    public  static ResultSet GetSelectedRoomAndTags(String keyword){
//        String getDataQuery="select r.roomName ,r.idroom ,t.tag ,t.idtag  from room r,room_has_tag tr,tag t where r.idroom = tr.room_idroom AND t.idtag = tr.tag_idtag";
//        DBSqlHandler  dbSqlHandler =new DBSqlHandler();
//        ResultSet resultSet=   dbSqlHandler.DbGet(getDataQuery);
//        return resultSet;
//
//    }





    public static ObservableList<TagHasRooms> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<TagHasRooms> taghasroomList = FXCollections.observableArrayList();

        while (resultSet.next()){
            taghasroomList.add(new TagHasRooms(resultSet.getInt("idroom"),resultSet.getInt("idtag"),resultSet.getString("roomName"),resultSet.getString("name")));
//            System.out.println(resultSet.getString("tag"));
        }

        return  taghasroomList;
    }

    public static ObservableList<TagData> getObservebleTagList(ResultSet resultSet) throws SQLException {
        ObservableList<TagData> taghasroomList = FXCollections.observableArrayList();

        while (resultSet.next()){
            taghasroomList.add(new TagData(resultSet.getInt("idtag"),resultSet.getString("name")));
            System.out.println(resultSet.getString("name"));
        }

        return  taghasroomList;
    }


}
