package Controller;

import Controller.LocationPrefernceDAO.LecturerRoomDAO;
import Controller.LocationPrefernceDAO.TaghasLocationDAO;
import Model.Lecture;
import Model.Room;
import Model.RoomHasLecture;
import Model.TagHasRooms;
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

public class LecturerPreferenceController implements Initializable {
    @FXML
    private ComboBox room_combo;
    @FXML  private ComboBox   lecture_combo;
    @FXML private Button add_preference_btn;

    @FXML private TextField lecture_search_textfiled;

    @FXML private Button tax_search_btn;
    @FXML private Button tag_edit_btn;
    @FXML private Button tag_delete_btn;

    @FXML private TableView<RoomHasLecture> Room_Lecture_Table;
    @FXML private TableColumn<RoomHasLecture,String> empid_column;
    @FXML private TableColumn<RoomHasLecture,String> lecture_column;
    @FXML private TableColumn<RoomHasLecture,String> level_column;
    @FXML private TableColumn<RoomHasLecture,String> room_column;
    @FXML private TableColumn<RoomHasLecture,String> roomid_column;
    @FXML private TableColumn<RoomHasLecture,String> buildingname;








    Lecture lecture;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lecture =new Lecture();
        LoadRoomList();
        LoadLectureList();
        LoadTableDate();
        FilterData();

        add_preference_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Room room =(Room) room_combo.getSelectionModel().getSelectedItem();
                String Roomid=room.getIdroom();

                Lecture lecturer=(Lecture) lecture_combo.getSelectionModel().getSelectedItem();
                String lecturerid=Integer.toString(lecturer.getIdemployee());

                LecturerRoomDAO lecturerRoomDAO=new LecturerRoomDAO();
                lecturerRoomDAO.InsertData(Roomid,lecturerid);
                LoadTableDate();


            }
        });

        tag_delete_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String lecturer_idemployee =String.valueOf(Room_Lecture_Table.getSelectionModel().getSelectedItem().getIdemployee());
                String room_idroom =String.valueOf(Room_Lecture_Table.getSelectionModel().getSelectedItem().getIdroom());

                    LecturerRoomDAO lecturerRoomDAO=new LecturerRoomDAO();
                    lecturerRoomDAO.DeleteData(room_idroom,lecturer_idemployee);
                System.out.println(" room_idroom lecturer_idemployee added to delete ");
                LoadTableDate();

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
    public  void  LoadLectureList(){
        try {
            lecture_combo.setItems(Lecture.getObservebleList(Lecture.getAllData()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void LoadTableDate(){

        empid_column.setCellValueFactory(new PropertyValueFactory<>("idemployee"));
        lecture_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        level_column.setCellValueFactory(new PropertyValueFactory<>("level"));
        room_column.setCellValueFactory(new PropertyValueFactory<>("idroom"));
        roomid_column.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        buildingname.setCellValueFactory(new PropertyValueFactory<>("buildingname"));


        try {
            Room_Lecture_Table.setItems(LecturerRoomDAO.getObservebleList(LecturerRoomDAO.GetAllLecturerAndRooms()));
            FilterData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void FilterData(){
        try {
            FilteredList<RoomHasLecture> LecHasRoomsFilteredList =new FilteredList<RoomHasLecture>(LecturerRoomDAO.getObservebleList(LecturerRoomDAO.GetAllLecturerAndRooms()), b->true);
            lecture_search_textfiled.textProperty().addListener((observable, oldValue, newValue) -> {
                LecHasRoomsFilteredList.setPredicate(lec_room->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();

                    if(lec_room.getRoomName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(lec_room.getName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(lec_room.getBuildingname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    else{
                        return false;
                    }


                });

                SortedList<RoomHasLecture> sortedList=new SortedList<>(LecHasRoomsFilteredList);
                sortedList.comparatorProperty().bind(Room_Lecture_Table.comparatorProperty());
                Room_Lecture_Table.setItems(LecHasRoomsFilteredList);

            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
