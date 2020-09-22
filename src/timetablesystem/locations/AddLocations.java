package timetablesystem.locations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import  timetablesystem.models.*;
import timetablesystem.models.Building;
import timetablesystem.models.Room;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddLocations implements Initializable {

    @FXML public Button add_building_btn;
    @FXML public Button add_room_btn;
    @FXML public Button clear_building;
    @FXML public Button clear_room;
    @FXML public Button building_search_btn;
    @FXML public Button room_search_btn;
    @FXML public Button building_delete;
    @FXML public Button delete_room;
    @FXML public  Button update_building;
    @FXML public  Button update_building_btn;
    @FXML public  Button room_edit_btn;
    @FXML public  Button update_room_btn;

    @FXML private TextField addBuilding_text;
    @FXML private TextField addRoom_text;
    @FXML private TextField building_search;
    @FXML private  TextField room_search;
    @FXML private  TextField room_capacity_text;
    @FXML private  ComboBox room_buiding_dop;

    @FXML private TableView<Building> building_table;

    @FXML private TableColumn<Building,String> building_id_row;
    @FXML private TableColumn<Building,String> building_name_row;

    @FXML private TableView<Room> room_table;
    @FXML private TableColumn<Room,String> room_id;
    @FXML private TableColumn<Room,String> room_name;
    @FXML private TableColumn<Room,String> room_building;
    @FXML private TableColumn<Room,String> room_capacity;






    Building Building ;
    Room Rooms;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Building=new Building();
        Rooms=new Room();
        //Room centraler
        //tevwrtwrt


         add_building_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String buildingname=addBuilding_text.getText().trim();
                if(buildingname.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Add Building is Empty ");
                    alert.showAndWait();
                }else {


                    Building building=new Building();
                    building.setBuildingName(buildingname);
                    building.CreateBuilding();

                    System.out.println("Buiding created");
                    addBuilding_text.clear();
                    showBuildingTable();
                    getValueforBuldinnameDropdown();
                }


            }
        });

<<<<<<< HEAD


