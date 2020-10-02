package Controller;

import Controller.LocationPrefernceDAO.LecturerRoomDAO;
import Controller.LocationPrefernceDAO.StudentSubgroupRoomDAO;
import Controller.LocationPrefernceDAO.TaghasLocationDAO;
import Model.Room;
import Model.RoomHasLecture;
import Model.StudentGroupHasRoom;
import Model.StudentSubgroup;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudetSubGroupPreferenceController implements Initializable {
    @FXML
    private ComboBox room_combo;
    @FXML  private ComboBox   subgroup_combo;
    @FXML private Button add_preference_btn;

    @FXML private Button tax_search_btn;
    @FXML private Button tag_edit_btn;
    @FXML private Button tag_delete_btn;
    @FXML private TextField tag_search_textfiled;

    @FXML private TableView<StudentGroupHasRoom> room_studentdgroup;

    @FXML private TableColumn<StudentGroupHasRoom,String> idstudents_grp;
    @FXML private TableColumn<StudentGroupHasRoom,String> programme;
    @FXML private TableColumn<StudentGroupHasRoom,String> year_sem;
    @FXML private TableColumn<StudentGroupHasRoom,String> grp_id;
    @FXML private TableColumn<StudentGroupHasRoom,String> sgrp_id;
    @FXML private TableColumn<StudentGroupHasRoom,String> roomName;
    @FXML private TableColumn<StudentGroupHasRoom,String> idroom;
    @FXML private TableColumn<StudentGroupHasRoom,String> buildingsname;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadRoomList();
        LoadSubGroupList();
        LoadStudntGroupAndRoom();
        FilterData();

        add_preference_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Room room =(Room) room_combo.getSelectionModel().getSelectedItem();
                StudentSubgroup studentSubgroup =(StudentSubgroup) subgroup_combo.getSelectionModel().getSelectedItem();


                if(room==null || studentSubgroup==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select Subgroup and Room");
                    alert.showAndWait();
                }else {

                    String Roomid=room.getIdroom();
                    String Subgroupid=Integer.toString(studentSubgroup.getIdstudents_grp());





                    StudentSubgroupRoomDAO newRoomD=new StudentSubgroupRoomDAO();
                    newRoomD.InsertData(Roomid,Subgroupid);
                    System.out.println("Subgroup and room ids added");
                    LoadStudntGroupAndRoom();

                }



            }
        });

        tag_delete_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String room_idroom = String.valueOf(room_studentdgroup.getSelectionModel().getSelectedItem().getIdroom());
                String students_grp_idstudents_grp = String.valueOf(room_studentdgroup.getSelectionModel().getSelectedItem().getIdstudents_grp());

                StudentSubgroupRoomDAO studentSubgroupRoomDAO=new StudentSubgroupRoomDAO();
                studentSubgroupRoomDAO.DeleteData(room_idroom,students_grp_idstudents_grp);
                LoadStudntGroupAndRoom();
                System.out.println("room_idroom and students_grp_idstudents_grp push to delete");

            }
        });



    }

    public void  LoadRoomList(){
        try {
            room_combo.setItems(Room.getObservebleList(Room.getAllData()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public  void  LoadSubGroupList(){
        try {
            subgroup_combo.setItems(StudentSubgroupRoomDAO.getObservebleList(StudentSubgroupRoomDAO.GetAllSubGroup()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void LoadStudntGroupAndRoom(){

        idstudents_grp.setCellValueFactory(new PropertyValueFactory<>("idstudents_grp"));
        programme.setCellValueFactory(new PropertyValueFactory<>("programme"));
        year_sem.setCellValueFactory(new PropertyValueFactory<>("year_sem"));
        grp_id.setCellValueFactory(new PropertyValueFactory<>("grp_id"));
        sgrp_id.setCellValueFactory(new PropertyValueFactory<>("sgrp_id"));
        roomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        idroom.setCellValueFactory(new PropertyValueFactory<>("idroom"));
        buildingsname.setCellValueFactory(new PropertyValueFactory<>("buildingsname"));


        try{
            if(StudentSubgroupRoomDAO.getObservebleStudentSubGroupAndRoomList(StudentSubgroupRoomDAO.GetAllSubGroupAndRoom())==null){
                System.out.println(" taghasLocationDAO is null");
            }else {
                System.out.println("Not Null");
                room_studentdgroup.setItems(StudentSubgroupRoomDAO.getObservebleStudentSubGroupAndRoomList(StudentSubgroupRoomDAO.GetAllSubGroupAndRoom()));
                FilterData();
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }



    }

    public  void  FilterData(){
        try {
            FilteredList<StudentGroupHasRoom> LecHasRoomsFilteredList =new FilteredList<StudentGroupHasRoom>(StudentSubgroupRoomDAO.getObservebleStudentSubGroupAndRoomList(StudentSubgroupRoomDAO.GetAllSubGroupAndRoom()), b->true);
            tag_search_textfiled.textProperty().addListener((observable, oldValue, newValue) -> {
                LecHasRoomsFilteredList.setPredicate(subgroup_room->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();

                    if(subgroup_room.getRoomName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(subgroup_room.getProgramme().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(subgroup_room.getYear_sem().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(subgroup_room.getSgrp_id().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }

                    else{
                        return false;
                    }


                });

                SortedList<StudentGroupHasRoom> sortedList=new SortedList<>(LecHasRoomsFilteredList);
                sortedList.comparatorProperty().bind(room_studentdgroup.comparatorProperty());
                room_studentdgroup.setItems(LecHasRoomsFilteredList);

            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
