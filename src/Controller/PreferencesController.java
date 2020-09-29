package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import timetablesystem.MainController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreferencesController implements Initializable {
    @FXML private Button tag_room_btn;
    @FXML private  Button lecture_room_btn;
    @FXML private  Button subgroup_room_btn;
    @FXML private  Button session_room_btn;

    @FXML private HBox loadPane;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LoadPereferenceViews("/View/TagPreference.fxml");

        tag_room_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                LoadPereferenceViews("/View/TagPreference.fxml");

            }
        });

        lecture_room_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoadPereferenceViews("/View/LecturerPreference.fxml");
            }
        });

        subgroup_room_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoadPereferenceViews("/View/StudetSubGroupPreference.fxml");
            }
        });

        session_room_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoadPereferenceViews("/View/SessionPreference.fxml");
            }
        });



    }


    public void LoadPereferenceViews(String path){
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource(path));
            loadPane.getChildren().clear();
            loadPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
