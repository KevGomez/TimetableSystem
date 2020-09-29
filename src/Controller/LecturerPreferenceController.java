package Controller;

import Controller.LocationPrefernceDAO.LecturerRoomDAO;
import Model.Lecture;
import Model.Room;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LecturerPreferenceController implements Initializable {
    @FXML
    private ComboBox room_combo;
    @FXML  private ComboBox   lecture_combo;
    @FXML private Button add_preference_btn;

    @FXML private Button tax_search_btn;
    @FXML private Button tag_edit_btn;
    @FXML private Button tag_delete_btn;

    Lecture lecture;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lecture =new Lecture();
        LoadRoomList();
        LoadLectureList();

        add_preference_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Room room =(Room) room_combo.getSelectionModel().getSelectedItem();
                String Roomid=room.getIdroom();

                Lecture lecturer=(Lecture) lecture_combo.getSelectionModel().getSelectedItem();
                String lecturerid=Integer.toString(lecturer.getIdemployee());

                LecturerRoomDAO lecturerRoomDAO=new LecturerRoomDAO();
                lecturerRoomDAO.InsertData(Roomid,lecturerid);

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

}
