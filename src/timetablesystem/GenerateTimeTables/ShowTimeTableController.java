package timetablesystem.GenerateTimeTables;

import Model.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import timetablesystem.DateTimeManagement.DaysHandler;
import timetablesystem.DateTimeManagement.TimeSlots;
import timetablesystem.DateTimeManagement.TimeSlotsController;


import java.net.URL;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ShowTimeTableController implements Initializable {

    @FXML
    private ChoiceBox<String> timetableuser;

    @FXML
    private ChoiceBox<String> tsuserList;

    @FXML
    private Label tsuserlabel;

    @FXML
    private Button showTimeTable;


    private GridPane timetableGrid;

    @FXML
    private ScrollPane tiimeTableArea;

    private DaysHandler daysHandler;

    private int row=10,column=5;
    private ArrayList<String>subjectsV2s;
    private ArrayList<String> lecturerV2s;
    private ArrayList<String> locations;
    private String user;

    private boolean isStudent=true;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        daysHandler= new DaysHandler();
        daysHandler.getData();
        column=DaysHandler.nDaysPerWeek;
        timetableGrid= new GridPane();
        showTimeTable.setDisable(true);
        System.out.println(DaysHandler.isthirtyMin);


        subjectsV2s= new ArrayList<>();
        locations = new ArrayList<>();


        subjectsV2s.add("Subject 1");
        subjectsV2s.add("Subject 2");
        subjectsV2s.add("Subject 3");
        subjectsV2s.add("Subject 4");
        subjectsV2s.add("Subject 5");

        locations.add("A001");
        locations.add("A002");
        locations.add("A003");
        locations.add("A004");
        locations.add("A005");



        String users[]={"Students","Lecturers"};
        ObservableList<String> ttusers= FXCollections.observableArrayList(users);
        ObservableList<String>  ttuserList = FXCollections.observableArrayList();
       timetableuser.setItems(ttusers);

       timetableuser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                user=users[(int)newValue];
                if ((int)newValue==0){
                    ttuserList.clear();
                    ttuserList.addAll(DataController.getTimeTableStudents());
                    tsuserList.setItems(ttuserList);
                    isStudent=true;
                }else{
                    ttuserList.clear();
                    ttuserList.addAll(DataController.getTimeTableLecturers());
                    tsuserList.setItems(ttuserList);
                    isStudent=false;
                }


                showTimeTable.setDisable(true);
                timetableGrid=null;
                timetableGrid= new GridPane();
                timetableGrid.getChildren().clear();
              //  tiimeTableArea.getChildren().clear();

            }
        });

        tsuserList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                showTimeTable.setDisable(false);
                timetableGrid=null;
                timetableGrid= new GridPane();
                timetableGrid.getChildren().clear();
               // tiimeTableArea.getChildren().clear();
            }
        });

    }


    @FXML
    public void showTimeTable(MouseEvent mouseEvent) {
        try {
            timetableGrid.getChildren().clear();
            generateHeaderCol();
            generateHeaderRow();
            if (isStudent){


                generatrTimeTable();
                //     timetableGrid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");

            }else{
                showLecturerTimeTable();
            }
            timetableGrid.setGridLinesVisible(true);
            showTimeTable.setDisable(true);
        }catch (Exception e){
            System.out.println(e.toString());
        }


    }

    private  void showLecturerTimeTable(){
        if (DaysHandler.isthirtyMin.equals("true")){
            row=2;
        }else{
            row=1;
        }
        List<SubjectsV2> subjectsV2s=DataController.getSubjectsV2();
        ArrayList<LocationV2> locationV2s=DataController.getLoactionV2();
        ArrayList<String> students=DataController.getTimeTableStudents();
        ArrayList<LocationV2> temparr;
        ArrayList<Label> labels= new ArrayList<>();


        Random random= new Random();
        int subjects[]=new int[2];
        subjects[0]=random.nextInt(subjectsV2s.size() - 1 + 1) + 1;
        subjects[1]=random.nextInt(subjectsV2s.size() - 1 + 1) + 1;
        while(subjects[0]==subjects[1]){
            subjects[1]=random.nextInt(subjectsV2s.size() - 1 + 1) + 1;
        }

        int groups[]=new int[2];
        groups[0]=random.nextInt(students.size() - 1 + 1) + 1;
        groups[1]=random.nextInt(students.size() - 1 + 1) + 1;
        while(groups[0]==groups[1]){
            groups[1]=random.nextInt(students.size() - 1 + 1) + 1;
        }
        String location[]={"Malabe","Metro"};




        for (int a=0;a<5;a++){
            temparr=locationV2s;
            for (int b=0;b<4;b++){

                int l=random.nextInt(temparr.size());
                System.out.print("l "+l+" ");

                Label label = new Label();
                label.setMinWidth(150.00);
                label.setText(subjectsV2s.get(subjects[random.nextInt(2)]).sid+"\n"+students.get(groups[random.nextInt(2)])+"\n"+temparr.get(l).getName()+"\n"+location[random.nextInt(2)]);
                label.setMinHeight(10);
                labels.add(label);
            }
        }



        int chDays[]=new int[2];
        chDays[0]=random.nextInt(5 - 1 + 1) + 1;
        chDays[1]=random.nextInt(5 - 1 + 1) + 1;
        while(chDays[0]==chDays[1]){
            chDays[1]=random.nextInt(5 - 1 + 1) + 1;
        }
        System.out.println(chDays[0]+" "+chDays[1]);
        int c=0;
        int postions[]= new int[3];


        for (int i=1;i<6;i++){


            postions[0]= random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            postions[1]= random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            while(postions[0]==postions[1]){
                postions[1]=random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            }
            postions[2]= random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            while(postions[0]==postions[2] ||postions[1]==postions[2] ){
                postions[2]=random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            }


            if (i==chDays[0]){
                timetableGrid.add(labels.get(c++),i,postions[0]);//lecture
                timetableGrid.add(labels.get(c++),i,postions[1]);//tute
                timetableGrid.add(labels.get(c++),i,postions[2]);//lab
            }else if (i==chDays[1]){
                timetableGrid.add(labels.get(c++),i,postions[0]);//lecture
                timetableGrid.add(labels.get(c++),i,postions[1]);//tute
                timetableGrid.add(labels.get(c++),i,postions[2]);//lab
            }else{
                System.out.println(labels.size());
                timetableGrid.add(labels.get(c++),i,postions[0]);//lecture
                timetableGrid.add(labels.get(c++),i,postions[1]);//tute
                timetableGrid.add(labels.get(c++),i,postions[2]);//lab
            }

        }

        tiimeTableArea.setContent(timetableGrid);




        System.out.println("All "+subjectsV2s.size()+" "+DataController.timeslotsCount());
    }


    private void generatrTimeTable(){
        if (DaysHandler.isthirtyMin.equals("true")){
            row=2;
        }else{
            row=1;
        }




        String timeTableCategory=tsuserList.getSelectionModel().getSelectedItem();
        String userid=tsuserList.getSelectionModel().getSelectedItem();
        List<SubjectsV2> subjectsV2s=DataController.getSubjectsV2().stream().filter(subjectsV2 ->userid.startsWith(subjectsV2.temID)).collect(Collectors.toList());
        ArrayList<LocationV2> locationV2s=DataController.getLoactionV2();
        ArrayList<LocationV2> temparr;
        ArrayList<Label> labels= new ArrayList<>();
        int x=2 ,y=2;

        Random random= new Random();
        int chDays[]=new int[2];
        chDays[0]=random.nextInt(5 - 1 + 1) + 1;
        chDays[1]=random.nextInt(5 - 1 + 1) + 1;
        while(chDays[0]==chDays[1]){
            chDays[1]=random.nextInt(5 - 1 + 1) + 1;
        }
        System.out.println(chDays[0]+" "+chDays[1]);





        for (int a=0;a<5;a++){
            temparr=locationV2s;
            for (int b=0;b<3;b++){

                int l=random.nextInt(temparr.size());
                System.out.print("l "+l+" ");

                Label label = new Label();
                label.setMinWidth(150.00);
                label.setText(subjectsV2s.get(a).sid+"\nLec \n"+temparr.get(l).getName());
                label.setMinHeight(10);
                labels.add(label);
                Label label2 = new Label();
                label2.setMinWidth(150.00);
                label2.setText(subjectsV2s.get(a).sid+"\nTute \n"+temparr.get(l).getName());
                label2.setMinHeight(10);
                labels.add(label2);
                Label label3 = new Label();
                label3.setMinWidth(150.00);
                label3.setText(subjectsV2s.get(a).sid+"\nLab \n"+temparr.get(l).getName());
                label3.setMinHeight(10);
                labels.add(label3);
            }
        }

        int c=0;

        int postions[]= new int[3];


        for (int i=1;i<6;i++){


            postions[0]= random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            postions[1]= random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            while(postions[0]==postions[1]){
                postions[1]=random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            }
            postions[2]= random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            while(postions[0]==postions[2] ||postions[1]==postions[2] ){
                postions[2]=random.nextInt(TimeSlotsController.getTimeSlots().size())+1;
            }


            if (i==chDays[0]){
                timetableGrid.add(labels.get(c++),i,postions[0]);//lecture
                timetableGrid.add(labels.get(c++),i,postions[1]);//tute
                timetableGrid.add(labels.get(c++),i,postions[2]);//lab
            }else if (i==chDays[1]){
                timetableGrid.add(labels.get(c++),i,postions[0]);//lecture
                timetableGrid.add(labels.get(c++),i,postions[1]);//tute
                timetableGrid.add(labels.get(c++),i,postions[2]);//lab
            }else{
            System.out.println(labels.size());
                timetableGrid.add(labels.get(c++),i,postions[0]);//lecture
                timetableGrid.add(labels.get(c++),i,postions[1]);//tute
                timetableGrid.add(labels.get(c++),i,postions[2]);//lab
            }

        }

        tiimeTableArea.setContent(timetableGrid);




        System.out.println("All "+subjectsV2s.size()+" "+DataController.timeslotsCount());


    }



    private void generateHeaderCol(){
        int x=1;
        column=daysHandler.getWorkingDays().size();
        for(String s:daysHandler.getWorkingDays()){
                Label label = new Label();
                label.setMinWidth(150.00);
                label.setText(s);
                label.setMinHeight(10);
            timetableGrid.add(label,x,0);
                x++;

        }
      //  tiimeTableArea.getChildren().add(timeTable);

    }

    private void generateHeaderRow(){
        row=TimeSlotsController.getTimeSlots().size();
        try {
            int y=1;
            for(TimeSlots slots: TimeSlotsController.getTimeSlots()){
                Label label = new Label();
                label.setMinWidth(150.00);
                label.setMinHeight(150.00);
                label.setText(slots.getTime());
                label.setMinHeight(10);
                timetableGrid.add(label,0,y);
                y++;

            }
          //  tiimeTableArea.getChildren().add(timeTable);
        }catch (Exception e){
            System.out.println(e.toString());
        }


    }




}
