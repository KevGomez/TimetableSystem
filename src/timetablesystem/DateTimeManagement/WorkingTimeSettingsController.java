package timetablesystem.DateTimeManagement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import timetablesystem.DataBaseHandler.DBHandler;


public class WorkingTimeSettingsController implements Initializable {
    @FXML
    private CheckBox monday;
    @FXML
    private CheckBox thursday;
    @FXML
    private CheckBox tuesday;
    @FXML
    private CheckBox friday;
    @FXML
    private CheckBox wednesday;
    @FXML
    private CheckBox saturday;
    @FXML
    private CheckBox sunday;
    @FXML
    private ChoiceBox<String> nDaysPerWeek;


    @FXML
    private ChoiceBox<String> hStart;
    @FXML
    private ChoiceBox<String> mStart;
    @FXML
    private ChoiceBox<String> sAmPm;
    @FXML
    private ChoiceBox<String> hEnd;
    @FXML
    private ChoiceBox<String> mEnd;
    @FXML
    private ChoiceBox<String> eAmPm;
    @FXML
    private ChoiceBox<String> hLunch;
    @FXML
    private ChoiceBox<String> mLunch;
    @FXML
    private ChoiceBox<String> lAmPm;
    @FXML
    private Label from;
    @FXML
    private ChoiceBox<String> duration;
    @FXML
    private GridPane timeSoltsGrid;


    @FXML
    private Label WorkingTimeError;
    @FXML
    private AnchorPane dtmanagement;


/*
*
*  12 am 1 min equal to 00:01
*
* */

    DaysHandler daysHandler;



    DBHandler dbHandler;
    ArrayList<TimeSlots> timeSlots;


    int tSlotX=0,tSlotY=0;
    String hours[]= new String[12],minutes[]= {"00","30"}, amPm[]= {"AM","PM"};
    String sTime,eTime,lTime,timeSlotTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        dbHandler = new DBHandler();

       // dbHandler.createTables(); // create tables

     //   dbHandler.createTables(); // create tables


        timeSlots=TimeSlotsController.getTimeSlots();





        VBox.setVgrow(dtmanagement, Priority.ALWAYS);

        daysHandler = new DaysHandler();
        daysHandler.getData();
        monday.setSelected(daysHandler.monday);
        tuesday.setSelected(daysHandler.tuesday);
        wednesday.setSelected(daysHandler.wednesday);
        thursday.setSelected(daysHandler.thursday);
        friday.setSelected(daysHandler.friday);
        saturday.setSelected(daysHandler.saturday);
        sunday.setSelected(daysHandler.sunday);




        for (int i=0; i<12;i++){

            if (i<12){
                hours[i]=String.valueOf((i+1));
            }

            if (i<daysHandler.tempnDaysPerWeek){
                nDaysPerWeek.getItems().add(String.valueOf(i+1));
            }

        }




      //  ObservableList<String> nDaysPerWeekValues = FXCollections.observableArrayList();
        ObservableList<String> observerHours = FXCollections.observableArrayList(hours);
        ObservableList<String> observerMinutes= FXCollections.observableArrayList(minutes);
        ObservableList<String> observerAMPM= FXCollections.observableArrayList(amPm);

        hStart.setItems(observerHours);
        hEnd.setItems(observerHours);
        hLunch.setItems(observerHours);


        mStart.setItems(observerMinutes);
        mEnd.setItems(observerMinutes);
        mLunch.setItems(observerMinutes);

        sAmPm.setItems(observerAMPM);
        eAmPm.setItems(observerAMPM);
        lAmPm.setItems(observerAMPM);






            sTime=daysHandler.sTime;
            eTime=daysHandler.eTime;
            lTime=daysHandler.lunch;
            timeSlotTime=daysHandler.sTime;




        String temStime[]=sliceTime(sTime);
        hStart.getSelectionModel().select((Integer.parseInt(temStime[0])-1));
        if (Integer.parseInt(temStime[1])==0){
            mStart.getSelectionModel().select(0);
        }else{
            mStart.getSelectionModel().select(1);
        }

        if (temStime[2].equals("AM")){
            sAmPm.getSelectionModel().select(0);
        }else{
            sAmPm.getSelectionModel().select(1);
        }





        String temEtime[]=sliceTime(eTime);
        hEnd.getSelectionModel().select((Integer.parseInt(temEtime[0])-1));
        if (Integer.parseInt(temStime[1])==0){
            mEnd.getSelectionModel().select(0);
        }else{
            mEnd.getSelectionModel().select(1);
        }
        if (temEtime[2].equals("AM")){
            eAmPm.getSelectionModel().select(0);
        }else{
            eAmPm.getSelectionModel().select(1);
        }






