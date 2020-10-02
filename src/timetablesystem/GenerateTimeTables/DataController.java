package timetablesystem.GenerateTimeTables;




import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DataController {

    public static ArrayList<String> getTimeTableStudents(){
        String query="SELECT * FROM students_grp ORDER BY sgrp_id";
        ArrayList<String> timeSlots= new ArrayList<>();
        try {
            ResultSet resultSet = new DBSqlHandler().DbGet(query);
            while (resultSet.next()) {

                timeSlots.add((resultSet.getString(7)));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return timeSlots;
    }

    public static ArrayList<String> getTimeTableLecturers(){
        String query="SELECT * FROM lecturers ORDER BY name";
        ArrayList<String> timeSlots= new ArrayList<>();
        try {
            ResultSet resultSet = new DBSqlHandler().DbGet(query);
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
        String query="SELECT * FROM lecturers ORDER BY name";
        try {
            //String name, String empid, String department, String center, int levelid
            ResultSet resultSet = new DBSqlHandler().DbGet(query);
            while (resultSet.next()) {
                lecturerV2.add(new LecturerV2(resultSet.getString(2),resultSet.getString(3),resultSet.getString(5),resultSet.getString(6),Integer.parseInt(resultSet.getString(8))));
            }
        }catch (Exception e){
            System.out.println("getLecturerV2() "+e);
        }
        return lecturerV2;
    }


    public static int timeslotsCount(){
        String query="SELECT COUNT(*) FROM timeslot";
        int count=0;
        try {

            ResultSet resultSet = new DBSqlHandler().DbGet(query);

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
        String query="SELECT * FROM room";
        try {

            ResultSet resultSet = new DBSqlHandler().DbGet(query);

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
        String query="SELECT * FROM subject   ORDER BY subject";
        try {
            //SubjectsV2(String sid, int year, int semester, int lhours, int thours, int labhours, int ehours) {
            ResultSet resultSet = new DBSqlHandler().DbGet(query);
            int lhours,thours,labhours,ehours;
            String year,semester;
            while (resultSet.next()) {
                year =resultSet.getString(2);
                semester =resultSet.getString(3);
                lhours =Integer.parseInt(resultSet.getString(6));
                thours =Integer.parseInt(resultSet.getString(7));
                labhours =Integer.parseInt(resultSet.getString(8));
                ehours =Integer.parseInt(resultSet.getString(9));
                String tempid="Y"+year+".S"+semester+".";
                subjectsV2s.add(new SubjectsV2(resultSet.getString(5),tempid, year,semester,lhours,thours,labhours,ehours));
            }
        }catch (Exception e){
            System.out.println("getSubjectsV2() "+e);
        }
        return subjectsV2s;
    }



}
