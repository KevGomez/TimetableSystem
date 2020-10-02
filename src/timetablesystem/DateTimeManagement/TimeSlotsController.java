package timetablesystem.DateTimeManagement;



import timetablesystem.DataBaseHandler.DBSqlHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TimeSlotsController {


    public static String insertTimeSlots(String time){

        String query="INSERT INTO timeslot( timeslot) VALUES ('"+time+"')";
        return  new DBSqlHandler().DbInsert(query);
    }

    public static void deleteTimeSlots(){

        String query="DELETE FROM timeslot";
        new DBSqlHandler().DbInsert(query);
    }

    public static ArrayList<TimeSlots> getTimeSlots(){
        String query="SELECT * FROM timeslot ORDER BY idtimeslot";
        ArrayList<TimeSlots> timeSlots= new ArrayList<>();
        try {
            ResultSet resultSet = new DBSqlHandler().DbGet(query);
            while (resultSet.next()) {

             timeSlots.add(new TimeSlots(resultSet.getString(2),Integer.parseInt(resultSet.getString(1))));
            }
        }catch (Exception e){
            System.out.println(e);
        }


        return timeSlots;
    }


}