        String temLtime[]=sliceTime(lTime);
        hLunch.getSelectionModel().select((Integer.parseInt(temLtime[0])-1));
        if (Integer.parseInt(temStime[1])==0){
            mLunch.getSelectionModel().select(0);
        }else{
            mLunch.getSelectionModel().select(1);
        }
        if (temLtime[2].equals("AM")){
            lAmPm.getSelectionModel().select(0);
        }else{
            lAmPm.getSelectionModel().select(1);
        }


        from.setText(sTime);
        duration.getItems().add("30 Min");
        duration.getItems().add("1 Hour");
        if (DaysHandler.isthirtyMin.equals("true")){
            duration.getSelectionModel().select(0);
        }else{
            duration.getSelectionModel().select(1);
        }




        for(int i=0;i<timeSlots.size();i++){
            addSlot(timeSlots.get(i).getTime());
            if (i==(timeSlots.size()-1)){
                timeSlotTime=timeSlots.get(i).getTime().split("to")[1];
                from.setText(timeSlotTime);
            }
        }


        //nDaysPerWeek.setItems(nDaysPerWeekValues);



        nDaysPerWeek.getSelectionModel().select(daysHandler.nDaysPerWeek-1);

        nDaysPerWeek.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

               daysHandler.setnDaysPerWeek(((int)number2+1));
            }
        });




        //--------------------------------------------------------------------------------------------------------------
        hStart.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String temStime[]=sliceTime(sTime);

                String time= ((int)newValue+1)+":"+temStime[1]+" "+temStime[2];
                sTime=time;
                timeSlotTime=time;
                daysHandler.changeValue("startTime",sTime);
                from.setText(sTime);

                clearTimeSlots();

            }
        });
        mStart.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String temStime[]=sliceTime(sTime);
                String time= temStime[0]+":"+minutes[((int)newValue)]+" "+temStime[2];
                sTime=time;
                timeSlotTime=time;
                daysHandler.changeValue("startTime",sTime);
                from.setText(sTime);

                clearTimeSlots();

            }
        });
        sAmPm.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String temStime[]=sliceTime(sTime);
                String time= temStime[0]+":"+temStime[1]+" "+amPm[((int)newValue)];
                sTime=time;
                timeSlotTime=time;
                daysHandler.changeValue("startTime",sTime);
                from.setText(sTime);

                clearTimeSlots();

            }
        });





        //--------------------------------------------------------------------------------------------------------------
        hEnd.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                String temp[]=sliceTime(eTime);
                String time=((int)newValue+1)+":"+temp[1]+" "+temp[2];
                if (isTimesEquals(sTime,time)){
                    eTime=time;
                    daysHandler.changeValue("endTime",eTime);
                    showError("");
                    clearTimeSlots();
                }else{
                    showError("Invalid End Time ");
                }


            }
        });
        mEnd.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                String temp[]=sliceTime(eTime);
                String time=temp[0]+":"+minutes[((int)newValue)]+" "+temp[2];
                if (isTimesEquals(sTime,time)){
                    eTime=time;
                    daysHandler.changeValue("endTime",eTime);
                    showError("");
                    clearTimeSlots();
                }else{
                    showError("Invalid End Time ");
                }
            }
        });
        eAmPm.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                String temp[]=sliceTime(eTime);
                String time=temp[0]+":"+temp[1]+" "+amPm[((int)newValue)];
                if (isTimesEquals(sTime,time)){
                    eTime=time;
                    daysHandler.changeValue("endTime",eTime);
                    showError("");
                    clearTimeSlots();
                }else{
                    showError("Invalid End Time ");
                }
            }
        });





        //--------------------------------------------------------------------------------------------------------------
        hLunch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
             String temp[]= sliceTime(lTime);
             String  time=((int)newValue+1)+":"+temp[1]+" "+temp[2];

                if (isTimesEquals(sTime,time)){
                    if (isTimesEquals(time,eTime)){
                        showError("");
                        lTime=time;
                        daysHandler.changeValue("lunch",lTime);
                    }else{
                        showError("Invalid Lunch Time");
                    }
                }else{
                    showError("Invalid Lunch Time");
                }

            }
        });
        mLunch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String temp[]= sliceTime(lTime);
                String  time=temp[0]+":"+minutes[((int)newValue)]+" "+temp[2];

                if (isTimesEquals(sTime,time)){
                    if (isTimesEquals(time,eTime)){
                        showError("");
                        lTime=time;
                        daysHandler.changeValue("lunch",lTime);
                    }else{
                        showError("Invalid Lunch Time");
                    }
                }else{
                    showError("Invalid Lunch Time");
                }

            }

        });
        lAmPm.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String temp[]= sliceTime(lTime);
                String  time=temp[0]+":"+temp[1]+" "+amPm[((int)newValue)];

                if (isTimesEquals(sTime,time)){
                    if (isTimesEquals(time,eTime)){
                        showError("");
                        lTime=time;
                        daysHandler.changeValue("lunch",lTime);
                    }else{
                        showError("Invalid Lunch Time");
                    }
                }else{
                    showError("Invalid Lunch Time");
                }

            }

        });




        duration.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if ((int)newValue==0){
                    daysHandler.changeValue("isthirtyMin","true");
                }else{
                    daysHandler.changeValue("isthirtyMin","false");
                }
                clearTimeSlots();
            }
        });


    }

    @FXML
    public void changeDay(MouseEvent mouseEvent) {
        Control control =((Control)mouseEvent.getSource());
        String id=control.getId();
        CheckBox checkBox =(CheckBox)control;

        daysHandler.setDays(id,checkBox.isSelected());
        nDaysPerWeek.getItems().clear();

        for (int i=0;i<daysHandler.tempnDaysPerWeek;i++){
            nDaysPerWeek.getItems().add(String.valueOf(i+1));
        }

        nDaysPerWeek.getSelectionModel().select(daysHandler.nDaysPerWeek-1);

    }

    @FXML
    public void changeNDayPerWeek(MouseEvent mouseEvent) {






    }




    @FXML
    public void clearGrid(MouseEvent mouseEvent){
        clearTimeSlots();
    }



    @FXML
    public void addNewTimeSlot(MouseEvent mouseEvent) {

        LocalTime start = LocalTime.parse(convert12to24(sTime));
        LocalTime end = LocalTime.parse(convert12to24(eTime));
        int hour=start.getHour(),min=start.getMinute();
        try {
            String prev=sTime;
            while(hour< end.getHour()||min<end.getMinute()){


            String slot=duration.getSelectionModel().getSelectedItem();

            String time="";

            if (slot.equals("30 Min")){
                min+=30;
                if (min==60){
                    hour++;
                    min=0;
                    time=hour+":00";;

                }else{
                    time=hour+":"+min;
                }


            }else if (slot.equals("1 Hour")){
                    hour++;
                time=hour+":"+min;
            }
            time=convert24to12(time);
            addSlot(prev+" to "+time );
            prev=time;
          }
        }catch (Exception e){
            System.out.println(e.toString());
        }


    }

    private void addSlotsError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Rest Time is not enough to crate new time slot");
        alert.showAndWait().ifPresent(rs->{
           
        });
    }

    private void addSlot(String time){
        TimeSlotsController.insertTimeSlots(time);
        Label label = new Label();
        label.setText(time);
        label.setMinHeight(10);
        timeSoltsGrid.add(label,tSlotX,tSlotY);
        tSlotX++;
        if (tSlotX==6){
            tSlotY++;
            tSlotX=0;
        }


    }


    private void clearTimeSlots(){
        timeSoltsGrid.getChildren().clear();
        TimeSlotsController.deleteTimeSlots();
        tSlotX=0;tSlotY=0;
    }


    private Boolean isTimesEquals(String s1,String s2 ){

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
            //s1<s2


        try {
            LocalTime start = LocalTime.parse(convert12to24(s1));
            LocalTime end = LocalTime.parse(convert12to24(s2));
            if (start.isBefore(end)) {
                System.out.println("Succ");
                return true;
            }else{
                System.out.println("Invalid");
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }





    }

    private void showError(String mess){

        WorkingTimeError.setText(mess);
    }

    private String getTime(int[]  time){
        return  hours[time[0]]+" : "+minutes[time[1]]+" "+amPm[time[2]];
    }

    private String[] sliceTime(String time){
        String slice[]= new String[3];
        String temp[]= time.split(" ");
        slice[2]=temp[1].trim();
        String temp2[]=temp[0].split(":");
        slice[0]=temp2[0].trim();
        slice[1]=temp2[1].trim();
        return slice;


    }
   

    private  String convert12to24(String input){
       // 10:22:12 PM

        DateFormat df = new SimpleDateFormat("hh:mm aa");

        DateFormat outputformat = new SimpleDateFormat("HH:mm");
        Date date;
        String output;
        try{
            date= df.parse(input);
            output = outputformat.format(date);
            System.out.println(output);
            return output;
        }catch(Exception pe){
            return pe.toString();
        }
    }


    private  String convert24to12(String input){
        // 10:22:12 PM



        DateFormat df = new SimpleDateFormat("HH:mm");

        DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
        Date date;
        String output;
        try{
            date= df.parse(input);
            output = outputformat.format(date);
            System.out.println(output);
            return output;
        }catch(Exception pe){
            return pe.toString();
        }
    }



}
