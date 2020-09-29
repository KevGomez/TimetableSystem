package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import timetablesystem.DataBaseHandler.DBHandler;
import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Lecture {

    private  int idemployee;
    private  String name;
    private  int level_id;
    private  String notavaible;
    private  int faculty_idfaculty;
    private  int level;
    private String centre;
    private  String department;


    public Lecture() {
    }

    public Lecture(int idemployee, String name, int level_id, String notavaible, int faculty_idfaculty, int level, String centre, String department) {
        this.idemployee = idemployee;
        this.name = name;
        this.level_id = level_id;
        this.notavaible = notavaible;
        this.faculty_idfaculty = faculty_idfaculty;
        this.level = level;
        this.centre = centre;
        this.department = department;
    }

    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        this.idemployee = idemployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public String getNotavaible() {
        return notavaible;
    }

    public void setNotavaible(String notavaible) {
        this.notavaible = notavaible;
    }

    public int getFaculty_idfaculty() {
        return faculty_idfaculty;
    }

    public void setFaculty_idfaculty(int faculty_idfaculty) {
        this.faculty_idfaculty = faculty_idfaculty;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public static ResultSet getAllData(){
        String selectAllQuerry="SELECT * FROM lecturer";
        DBSqlHandler sqlConnection=new DBSqlHandler();
        ResultSet getAllBuilding=sqlConnection.DbGet(selectAllQuerry);
        return  getAllBuilding;
    }

    public ResultSet GroupBy(String group,String table,String uniq){
        String groupQuerry="SELECT COUNT("+uniq+"),"+group+" FROM "+table+" GROUP BY "+group;
        System.out.println(groupQuerry);
//        SQLConnection sqlConnection=new SQLConnection();
        DBSqlHandler sqlConnection=new DBSqlHandler();
        ResultSet getAllBuilding=sqlConnection.DbGet(groupQuerry);
        return  getAllBuilding;
    }

    public ObservableList<PieChart.Data> getPiCtartData(ResultSet resultSet) throws SQLException {
        ObservableList<PieChart.Data> lecture_groups= FXCollections.observableArrayList();
            while (resultSet.next()){
                lecture_groups.add(new PieChart.Data(resultSet.getString(2), resultSet.getInt(1)));

            }

            return lecture_groups;

    }


    public static ObservableList<Lecture> getObservebleList(ResultSet resultSet) throws SQLException {
        ObservableList<Lecture> LectureList = FXCollections.observableArrayList();
        while (resultSet.next()){
            LectureList.add(new Lecture(resultSet.getInt("idemployee"),resultSet.getString("name"),resultSet.getInt("level_id"),resultSet.getString("notavaible"),resultSet.getInt("faculty_idfaculty"),resultSet.getInt("level"),resultSet.getString("centre"),resultSet.getString("department")));

        }

        return  LectureList;
    }


    //toSring overide method

    @Override
    public String toString() {
        return this.getName();
    }
}
