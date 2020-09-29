package Controller;

import Controller.LocationPrefernceDAO.TaghasLocationDAO;
import Model.Building;
import Model.Room;
import Model.TagData;
import Model.TagHasRooms;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TagPreferenceController implements Initializable {
    @FXML  private ComboBox   room_combo;
    @FXML  private ComboBox   tag_combo;
    @FXML private Button add_preference_btn;

    @FXML private Button tax_search_btn;
    @FXML private Button tag_edit_btn;
    @FXML private Button tag_delete_btn;

    @FXML   TableView<TagHasRooms> tag_room_table;
    @FXML   TableColumn<TagHasRooms,String> room_column;
    @FXML   TableColumn<TagHasRooms,String> tag_column;
    @FXML TableColumn<TagHasRooms,Integer> tag_id_column;
    @FXML TableColumn<TagHasRooms,Integer> room_id_column;



//   TagHasRooms tagHasRooms;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        tagHasRooms=new TagHasRooms();

        LoadRoomList();
        ShowTagHasRoomTable();
        LoadTagList();



        add_preference_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TagData tag =(TagData)tag_combo.getSelectionModel().getSelectedItem();
                Room room =(Room) room_combo.getSelectionModel().getSelectedItem();
                String Roomid=room.getIdroom();
                String Tagid= Integer.toString(tag.getId());

                TaghasLocationDAO newTaghasRoom =new TaghasLocationDAO();
                newTaghasRoom.InsertData(Roomid,Tagid);
                //IF tag is lecture ,tutorial tag allocate to particular room
                if(Integer.parseInt(Tagid)==1){
                    newTaghasRoom.InsertData(Roomid,"2");

                }else if(Integer.parseInt(Tagid)==2){
                    //IF tag is tutorial ,lecture tag allocate to particular room

                    newTaghasRoom.InsertData(Roomid,"1");

                }

                ShowTagHasRoomTable();
                System.out.println("add new tag has room");

            }
        });

        tag_delete_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String roomID= String.valueOf(tag_room_table.getSelectionModel().getSelectedItem().getRoom_idroom());
                String tagID=String.valueOf(tag_room_table.getSelectionModel().getSelectedItem().getTag_idtag());

                TaghasLocationDAO taghasLocationDAO=new TaghasLocationDAO();
                taghasLocationDAO.DeleteData(roomID,tagID);
                System.out.println("Add tag id and room id to delete");
                ShowTagHasRoomTable();

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


    public  void LoadTagList(){
        try {
            tag_combo.setItems(TaghasLocationDAO.getObservebleTagList(TaghasLocationDAO.GetAllTags()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ShowTagHasRoomTable(){
        //Room tag tagble
        room_column.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        tag_column.setCellValueFactory(new PropertyValueFactory<>("tag"));
        room_id_column.setCellValueFactory(new PropertyValueFactory<>("room_idroom"));
        tag_id_column.setCellValueFactory(new PropertyValueFactory<>("tag_id_column"));

        try{
//            tag_room_table.setItems(taghasLocationDAO.getObservebleList(taghasLocationDAO.GetAllRoomsAndTags()));
            if(TaghasLocationDAO.getObservebleList(TaghasLocationDAO.GetAllRoomsAndTags())==null){
                System.out.println(" taghasLocationDAO is null");
            }else {
                System.out.println("Not Null");
                tag_room_table.setItems(TaghasLocationDAO.getObservebleList(TaghasLocationDAO.GetAllRoomsAndTags()));
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}
