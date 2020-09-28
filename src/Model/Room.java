package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.DataBaseHandler.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    String RoomName;
    String RoomId;
    String RoomCapacity;
    String BuildingName;
    String NotReservedTime;

    public Room(String roomName, String roomId, String roomCapacity, String buildingName, String NotReservedTime) {
        this.RoomName = roomName;
        this.RoomId = roomId;
        this.RoomCapacity=roomCapacity;
        this.BuildingName=buildingName;
        this.NotReservedTime=NotReservedTime;

    }

    public Room( ) {

    }


    public String getRoomName() {
        return RoomName;
    }

    public String getRoomId() {
        return RoomId;
    }

    public String getRoomCapacity() {
        return RoomCapacity;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setRoomCapacity(String roomCapacity) {
        RoomCapacity = roomCapacity;
    }

    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public String getNotReservedTime() {
        return NotReservedTime;
    }

    public void setNotReservedTime(String notReservedTime) {
        NotReservedTime = notReservedTime;
    }

    public void CreateRoom(){
        String insertBuilding="INSERT INTO  room (RoomName,Capacity,BuildingName,Notreservedtime) VALUES ('"+this.RoomName+"',"+this.RoomCapacity+",'"+this.BuildingName+"','"+this.NotReservedTime+"')";
//        SQLConnection sqlConnection=new SQLConnection();
        DBHandler sqlConnection=new DBHandler();
        sqlConnection.DbInsert(insertBuilding);
    }

    public static ResultSet getAllData(){
        String selectBuilding="SELECT * FROM room ";
//      SQLConnection sqlConnection=new SQLConnection();
        DBHandler sqlConnection=new DBHandler();
        ResultSet getAllRoom=sqlConnection.DbGet(selectBuilding);

        return  getAllRoom;
    }

    public ResultSet getSelectedData(String keyword){
        String selectBuilding="SELECT * FROM room WHERE RoomName LIKE '%"+keyword+"%' OR BuildingName LIKE '%"+keyword+"'";
        System.out.println(selectBuilding);
        DBHandler sqlConnection=new DBHandler();
        ResultSet getSelectedRoom=sqlConnection.DbGet(selectBuilding);
        return  getSelectedRoom;
    }


    public ObservableList<Room> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<Room> RoomList = FXCollections.observableArrayList();
        while (resultSet.next()){
            RoomList.add(new Room(resultSet.getString("RoomName"),String.valueOf(resultSet.getInt("ID")),String.valueOf(resultSet.getInt("Capacity")),resultSet.getString("BuildingName"),resultSet.getString("Notreservedtime")));
            System.out.println("ID"+String.valueOf(resultSet.getInt("ID"))+" cap:"+String.valueOf(resultSet.getInt("Capacity")));
        }

        return  RoomList;
    }


    public static ObservableList<String> getStringObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<String> RoomList = FXCollections.observableArrayList();
        while (resultSet.next()){
            RoomList.add(resultSet.getString("RoomName")+""+resultSet.getString("BuildingName")+" ("+resultSet.getString("ID")+")");
        }

        return  RoomList;
    }

    public void DeleteData(String id){
        String deletequery="DELETE FROM room WHERE ID ="+id;
        DBHandler sqlConnection=new DBHandler();
        sqlConnection.DbInsert(deletequery);
    }

    public void UpdateData(String id,String RoomNamevalue,String RoomCapasity,String newBuilding,String newNotresrvedTime){
        String updateQuery="UPDATE room SET RoomName = '"+RoomNamevalue+"',Capacity = "+RoomCapasity+",Notreservedtime = '"+newNotresrvedTime+"',BuildingName ='"+newBuilding+"' WHERE ID ="+id;
//        SQLConnection sqlConnection=new SQLConnection();
        DBHandler sqlConnection=new DBHandler();
        sqlConnection.DbInsert(updateQuery);
    }
}
