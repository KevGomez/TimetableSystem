package Controller;

import Controller.LocationPrefernceDAO.SubgroupRoomDAO;
import Model.Lecture;
import Model.Room;
import Model.StudentSubgroup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadRoomList();
        LoadSubGroupList();

        add_preference_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Room room =(Room) room_combo.getSelectionModel().getSelectedItem();
                String Roomid=room.getIdroom();

                StudentSubgroup studentSubgroup =(StudentSubgroup)subgroup_combo.getSelectionModel().getSelectedItem();
                String Subgroupid=Integer.toString(studentSubgroup.getIdstudents_grp());

                SubgroupRoomDAO newRoomD=new SubgroupRoomDAO();
                newRoomD.InsertData(Roomid,Subgroupid);
                System.out.println("Subgroup and room ids added");


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
            subgroup_combo.setItems(SubgroupRoomDAO.getObservebleList(SubgroupRoomDAO.GetAllSubGroup()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
