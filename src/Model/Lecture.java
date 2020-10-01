package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Lecture {

    private  int idemployee;
    private  String name;
    private String level_id;
    private  String notavaible;
    private String faculty_idfaculty;
    private String level;
    private String centre;
    private  String department;


    public Lecture() {
    }

    public Lecture(int idemployee, String name, String level_id, String notavaible, String faculty_idfaculty, String level, String centre, String department) {
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

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public String getNotavaible() {
        return notavaible;
    }

    public void setNotavaible(String notavaible) {
        this.notavaible = notavaible;
    }

    public String getFaculty_idfaculty() {
        return faculty_idfaculty;
    }

    public void setFaculty_idfaculty(String faculty_idfaculty) {
        this.faculty_idfaculty = faculty_idfaculty;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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
        String selectAllQuerry="SELECT * FROM lecturers";
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
            LectureList.add(new Lecture(resultSet.getInt("idemployee"),resultSet.getString("name"),resultSet.getString("lectureID"),resultSet.getString("building"),resultSet.getString("faculty"),resultSet.getString("level"),resultSet.getString("center"),resultSet.getString("department")));

        }

        return  LectureList;
    }


    //toSring overide method

    @Override
    public String toString() {
        return this.getName();
    }
}
