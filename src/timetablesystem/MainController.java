/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablesystem;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Hansani Madushika
 */
public class MainController implements Initializable {

   
    @FXML
    private Label location;
    @FXML
    private Label lblComplan;
    @FXML
    private Label Subject;
    @FXML
    private Label Lecturers;
    @FXML
    private VBox loadPane;
    @FXML
    private Label lblPayment;
    
     public static AnchorPane rootP;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private ImageView imgNot;
    //@FXML
    //private ScrollPane scrolPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//     scrolPane.setVisible(false);
     setCurrentTime();
     setCurrentDate();
     
    } 
    
    
    private void setCurrentTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Calendar time = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        lblTime.setText(simpleDateFormat.format(time.getTime()));
                    }
                }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    
    }

    private void setCurrentDate() {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
        String curDate=format.format(date);
        lblDate.setText(curDate);
    }
    
    
        @FXML
    private void subjectOnClick(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/View/Subject.fxml"));
            this.loadPane.getChildren().clear();
            this.loadPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


        
           @FXML
    private void lblLecturersClick(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/View/Lecturer.fxml"));
            this.loadPane.getChildren().clear();
            this.loadPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void showWorkingTimeSettngs(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../View/WorkingTimeSettings.fxml"));
            this.loadPane.getChildren().clear();
            this.loadPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //location window opener
    @FXML
    private void onLocationClick(MouseEvent event) {
        System.out.println("Location call");
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("./../View/AddLocations.fxml"));
            this.loadPane.getChildren().clear();
            this.loadPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //statistic window opener
    @FXML
    private void onStatisticClick(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("./../View/Statistics.fxml"));
            this.loadPane.getChildren().clear();
            this.loadPane.getChildren().add(root);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //location preference opener
    @FXML
    private  void onLocationPreference(MouseEvent event){
        try{

            Parent root = FXMLLoader.load(this.getClass().getResource("./../View/LocationPreference.fxml"));
            this.loadPane.getChildren().clear();
            this.loadPane.getChildren().add(root);
        }catch (IOException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
