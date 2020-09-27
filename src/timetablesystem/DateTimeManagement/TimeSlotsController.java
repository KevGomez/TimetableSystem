package timetablesystem.DateTimeManagement;

import timetablesystem.DataBaseHandler.DBHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TimeSlotsController {


    public static String insertTimeSlots(String time){

        String query="INSERT INTO `timeslots`(`tid`, `tSlots`) VALUES (null,'"+time+"')";
        return  new DBHandler().DbInsert(query);
    }

    public static void deleteTimeSlots(){

        String query="DELETE FROM `timeslots`";
        new DBHandler().DbInsert(query);
    }

    public static ArrayList<TimeSlots> getTimeSlots(){
        String query="SELECT * FROM `timeslots` ORDER BY id";
        ArrayList<TimeSlots> timeSlots= new ArrayList<>();
        try {
            ResultSet resultSet = new DBHandler().DbGet(query);
            while (resultSet.next()) {

             timeSlots.add(new TimeSlots(resultSet.getString(2),Integer.parseInt(resultSet.getString(1))));
            }
        }catch (Exception e){
            System.out.println(e);
        }


        return timeSlots;
    }


}
