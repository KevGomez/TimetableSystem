package timetablesystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import timetablesystem.Connections.SQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Subject {
    private  int ID;
    private  String Name;
    private  String Year;
    private  String Code;
    private  int Lecture;

    public Subject() {
    }

    public Subject(int ID, String name, String year, String code, int lecture) {
        this.ID = ID;
        Name = name;
        Year = year;
        Code = code;
        Lecture = lecture;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getYear() {
        return Year;
    }

    public String getCode() {
        return Code;
    }

    public int getLecture() {
        return Lecture;
    }

    public ResultSet getAllData(){
        String selectAllQuerry="SELECT * FROM subject";
        SQLConnection sqlConnection=new SQLConnection();
        ResultSet getAllBuilding=sqlConnection.SelectQuery(selectAllQuerry);
        return  getAllBuilding;
    }

    public ResultSet GroupBy(String group,String table,String uniq){
        String groupQuerry="SELECT COUNT("+uniq+"),"+group+" FROM "+table+" GROUP BY "+group;
        System.out.println(groupQuerry);
        SQLConnection sqlConnection=new SQLConnection();
        ResultSet getAllBuilding=sqlConnection.SelectQuery(groupQuerry);
        return  getAllBuilding;
    }

    public ObservableList<PieChart.Data> getPiCtartData(ResultSet resultSet) throws SQLException {
        ObservableList<PieChart.Data> lecture_groups= FXCollections.observableArrayList();
        while (resultSet.next()){
            lecture_groups.add(new PieChart.Data(resultSet.getString(2), resultSet.getInt(1)));

        }

        return lecture_groups;

    }
}
