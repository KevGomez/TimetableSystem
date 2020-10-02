package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetablesystem.DataBaseHandler.DBHandler;
import timetablesystem.DataBaseHandler.DBSqlHandler;

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

    @Override
    public String toString() {
        return this.getBuildingName();
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
        String insertBuilding="INSERT INTO  buildings (name) VALUES ('"+this.BuildingName+"')";
        DBSqlHandler sqlConnection=new DBSqlHandler();
        sqlConnection.DbInsert(insertBuilding);
    }

    public static ResultSet getAllData(){
        String selectBuilding="SELECT * FROM buildings ";
//        SQLConnection sqlConnection=new SQLConnection();
        DBSqlHandler sqlConnection=new DBSqlHandler();
        ResultSet getAllBuilding=sqlConnection.DbGet(selectBuilding);
        return  getAllBuilding;
    }


    public ResultSet getSelectedData(String keyword){
        String selectBuilding="SELECT * FROM buildings WHERE name LIKE '%"+keyword+"%'";
//        SQLConnection sqlConnection=new SQLConnection();
        DBSqlHandler sqlConnection=new DBSqlHandler();
        ResultSet getAllBuilding=sqlConnection.DbGet(selectBuilding);
        return  getAllBuilding;
    }

    public ResultSet getSelectedDataUseID(String keyword){
        String selectBuilding="SELECT * FROM buildings WHERE idbuildings ="+keyword;
//        SQLConnection sqlConnection=new SQLConnection();
        DBSqlHandler sqlConnection=new DBSqlHandler();
        ResultSet getAllBuilding=sqlConnection.DbGet(selectBuilding);
        return  getAllBuilding;
    }



    public ObservableList<Building> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<Building> BuildingList = FXCollections.observableArrayList();
        while (resultSet.next()){
            BuildingList.add(new Building(resultSet.getString("name"),Integer.toString(resultSet.getInt("idbuildings"))));

        }

        return  BuildingList;
    }

    public static ObservableList<String> getStringObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<String> RoomList = FXCollections.observableArrayList();
        while (resultSet.next()){
            RoomList.add(resultSet.getString("name"));
        }

        return  RoomList;
    }

    public void DeleteData(String id){
        String deletequery="DELETE FROM buildings WHERE idbuildings = "+id;
//        SQLConnection sqlConnection=new SQLConnection();
        DBSqlHandler sqlConnection=new DBSqlHandler();
        String val= sqlConnection.DbInsert(deletequery);
        System.out.println("Delete query execute"+val);
    }


    public void UpdateData(String id,String value){
        String updateQuery="UPDATE buildings SET name = '"+value+"' WHERE idbuildings ="+id;
//        SQLConnection sqlConnection=new SQLConnection();
        DBSqlHandler sqlConnection=new DBSqlHandler();
        sqlConnection.DbInsert(updateQuery);
    }


 }
