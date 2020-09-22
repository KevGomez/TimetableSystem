package timetablesystem.DateTimeManagement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.omg.CORBA.PRIVATE_MEMBER;


import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.input.KeyEvent;

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
    ArrayList<String> timeSlots= new ArrayList<>();

    int tSlotX=0,tSlotY=0;
    String hours[]= new String[12],minutes[]= new String[60], amPm[]= {"AM","PM"};
    int sTime[]= new int[3],eTime[]= new int[3],lTime[]= new int[3],timeSlotTime[]= new int[3];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


        for (int i=0; i<60;i++){
            if (i==0){
                minutes[i]="00";
            }else{
                minutes[i]=String.valueOf(i);
            }
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



        String tempStart[]=daysHandler.sTime.split(" ");
        String tempEnd[]=daysHandler.eTime.split(" ");
        String tempLunch[]=daysHandler.lunch.split(" ");

        for (int i=0;i<tempStart.length;i++){
            sTime[i]=Integer.parseInt(tempStart[i]);
            eTime[i]=Integer.parseInt(tempEnd[i]);
            lTime[i]=Integer.parseInt(tempLunch[i]);
            timeSlotTime[i]=Integer.parseInt(tempStart[i]);

        }



        hStart.getSelectionModel().select(sTime[0]);
        mStart.getSelectionModel().select(sTime[1]);
        sAmPm.getSelectionModel().select(sTime[2]);

        hEnd.getSelectionModel().select(eTime[0]);
        mEnd.getSelectionModel().select(eTime[1]);
        eAmPm.getSelectionModel().select(eTime[2]);


        hLunch.getSelectionModel().select(lTime[0]);
        mLunch.getSelectionModel().select(lTime[1]);
        lAmPm.getSelectionModel().select(lTime[2]);




        from.setText(getTime(sTime));
        duration.getItems().add("30 Min");
        duration.getItems().add("1 Hour");
        duration.getSelectionModel().select(0);




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
                sTime[0]=(int)newValue;
                timeSlotTime[0]=(int)newValue;
                daysHandler.setWorkingTime("startTime",sTime);
                from.setText(getTime(sTime));

            }
        });
        mStart.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sTime[1]=(int)newValue;
                timeSlotTime[1]=(int)newValue;
                daysHandler.setWorkingTime("startTime",sTime);
                from.setText(getTime(sTime));
            }
        });
        sAmPm.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sTime[2]=(int)newValue;
                timeSlotTime[2]=(int)newValue;
                daysHandler.setWorkingTime("startTime",sTime);
                from.setText(getTime(sTime));
            }
        });





        //--------------------------------------------------------------------------------------------------------------
        hEnd.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                int temp[]={eTime[0],eTime[1],eTime[2]};
                temp[0]=(int)newValue;
                if (isTimesEquals(sTime,temp)){
                    eTime[0]=(int)newValue;
                    daysHandler.setWorkingTime("endTime",eTime);
                    showError("");

                }else{
                    showError("Invalid End Time ");
                }


            }
        });
        mEnd.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int temp[]={eTime[0],eTime[1],eTime[2]};
                temp[2]=(int)newValue;
                if (isTimesEquals(sTime,temp)){
                    eTime[2]=(int)newValue;
                    daysHandler.setWorkingTime("endTime",eTime);
                    showError("");


                }else{
                    showError("Invalid End Time ");
                }

            }
        });
        eAmPm.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int temp[]={eTime[0],eTime[1],eTime[2]};
                temp[2]=(int)newValue;
                if (isTimesEquals(sTime,temp)){
                    eTime[2]=(int)newValue;
                    daysHandler.setWorkingTime("endTime",eTime);
                    showError("");

                }else{
                    showError("Invalid End Time ");
                }
            }
        });





        //--------------------------------------------------------------------------------------------------------------
        hLunch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               int temp[]= {lTime[0],lTime[1],lTime[2]};
               temp[0]=(int)newValue;

                if (isTimesEquals(sTime,temp)){
                    if (isTimesEquals(temp,eTime)){
                        showError("");
                        lTime[0]=(int)newValue;
                        daysHandler.setWorkingTime("lunch",lTime);
                    }else{
                        showError("Invalid Lunch Time h 2");
                    }
                }else{
                    showError("Invalid Lunch Time h 1");
                }

            }
        });
        mLunch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int temp[]= {lTime[0],lTime[1],lTime[2]};
                temp[1]=(int)newValue;

                if (isTimesEquals(sTime,temp)){
                    if (isTimesEquals(temp,eTime)){
                        showError("");
                        lTime[1]=(int)newValue;
                        daysHandler.setWorkingTime("lunch",lTime);
                    }else{
                        showError("Invalid Lunch Time m 2");
                    }
                }else{
                    showError("Invalid Lunch Time m 1");
                }
            }
        });
        lAmPm.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int temp[]= {lTime[0],lTime[1],lTime[2]};
                temp[2]=(int)newValue;

                if (isTimesEquals(sTime,temp)){
                    if (isTimesEquals(temp,eTime)){
                        showError("");
                        lTime[2]=(int)newValue;
                        daysHandler.setWorkingTime("lunch",lTime);
                        showError("Invalid Lunch Time am 2");
                    }
                }else{
                    showError("Invalid Lunch Time am 1");
                }

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
    public void addNewTimeSlot(MouseEvent mouseEvent) {

        String slot=duration.getSelectionModel().getSelectedItem();

        String nextTime,time="";

        int min=timeSlotTime[1];
        int hour=timeSlotTime[0];
        int ampm=timeSlotTime[2];
        System.out.println(hour);
        String tAmPm=amPm[ampm];


        if (slot.equals("30 Min")){
            min+=30;

            if (min>60){

                min-=60;
                hour++;
                if (hour==11){
                    if (timeSlotTime[2]==0){
                        tAmPm="PM";
                        ampm=1;
                    }else{
                        tAmPm="AM";
                        ampm=0;
                    }
                }
                if (hour==12){
                    hour=0;
                }

                nextTime=hours[hour]+" "+min+" "+tAmPm;
            }else{
                nextTime=hours[hour]+" "+min+" "+tAmPm;
            }
            if (min==0){
                nextTime=hours[hour]+" 00 "+" "+tAmPm;
            }
          //  System.out.println(hour+" "+hours[hour]);
            time=getTime(timeSlotTime)+" to "+nextTime;



        }else if (slot.equals("1 Hour")){
            hour++;
            if (hour==11){
                if (timeSlotTime[2]==0){
                    tAmPm="PM";
                    ampm=1;
                }else{
                    tAmPm="AM";
                    ampm=0;
                }

            }
            if (hour==12){
                hour=0;
            }


            nextTime=hours[hour]+" "+min+" "+tAmPm;

            time=getTime(timeSlotTime)+" to "+nextTime;


        }


           if (isTimesEquals(timeSlotTime,eTime)){
               addSlot(time);
               timeSlotTime[0]=hour;
               timeSlotTime[1]=min;
               timeSlotTime[2]=ampm;
            }else{
                addSlotsError();
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
        Label label = new Label();
        label.setText(time);
        label.setMinHeight(10);
        timeSoltsGrid.add(label,tSlotX,tSlotY);
        tSlotX++;
        if (tSlotX==6){
            tSlotY++;
            tSlotX=0;
        }
        from.setText(getTime(timeSlotTime));

    }

    private Boolean isTimesEquals(int s1[],int s2[] ){

        // check s1<s2
        int sh1=s1[0],sh2=s2[0],sm1=s1[2],sm2=s2[2];
        if (s1[2]==1){
            if (sh1<11){
                sh1+=12;
            }
        }

        if (s2[2]==1){
            if (sh2<11){
                sh2+=12;
            }
        }

        if (sh1<sh2){
                return true;
        }else if(sh1==sh2) {
                    if(sm1<sm2){
                        return true;
                    }else {
                        return false;
                    }
        }else {
                return false;
        }





    }

    private void showError(String mess){

        WorkingTimeError.setText(mess);
    }

    private String getTime(int[]  time){
        return  hours[time[0]]+" : "+minutes[time[1]]+" "+amPm[time[2]];
    }
   




}
