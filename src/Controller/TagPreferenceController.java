package Controller;

import Controller.LocationPrefernceDAO.TaghasLocationDAO;
import Model.Building;
import Model.Room;
import Model.TagData;
import Model.TagHasRooms;
import javafx.collections.ObservableList;
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
import java.util.function.Predicate;

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
    @FXML TextField tag_search_textfiled;



//   TagHasRooms tagHasRooms;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        tagHasRooms=new TagHasRooms();

        LoadRoomList();
        ShowTagHasRoomTable();
        LoadTagList();
        FilterData();



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

                tag_room_table.getItems().removeAll(tag_room_table.getSelectionModel().getSelectedItem());


                TaghasLocationDAO taghasLocationDAO=new TaghasLocationDAO();
                taghasLocationDAO.DeleteData(roomID,tagID);
                System.out.println("Add tag_id and room_id to delete");
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
        tag_id_column.setCellValueFactory(new PropertyValueFactory<>("tag_idtag"));

        try{
//            tag_room_table.setItems(taghasLocationDAO.getObservebleList(taghasLocationDAO.GetAllRoomsAndTags()));
            if(TaghasLocationDAO.getObservebleList(TaghasLocationDAO.GetAllRoomsAndTags())==null){
                System.out.println(" taghasLocationDAO is null");
            }else {
                System.out.println("Not Null");
                tag_room_table.setItems(TaghasLocationDAO.getObservebleList(TaghasLocationDAO.GetAllRoomsAndTags()));
                FilterData();
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    public void FilterData(){
        try {
            FilteredList<TagHasRooms> tagHasRoomsFilteredList =new FilteredList<TagHasRooms>(TaghasLocationDAO.getObservebleList(TaghasLocationDAO.GetAllRoomsAndTags()),b->true);
            tag_search_textfiled.textProperty().addListener((observable, oldValue, newValue) -> {
                tagHasRoomsFilteredList.setPredicate(tag_room->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();

                    if(tag_room.getRoomName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(tag_room.getTag().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else{
                        return false;
                    }


                });

                SortedList<TagHasRooms> sortedList=new SortedList<>(tagHasRoomsFilteredList);
                sortedList.comparatorProperty().bind(tag_room_table.comparatorProperty());
                tag_room_table.setItems(tagHasRoomsFilteredList);

            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
