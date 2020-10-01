package timetablesystem.DateTimeManagement;


import jdk.nashorn.internal.runtime.ECMAException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class DaysHandler {
    public static Boolean  monday,tuesday, wednesday, thursday, friday,saturday,sunday;
    public static int nDaysPerWeek=7,tempnDaysPerWeek=7;
    public static String sTime="08:00 AM",eTime="06:00 PM",lunch="12:00 PM",isthirtyMin="true";

    private String filePath = "./src/data/days.json";
    private File daysdat= new File(filePath);


    public DaysHandler(){
        try {

            if (daysdat.createNewFile()){
                System.out.println("File Create "+daysdat.getName());
                JSONObject writeJsonObject = new JSONObject();
                writeJsonObject.put("sunday",true);
                writeJsonObject.put("monday",true);
                writeJsonObject.put("tuesday",true);
                writeJsonObject.put("wednesday",true);
                writeJsonObject.put("thursday",true);
                writeJsonObject.put("friday",true);
                writeJsonObject.put("saturday",true);
                writeJsonObject.put("ndpWeek",7);
                writeJsonObject.put("startTime",sTime);
                writeJsonObject.put("endTime",eTime);
                writeJsonObject.put("lunch",lunch);
                writeJsonObject.put("isthirtyMin",isthirtyMin);
                FileWriter fileWriter= new FileWriter(filePath);
                fileWriter.write(writeJsonObject.toString());
                fileWriter.close();
            }
            calnDaysPerWeek(readFile());
            getData();
        }catch (Exception e){
            System.err.println("Constructor "+e);
        }

    }


    public DaysHandler getData(){
        try {
            JSONObject readJsonObject=readFile();
           sunday= (Boolean)readJsonObject.get("sunday");
            monday= (Boolean)readJsonObject.get("monday");
            tuesday= (Boolean)readJsonObject.get("tuesday");
            wednesday= (Boolean)readJsonObject.get("wednesday");
            thursday= (Boolean)readJsonObject.get("thursday");
            friday= (Boolean)readJsonObject.get("friday");
            saturday= (Boolean)readJsonObject.get("saturday");
            nDaysPerWeek=(int)readJsonObject.get("ndpWeek");
            sTime= (String)readJsonObject.get("startTime");
            eTime= (String)readJsonObject.get("endTime");
            lunch= (String)readJsonObject.get("lunch");
            isthirtyMin= (String) readJsonObject.get("isthirtyMin");

        }catch (Exception e){
            System.out.println("getData "+e);
        }

        return this;
    }


    public void setDays(String Day, Boolean value){
        try {
            System.out.println(nDaysPerWeek+" "+tempnDaysPerWeek);
           if (value){
                tempnDaysPerWeek++;
            }else {
                tempnDaysPerWeek--;
                if (tempnDaysPerWeek<nDaysPerWeek){
                    nDaysPerWeek--;
                }
            }
            System.out.println(nDaysPerWeek+" "+tempnDaysPerWeek);
            JSONObject writeJsonObject=readFile();
            writeJsonObject.put(Day,value);
            writeJsonObject.put("ndpWeek",nDaysPerWeek);
            FileWriter fileWriter= new FileWriter(filePath,false);
            fileWriter.write(writeJsonObject.toString());
            fileWriter.close();
        }catch (Exception e){
            System.err.println("Change Data "+e);
        }

    }


    public void setnDaysPerWeek(int days){
        try {
            if (days==0){
                days=nDaysPerWeek;
            }
            JSONObject writeJsonObject=readFile();
            writeJsonObject.put("ndpWeek",days);
            FileWriter fileWriter= new FileWriter(filePath,false);
            fileWriter.write(writeJsonObject.toString());
            fileWriter.close();
            nDaysPerWeek=days;
        }catch (Exception e){

        }

    }

    public void changeValue(String key ,String value){

        try {

            JSONObject writeJsonObject=readFile();
            writeJsonObject.put(key,value);
            FileWriter fileWriter= new FileWriter(filePath,false);
            fileWriter.write(writeJsonObject.toString());
            fileWriter.close();
        }catch (Exception e){

        }
    }
    public ArrayList<String> getWorkingDays(){
        ArrayList<String> days = new ArrayList<>();
        if (monday){
            days.add("Monday");
        }
        if (tuesday){
            days.add("Tuesday");
        }
        if (wednesday){
            days.add("Wednesday");
        }
        if (thursday){
            days.add("Thursday");
        }
        if (friday){
            days.add("Friday");
        }
        if (saturday){
            days.add("Saturday");
        }
        if (sunday){
            days.add("Sunday");
        }
        return days;
    }


    private JSONObject readFile(){
        JSONObject readJsonObject=null;
        try {

            Scanner scanner = new Scanner(daysdat);
            if(scanner.hasNextLine()){
                String data= scanner.nextLine();
                readJsonObject  = new JSONObject(data);
            }
            scanner.close();
            return readJsonObject;
        }catch (Exception e){
            System.out.println("read File"+e);
        }
       return null;
    }

    private void calnDaysPerWeek(JSONObject jsonObject){

        try {
            Iterator<String> iterator=jsonObject.keys();
            int c=0;
            while (iterator.hasNext()){
                String key= iterator.next();
                key=key.trim();
                if (key.equals("monday")||key.equals("tuesday")||key.equals("wednesday")||key.equals("thursday")||key.equals("friday")||key.equals("saturday")||key.equals("sunday")){
                    if ((Boolean)jsonObject.get(key)){
                        c++;
                    }


                }

            }
            tempnDaysPerWeek=c;

        }catch (Exception e){
            System.out.println("calnDaysPerWeek "+e);
        }

    }

}
