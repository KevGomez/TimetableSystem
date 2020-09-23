package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import timetablesystem.Connections.SQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Building {
    private String BuildingName;
    private String Id;

    public Building(){

    }

    //set buiding name
    public Building(String buildingName, String id) {
        BuildingName = buildingName;
        Id = id;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public String getId() {
        return Id;
    }

    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }

    public void setId(String id) {
        Id = id;
    }

    public void CreateBuilding(){
        String insertBuilding="INSERT INTO  building (BuildingName) VALUES ('"+this.BuildingName+"')";
        SQLConnection sqlConnection=new SQLConnection();
        sqlConnection.InsertQuery(insertBuilding);
    }

    public ResultSet getAllData(){
        String selectBuilding="SELECT * FROM building ";
        SQLConnection sqlConnection=new SQLConnection();
        ResultSet getAllBuilding=sqlConnection.SelectQuery(selectBuilding);
        return  getAllBuilding;
    }


    public ResultSet getSelectedData(String keyword){
        String selectBuilding="SELECT * FROM building WHERE BuildingName LIKE '%"+keyword+"%'";
        SQLConnection sqlConnection=new SQLConnection();
        ResultSet getAllBuilding=sqlConnection.SelectQuery(selectBuilding);
        return  getAllBuilding;
    }

    public ObservableList<Building> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<Building> BuildingList = FXCollections.observableArrayList();
        while (resultSet.next()){
            BuildingList.add(new Building(resultSet.getString(1),Integer.toString(resultSet.getInt(2))));

        }

        return  BuildingList;
    }

    public ObservableList<String> getStringObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<String> RoomList = FXCollections.observableArrayList();
        while (resultSet.next()){
            RoomList.add(resultSet.getString("BuildingName"));
        }

        return  RoomList;
    }

    public void DeleteData(String id){
        String deletequery="DELETE FROM building WHERE ID ="+id;
        SQLConnection sqlConnection=new SQLConnection();
        sqlConnection.InsertQuery(deletequery);
    }


    public void UpdateData(String id,String value){
        String updateQuery="UPDATE building SET BuildingName = '"+value+"' WHERE ID ="+id;
        SQLConnection sqlConnection=new SQLConnection();
        sqlConnection.InsertQuery(updateQuery);
    }


 }