=======
>>>>>>> add new locations
        add_room_btn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 String roomname=addRoom_text.getText().trim();
                 String roomcapasity=room_capacity_text.getText().trim();
                 String building_room=room_buiding_dop.getValue().toString();

                 System.out.println(building_room);
                    if (roomname.isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Add Rooms is Empty ");
                        alert.showAndWait();
                    }else{
                        Room room =new Room();
                        room.setRoomName(roomname);
                        room.setRoomCapacity(roomcapasity);
                        room.setBuildingName(building_room);
                        room.CreateRoom();
                        addRoom_text.clear();
                        room_capacity_text.clear();
                        showRoomsTable();


                    }
              }
         });

        clear_building.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBuilding_text.clear();
            }
        });

        clear_room.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addRoom_text.clear();
            }
        });

        room_search_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String roomkeyword=room_search.getText().trim();
                searchRoom(roomkeyword);
            }
        });

        building_search_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String buidingroom =building_search.getText().trim();
                searchBuilding(buidingroom);
            }
        });


        building_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String buildingID=(building_table.getSelectionModel().getSelectedItem().getId());
                if (!buildingID.isEmpty()){
                    Building.DeleteData(buildingID);
                    building_table.getItems().removeAll(building_table.getSelectionModel().getSelectedItem());
                    System.out.println("Delete Building Successful");
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select a any row ");
                    alert.showAndWait();
                }


            }
        });

        delete_room.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String roomID=room_table.getSelectionModel().getSelectedItem().getRoomId();
               
                System.out.println(roomID);
                if(!roomID.isEmpty()){
                    Rooms.DeleteData(roomID);
                    room_table.getItems().removeAll(room_table.getSelectionModel().getSelectedItem());
                    System.out.println("Delete Room Successful");
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select a any row ");
                    alert.showAndWait();
                }
            }
        });

        update_building.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String buildingID=(building_table.getSelectionModel().getSelectedItem().getId());
                String buildingName=(building_table.getSelectionModel().getSelectedItem().getBuildingName());

                if (!buildingID.isEmpty()){
//                    update_building_btn.setVisible(true);
                    togalUpdateAndAddButtonBuilding();

                    addBuilding_text.setText(buildingName);

                    update_building_btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String newBuildigvalue=addBuilding_text.getText().trim();
                            if(!newBuildigvalue.isEmpty()){
                                Building.UpdateData(buildingID,newBuildigvalue);
                                showBuildingTable();
                                togalUpdateAndAddButtonBuilding();
                                addBuilding_text.clear();
                                System.out.println("updated!");
                            }else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Building is Empty ");
                                alert.showAndWait();
                            }

                        }
                    });

                    System.out.println("update Building Successful");
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select a any row ");
                    alert.showAndWait();
                }

            }
        });

        room_edit_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String roomID=(room_table.getSelectionModel().getSelectedItem().getRoomId());
                String roomName=(room_table.getSelectionModel().getSelectedItem().getRoomName());
                String roomCapasity=(room_table.getSelectionModel().getSelectedItem().getRoomCapacity());
                String roomBuilding=(room_table.getSelectionModel().getSelectedItem().getBuildingName());


                if(!roomID.isEmpty()){
                    togalUpdateAndAddButtonRoom();
                    addRoom_text.setText(roomName);
                    room_capacity_text.setText(roomCapasity);
                    room_buiding_dop.setValue(roomBuilding);

                    update_room_btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String newRoomvalue=addRoom_text.getText().trim();
                            String newRoomCapasityvalue=room_capacity_text.getText().trim();
                            String newRoomBuilding=room_buiding_dop.getValue().toString();


                            if(!newRoomvalue.isEmpty()){
                                Rooms.UpdateData(roomID,newRoomvalue,newRoomCapasityvalue,newRoomBuilding);
                                showRoomsTable();
                                togalUpdateAndAddButtonRoom();
                                addRoom_text.clear();
                                room_capacity_text.clear();

                                System.out.println("Room updated");
                            }else{
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Rooms is Empty ");
                                alert.showAndWait();
                            }


                        }
                    });

                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select a any row ");
                    alert.showAndWait();
                }

            }
        });


        showBuildingTable();
        showRoomsTable();
        getValueforBuldinnameDropdown();



    }

    public void showBuildingTable(){
        getValueforBuldinnameDropdown();
        //Building Table
        building_id_row.setCellValueFactory(new PropertyValueFactory<>("Id"));
        building_name_row.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
        try {
            building_table.setItems(Building.getObservebleList(Building.getAllData()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void showRoomsTable(){
        //Room Table
        room_id.setCellValueFactory(new PropertyValueFactory<>("RoomId"));
        room_name.setCellValueFactory(new  PropertyValueFactory<>("RoomName"));
        room_capacity.setCellValueFactory(new  PropertyValueFactory<>("RoomCapacity"));
        room_building.setCellValueFactory(new  PropertyValueFactory<>("BuildingName"));


        try {
            room_table.setItems(Rooms.getObservebleList(Rooms.getAllData()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void searchBuilding(String keyword){
        building_id_row.setCellValueFactory(new PropertyValueFactory<>("Id"));
        building_name_row.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
        try {
            building_table.setItems(Building.getObservebleList(Building.getSelectedData(keyword)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void searchRoom(String keyword){
        room_id.setCellValueFactory(new PropertyValueFactory<>("RoomId"));
        room_name.setCellValueFactory(new  PropertyValueFactory<>("RoomName"));
        try {
            room_table.setItems(Rooms.getObservebleList(Rooms.getSelectedData(keyword)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void togalUpdateAndAddButtonBuilding(){
         update_building_btn.setVisible(!update_building_btn.isVisible());
         add_building_btn.setVisible(!add_building_btn.isVisible());

    }

    public void togalUpdateAndAddButtonRoom(){
        update_room_btn.setVisible(!update_room_btn.isVisible());
        add_room_btn.setVisible(!add_room_btn.isVisible());

    }

    public void getValueforBuldinnameDropdown(){

        try {
            room_buiding_dop.setItems(Building.getStringObservebleList(Building.getAllData()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
