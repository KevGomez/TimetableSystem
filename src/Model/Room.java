package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    String RoomName;
    String idroom;
    String capacity;
    String buildings_idbuildings;
    String notreservedtime;

    public Room(String roomName, String roomId, String roomCapacity, String buildingName, String NotReservedTime) {
        this.RoomName = roomName;
        this.idroom = roomId;
        this.capacity=roomCapacity;
        this.buildings_idbuildings=buildingName;
        this.notreservedtime=NotReservedTime;

    }

    public Room( ) {

    }

    @Override
    public String toString() {
        return this.getRoomName();
    }



    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getIdroom() {
        return idroom;
    }

    public void setIdroom(String idroom) {
        this.idroom = idroom;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getBuildings_idbuildings() {
        return buildings_idbuildings;
    }

    public void setBuildings_idbuildings(String buildings_idbuildings) {
        this.buildings_idbuildings = buildings_idbuildings;
    }

    public String getNotreservedtime() {
        return notreservedtime;
    }

    public void setNotreservedtime(String notreservedtime) {
        this.notreservedtime = notreservedtime;
    }

    public void CreateRoom(){
        String insertBuilding="INSERT INTO  room (roomName,capacity,buildings_idbuildings,notreservedtime) VALUES ('"+this.RoomName+"','"+this.capacity+"',"+this.buildings_idbuildings+",'"+this.notreservedtime+"')";
//      SQLConnection sqlConnection=new SQLConnection();
        System.out.println(insertBuilding);
        DBSqlHandler sqlConnection=new DBSqlHandler();
        sqlConnection.DbInsert(insertBuilding);
    }

    public static ResultSet getAllData(){
        String selectBuilding="SELECT * FROM room ";
//      SQLConnection sqlConnection=new SQLConnection();
        DBSqlHandler sqlConnection=new DBSqlHandler();
        ResultSet getAllRoom=sqlConnection.DbGet(selectBuilding);

        return  getAllRoom;
    }

    public ResultSet getSelectedData(String keyword){
        String selectBuilding="SELECT * FROM room WHERE roomName LIKE '%"+keyword+"%' OR capacity LIKE '%"+keyword+"'";
        System.out.println(selectBuilding);
        DBSqlHandler sqlConnection=new DBSqlHandler();
        ResultSet getSelectedRoom=sqlConnection.DbGet(selectBuilding);
        return  getSelectedRoom;
    }


    public static ObservableList<Room> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<Room> RoomList = FXCollections.observableArrayList();
        while (resultSet.next()){
            RoomList.add(new Room(resultSet.getString("roomName"),String.valueOf(resultSet.getInt("idroom")),resultSet.getString("capacity"),String.valueOf(resultSet.getInt("buildings_idbuildings")),resultSet.getString("notreservedtime")));

//            System.out.println("ID"+String.valueOf(resultSet.getInt("ID"))+" cap:"+String.valueOf(resultSet.getInt("Capacity")));
        }

        return  RoomList;
    }


    public static ObservableList<String> getStringObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<String> RoomList = FXCollections.observableArrayList();
        while (resultSet.next()){
            RoomList.add(resultSet.getString("roomName")+""+resultSet.getString("buildings_idbuildings")+" ("+resultSet.getString("idroom")+")");
        }

        return  RoomList;
    }

    public void DeleteData(String id){
        String deletequery="DELETE FROM room WHERE idroom ="+id;
        DBSqlHandler sqlConnection=new DBSqlHandler();
        sqlConnection.DbInsert(deletequery);
    }

    public void UpdateData(String id,String RoomNamevalue,String RoomCapasity,String newBuilding,String newNotresrvedTime){
        String updateQuery="UPDATE room SET roomName = '"+RoomNamevalue+"',capacity = "+RoomCapasity+",notreservedtime = '"+newNotresrvedTime+"',buildings_idbuildings ='"+newBuilding+"' WHERE idroom ="+id;
//        SQLConnection sqlConnection=new SQLConnection();
        DBSqlHandler sqlConnection=new DBSqlHandler();
        sqlConnection.DbInsert(updateQuery);
    }
}
