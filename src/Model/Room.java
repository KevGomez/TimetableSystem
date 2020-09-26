package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.Connections.SQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    String RoomName;
    String RoomId;
    String RoomCapacity;
    String BuildingName;

    public Room(String roomName, String roomId, String roomCapacity, String buildingName) {
        this.RoomName = roomName;
        this.RoomId = roomId;
        this.RoomCapacity=roomCapacity;
        this.BuildingName=buildingName;


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

    public void CreateRoom(){
        String insertBuilding="INSERT INTO  room (RoomName,Capacity,BuildingName) VALUES ('"+this.RoomName+"',"+this.RoomCapacity+",'"+this.BuildingName+"')";
        SQLConnection sqlConnection=new SQLConnection();
        sqlConnection.InsertQuery(insertBuilding);
    }

    public ResultSet getAllData(){
        String selectBuilding="SELECT * FROM room ";
        SQLConnection sqlConnection=new SQLConnection();
        ResultSet getAllRoom=sqlConnection.SelectQuery(selectBuilding);
        return  getAllRoom;
    }

    public ResultSet getSelectedData(String keyword){
        String selectBuilding="SELECT * FROM room WHERE RoomName LIKE '%"+keyword+"%' OR BuildingName LIKE '%"+keyword+"'";
        System.out.println(selectBuilding);
        SQLConnection sqlConnection=new SQLConnection();
        ResultSet getSelectedRoom=sqlConnection.SelectQuery(selectBuilding);
        return  getSelectedRoom;
    }


    public ObservableList<Room> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<Room> RoomList = FXCollections.observableArrayList();
        while (resultSet.next()){
            RoomList.add(new Room(resultSet.getString("RoomName"),String.valueOf(resultSet.getInt("ID")),String.valueOf(resultSet.getInt("Capacity")),resultSet.getString("BuildingName")));
            System.out.println("ID"+String.valueOf(resultSet.getInt("ID"))+" cap:"+String.valueOf(resultSet.getInt("Capacity")));
        }

        return  RoomList;
    }



    public void DeleteData(String id){
        String deletequery="DELETE FROM room WHERE ID ="+id;
        SQLConnection sqlConnection=new SQLConnection();
        sqlConnection.InsertQuery(deletequery);
    }

    public void UpdateData(String id,String RoomNamevalue,String RoomCapasity,String newBuilding){
        String updateQuery="UPDATE room SET RoomName = '"+RoomNamevalue+"',Capacity = "+RoomCapasity+",BuildingName ='"+newBuilding+"' WHERE ID ="+id;
        SQLConnection sqlConnection=new SQLConnection();
        sqlConnection.InsertQuery(updateQuery);
    }
}
