package Controller;

import Controller.LocationPrefernceDAO.SessionRoomDAO;
import Controller.LocationPrefernceDAO.StudentSubgroupRoomDAO;
import Controller.LocationPrefernceDAO.TaghasLocationDAO;
import Model.Room;
import Model.Session;
import Model.SessionHasRoom;
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

public class SessionPreferenceController implements Initializable {
    @FXML
    private ComboBox room_combo;
    @FXML  private ComboBox   session_combo;
    @FXML private Button add_preference_btn;
    @FXML private TextField tag_search_textfiled;

    @FXML private TableView<SessionHasRoom> sessionhasroom;
    @FXML private TableColumn<SessionHasRoom,String> idsessions;
    @FXML private TableColumn<SessionHasRoom,String> idroom;
    @FXML private TableColumn<SessionHasRoom,String> roomName;
    @FXML private TableColumn<SessionHasRoom,String> semester;
    @FXML private TableColumn<SessionHasRoom,String> subjects;
    @FXML private TableColumn<SessionHasRoom,String> tag;
    @FXML private TableColumn<SessionHasRoom,String> year;


    @FXML private Button tag_delete_btn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadRoomList();
        LoardSessionList();
        LoadSessionHasRoomTable();
        FilterData();

        add_preference_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Room room =(Room) room_combo.getSelectionModel().getSelectedItem();
                String Roomid=room.getIdroom();

                SessionHasRoom session=(SessionHasRoom) session_combo.getSelectionModel().getSelectedItem();
                String sessionid= Integer.toString(session.getIdsessions());

//
//                try {
//                    SessionHasRoom tag = (SessionHasRoom) SessionRoomDAO.getObservebleSessionHasRoomList(SessionRoomDAO.GetTagUseSessionID(sessionid));
//                    String tag_name=tag.getTag();

                    SessionRoomDAO sessionRoomDAO =new SessionRoomDAO();
                    sessionRoomDAO.InsertData(Roomid,sessionid);
                    System.out.println("Session and room id added");
                    LoadSessionHasRoomTable();

//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }


            }
        });
//
//        tag_delete_btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                String rooID= String.valueOf(sessionhasroom.getSelectionModel().getSelectedItem().getIdroom());
//                String sessionID= String.valueOf(sessionhasroom.getSelectionModel().getSelectedItem().getIdsessions());
//
//                SessionRoomDAO sessionRoomDAO=new SessionRoomDAO();
//                sessionRoomDAO.DeleteData(rooID,sessionID);
//            }
//        });


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
//            session_combo.setItems(SessionRoomDAO.getObservebleList(SessionRoomDAO.GetAllSessions()));
            session_combo.setItems(SessionRoomDAO.getObservebleSessionHasRoomList(SessionRoomDAO.GetAllSessionsAndRooms()));

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void  LoadSessionHasRoomTable(){
        idsessions.setCellValueFactory(new PropertyValueFactory<>("idsessions"));
        idroom.setCellValueFactory(new PropertyValueFactory<>("idroom"));
        roomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        subjects.setCellValueFactory(new PropertyValueFactory<>("subjects"));
        tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));





        try{
            if(SessionRoomDAO.getObservebleSessionHasRoomList(SessionRoomDAO.GetAllSessionsAndRooms())==null){
                System.out.println(" taghasLocationDAO is null");
            }else {
                System.out.println("Not Null");
                sessionhasroom.setItems(SessionRoomDAO.getObservebleSessionHasRoomList(SessionRoomDAO.GetAllSessionsAndRooms()));
                FilterData();
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    public void FilterData(){
        try {
            FilteredList<SessionHasRoom> sessionHasRoomsFilteredList =new FilteredList<SessionHasRoom>(SessionRoomDAO.getObservebleSessionHasRoomList(SessionRoomDAO.GetAllSessionsAndRooms()), b->true);
            tag_search_textfiled.textProperty().addListener((observable, oldValue, newValue) -> {
                sessionHasRoomsFilteredList.setPredicate(session_room->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();

                    if(session_room.getRoomName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(session_room.getTag().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(session_room.getSubjects().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(String.valueOf(session_room.getSemester()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(session_room.getRoomName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }

                    else{
                        return false;
                    }


                });

                SortedList<SessionHasRoom> sortedList=new SortedList<>(sessionHasRoomsFilteredList);
                sortedList.comparatorProperty().bind(sessionhasroom.comparatorProperty());
                sessionhasroom.setItems(sessionHasRoomsFilteredList);

            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
