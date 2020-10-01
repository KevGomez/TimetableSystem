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


    public static ArrayList<LecturerV2> getLecturerV2(){
        ArrayList<LecturerV2> lecturerV2 = new ArrayList<>();
        String query="SELECT * FROM `lecturer` ORDER BY name";
        try {
            //String name, String empid, String department, String center, int levelid
            ResultSet resultSet = new DBHandler().DbGet(query);
            while (resultSet.next()) {
                lecturerV2.add(new LecturerV2(resultSet.getString(2),resultSet.getString(1),resultSet.getString(4),resultSet.getString(5),Integer.parseInt(resultSet.getString(7))));
            }
        }catch (Exception e){
            System.out.println("getLecturerV2() "+e);
        }
        return lecturerV2;
    }


    public static int timeslotsCount(){
        String query="SELECT COUNT(*) FROM `timeslots`e";
        int count=0;
        try {

            ResultSet resultSet = new DBHandler().DbGet(query);

            if (resultSet.next()) {
                count=Integer.parseInt(resultSet.getString(1));
            }
        }catch (Exception e){
            System.out.println("getSubjectsV2() "+e);
        }
        return count;
    }


    public static ArrayList<LocationV2> getLoactionV2(){
        ArrayList<LocationV2> locationV2s= new ArrayList<>();
        String query="SELECT * FROM `room`";
        try {

            ResultSet resultSet = new DBHandler().DbGet(query);

            while (resultSet.next()) {
                locationV2s.add(new LocationV2(resultSet.getString(2)));
            }
        }catch (Exception e){
            System.out.println("getLoactionV2() "+e);
        }
        return locationV2s;
    }


    public static ArrayList<SubjectsV2> getSubjectsV2(){
        ArrayList<SubjectsV2> subjectsV2s = new ArrayList<>();
        String query="SELECT * FROM `subjects`   ORDER BY name";
        try {
            //SubjectsV2(String sid, int year, int semester, int lhours, int thours, int labhours, int ehours) {
            ResultSet resultSet = new DBHandler().DbGet(query);
            int year,semester,lhours,thours,labhours,ehours;
            while (resultSet.next()) {
                year =Integer.parseInt(resultSet.getString(3));
                semester =Integer.parseInt(resultSet.getString(4));
                lhours =Integer.parseInt(resultSet.getString(5));
                thours =Integer.parseInt(resultSet.getString(6));
                labhours =Integer.parseInt(resultSet.getString(7));
                ehours =Integer.parseInt(resultSet.getString(8));
                String tempid="Y"+year+"S"+semester;
                subjectsV2s.add(new SubjectsV2(resultSet.getString(1),tempid, year,semester,lhours,thours,labhours,ehours));
            }
        }catch (Exception e){
            System.out.println("getSubjectsV2() "+e);
        }
        return subjectsV2s;
    }



}
