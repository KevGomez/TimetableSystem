package timetablesystem.GenerateTimeTables;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import timetablesystem.DateTimeManagement.DaysHandler;
import timetablesystem.DateTimeManagement.TimeSlots;
import timetablesystem.DateTimeManagement.TimeSlotsController;


import java.net.URL;
import java.util.ResourceBundle;

public class ShowTimeTableController implements Initializable {

    @FXML
    private ChoiceBox<String> timetableuser;

    @FXML
    private ChoiceBox<String> tsuserList;

    @FXML
    private Label tsuserlabel;

    @FXML
    private Button showTimeTable;

    @FXML
    private GridPane timeTableGrid;

    @FXML
    private Pane tiimeTableArea;

    private DaysHandler daysHandler;
    private GridPane timeTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        daysHandler= new DaysHandler();
        timeTable= new GridPane();



        String users[]={"Students","Lecturers"};
        ObservableList<String> ttusers= FXCollections.observableArrayList(users);
        ObservableList<String>  ttuserList = FXCollections.observableArrayList();
        timetableuser.setItems(ttusers);

        timetableuser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if ((int)newValue==0){
                    ttuserList.clear();
                    ttuserList.addAll(DataController.getTimeTableStudents());
                    tsuserList.setItems(ttuserList);
                }else{
                    ttuserList.clear();
                    ttuserList.addAll(DataController.getTimeTableLecturers());
                    tsuserList.setItems(ttuserList);
                }
            }
        });

    }


    @FXML
    public void showTimeTable(MouseEvent mouseEvent) {
        try {
            timeTable.getChildren().clear();
            generateHeaderCol();
            generateHeaderRow();
            generatrTimeTable();
        }catch (Exception e){

        }


    }

    private void generatrTimeTable(){

        String timeTableCategory=tsuserList.getSelectionModel().getSelectedItem();
        System.out.println(timeTableCategory);
    }



    private void generateHeaderCol(){
        int x=1;
        for(String s:daysHandler.getWorkingDays()){
                Label label = new Label();
                label.setMinWidth(150.00);
                label.setText(s);
                label.setMinHeight(10);
                timeTable.add(label,x,0);
                x++;

        }
        tiimeTableArea.getChildren().add(timeTable);

    }

    private void generateHeaderRow(){
        try {
            int y=1;
            for(TimeSlots slots: TimeSlotsController.getTimeSlots()){
                Label label = new Label();
                label.setMinWidth(150.00);
                label.setMinHeight(150.00);
                label.setText(slots.getTime());
                label.setMinHeight(10);
                timeTable.add(label,0,y);
                y++;

            }
            tiimeTableArea.getChildren().add(timeTable);
        }catch (Exception e){
            System.out.println(e.toString());
        }


    }




}
