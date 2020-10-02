package Controller;

import Model.Building;
import Model.Room;
import Model.TagData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddLocationsController implements Initializable {
    @FXML
    public Button add_building_btn;
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
    @FXML private ComboBox room_buiding_dop;
    @FXML private  TextField notReservedTime_text;

    @FXML private TableView<Model.Building> building_table;

    @FXML private TableColumn<Building,String> building_id_row;
    @FXML private TableColumn<Building,String> building_name_row;

    //Time picker
    @FXML private ComboBox hours;
    @FXML private ComboBox minitues;
    @FXML private ComboBox meridiem;





    @FXML private TableView<Room> room_table;
    @FXML private TableColumn<Room,String> room_id;
    @FXML private TableColumn<Room,String> room_name;
    @FXML private TableColumn<Room,String> room_building;
    @FXML private TableColumn<Room,String> room_capacity;
    @FXML private TableColumn<Room,String> room_NotReserd_colum;






    Building Building ;
    Room Rooms;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Building=new Building();
        Rooms=new Room();
        setTimeValues();


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

        add_room_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String roomname=addRoom_text.getText().trim();
                String roomcapasity=room_capacity_text.getText().trim();
                Building building = (Building) room_buiding_dop.getSelectionModel().getSelectedItem();
                String building_room =building.getId();

                setTimeValues();





//                String notreservedtime=notReservedTime_text.getText().trim();
                String notreservedtime=timeGenerator();


                System.out.println(building_room);
                if (roomname.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Add Rooms is Empty ");
                    alert.showAndWait();
                }else{
                    Room room =new Room();
                    room.setRoomName(roomname);
                    room.setCapacity(roomcapasity);
                    room.setBuildings_idbuildings(building_room);
                    room.setNotreservedtime(notreservedtime);
                    room.CreateRoom();
                    addRoom_text.clear();
                    room_capacity_text.clear();
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
                String roomID=room_table.getSelectionModel().getSelectedItem().getIdroom();

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
                String roomID=(room_table.getSelectionModel().getSelectedItem().getIdroom());
                String roomName=(room_table.getSelectionModel().getSelectedItem().getRoomName());
                String roomCapasity=(room_table.getSelectionModel().getSelectedItem().getCapacity());
                String roomBuilding=(room_table.getSelectionModel().getSelectedItem().getBuildings_idbuildings());
                String roomNotreservedTime=(room_table.getSelectionModel().getSelectedItem().getNotreservedtime());


                if(!roomID.isEmpty()){
                    togalUpdateAndAddButtonRoom();
                    addRoom_text.setText(roomName);
                    room_capacity_text.setText(roomCapasity);

//                    notReservedTime_text.setVisible(true);
//                    hours.setVisible(false);
//                    minitues.setVisible(false);

                    ToggleTime();

                    notReservedTime_text.setText(roomNotreservedTime);



                    update_room_btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String newRoomvalue=addRoom_text.getText().trim();
                            String newRoomCapasityvalue=room_capacity_text.getText().trim();
                            String newRoomNotReservdTime=notReservedTime_text.getText().trim();
                            String newBuildingID;
                            Building dropDownValue= (Model.Building) room_buiding_dop.getSelectionModel().getSelectedItem();

                           if(dropDownValue==null){
                               System.out.println("Table Values "+roomBuilding);
                               newBuildingID=roomBuilding;
                           }else{
                               if(dropDownValue.getId()==roomBuilding){
                                   System.out.println("Table Values "+roomBuilding);
                                   newBuildingID=roomBuilding;


                               }else{
                                   System.out.println("DropDown Values "+dropDownValue.getId());
                                   newBuildingID=dropDownValue.getId();

                               }
                           }

                            if(!newRoomvalue.isEmpty()){

                                Rooms.UpdateData(roomID,newRoomvalue,newRoomCapasityvalue, newBuildingID,newRoomNotReservdTime);
                                    System.out.println(roomID+" "+newRoomvalue+" "+newRoomCapasityvalue+" "+newBuildingID+" "+newRoomNotReservdTime);
                                    System.out.println("Room updated");

                            }else{
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Rooms is Empty ");
                                alert.showAndWait();
                            }




                            showRoomsTable();
                            togalUpdateAndAddButtonRoom();
                            addRoom_text.clear();
                            room_capacity_text.clear();
                            notReservedTime_text.clear();
                            ToggleTime();



                        }
                    });

                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select a any row ");
                    alert.showAndWait();
                }

            }
        });

        room_buiding_dop.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                Building building =(Building) room_buiding_dop.getSelectionModel().getSelectedItem();
                System.out.println("Drop down relece");

                System.out.println(building.getId());
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
        room_id.setCellValueFactory(new PropertyValueFactory<>("idroom"));
        room_name.setCellValueFactory(new  PropertyValueFactory<>("roomName"));
        room_capacity.setCellValueFactory(new  PropertyValueFactory<>("capacity"));
        room_building.setCellValueFactory(new  PropertyValueFactory<>("buildings_idbuildings"));
        room_NotReserd_colum.setCellValueFactory(new PropertyValueFactory<>("notreservedtime"));


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
        System.out.println("call togalUpdateAndAddButtonRoom metjod ");
        update_room_btn.setVisible(!update_room_btn.isVisible());
        add_room_btn.setVisible(!add_room_btn.isVisible());

    }


    public void getValueforBuldinnameDropdown(){

        try {
            room_buiding_dop.setItems(Building.getObservebleList(Building.getAllData()));




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void setTimeValues(){
        ObservableList<String> minitusList = FXCollections.observableArrayList();
        ObservableList<String> hoursList = FXCollections.observableArrayList();


        for(int i=0;i<60;i++){
            minitusList.add(String.valueOf(i+1));
        }

        for(int i=0;i<24;i++){
            hoursList.add(String.valueOf(i+1));
        }



        minitues.setItems(minitusList);
        hours.setItems(hoursList);


    }


    public String timeGenerator(){

         String hours_time = hours.getSelectionModel().getSelectedItem().toString();
         String minitues_time = minitues.getSelectionModel().getSelectedItem().toString();


        return  hours_time+":"+minitues_time+":00";
    }

    public void ToggleTime(){
        notReservedTime_text.setVisible(!notReservedTime_text.isVisible());
        hours.setVisible(!hours.isVisible());
        minitues.setVisible(!minitues.isVisible());
    }




}
