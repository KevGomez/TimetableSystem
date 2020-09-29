package timetablesystem.GenerateTimeTables;

import timetablesystem.DataBaseHandler.DBHandler;


import java.sql.ResultSet;
import java.util.ArrayList;

public class DataController {

    public static ArrayList<String> getTimeTableStudents(){
        String query="SELECT * FROM `student` ORDER BY Name";
        ArrayList<String> timeSlots= new ArrayList<>();
        try {
            ResultSet resultSet = new DBHandler().DbGet(query);
            while (resultSet.next()) {

                timeSlots.add((resultSet.getString(2)));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return timeSlots;
    }

    public static ArrayList<String> getTimeTableLecturers(){
        String query="SELECT * FROM `lecturer` ORDER BY name";
        ArrayList<String> timeSlots= new ArrayList<>();
        try {
            ResultSet resultSet = new DBHandler().DbGet(query);
            while (resultSet.next()) {

                timeSlots.add((resultSet.getString(2)));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return timeSlots;
    }





}
