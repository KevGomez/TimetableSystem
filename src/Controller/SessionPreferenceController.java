package Controller;

import Controller.LocationPrefernceDAO.SessionRoomDAO;
import Model.Room;
import Model.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SessionPreferenceController implements Initializable {
    @FXML
    private ComboBox room_combo;
    @FXML  private ComboBox   session_combo;
    @FXML private Button add_preference_btn;

    @FXML private Button tax_search_btn;
    @FXML private Button tag_edit_btn;
    @FXML private Button tag_delete_btn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadRoomList();
        LoardSessionList();

        add_preference_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Room room =(Room) room_combo.getSelectionModel().getSelectedItem();
                String Roomid=room.getIdroom();

                Session session=(Session) session_combo.getSelectionModel().getSelectedItem();
                String sessionid= Integer.toString(session.getIdsessions());

                SessionRoomDAO sessionRoomDAO =new SessionRoomDAO();
                sessionRoomDAO.InsertData(Roomid,sessionid);
                System.out.println("Session and room id added");
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

    public void LoardSessionList(){

        try{
            session_combo.setItems(SessionRoomDAO.getObservebleList(SessionRoomDAO.GetAllSessions()));


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
