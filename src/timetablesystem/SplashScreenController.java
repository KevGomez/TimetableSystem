/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablesystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
/**
 * FXML Controller class
 *
 * @author Vidula
 */
public class SplashScreenController implements Initializable {

    @FXML
    private ProgressBar progress;
     @FXML
    private Text load;
      @FXML
     private Text percent;
    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//          Thread loader = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                   System.out.println("Started in run");
//                    progress.setProgress(10);
//                    percent.setText("0%");
//                    load.setText("Gathering Data");
//                    
//                    Thread.sleep(1000);
//
//                 
//                    progress.setProgress(25);
//                     load.setText("Access to Central DB");
//                   percent.setText("25%");
//                    Thread.sleep(1000);
//
//                    progress.setProgress(57);
//                    percent.setText("57%");
//                     load.setText("Check Code History");
//                    Thread.sleep(1000);
//
//                   
//                    progress.setProgress(68);
//                    percent.setText("68%");
//                    load.setText("Fetching Data");
//                    Thread.sleep(1000);
//
//                    
//                    progress.setProgress(10);
//                    percent.setText("95%");
//                    Thread.sleep(2000);
//
//                    
//                    progress.setProgress(25);
//                    percent.setText("100%");
//                    load.setText("Ready To Deploy");
//                    Thread.sleep(1000);
//                    
//                  
//                   showMain();
//                    
//                 
//
//                } catch (Exception ex) {
//                    System.out.println("Error" + ex);
//                }
//            }
//        });
//        loader.start();
//         System.out.println("inihed in run");
//        
//        
//             
//
//              
//    }    
    
      
      
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          Thread loader = new Thread(new Runnable() {
              
       
              
              
              
            @Override
            public void run() {
                try {
                   System.out.println("Started in run");
                    progress.setProgress(10);
                    percent.setText("0%");
                    load.setText("Gathering Data");
                    
                    Thread.sleep(1000);

                 
                    progress.setProgress(25);
                     load.setText("Access to Central DB");
                   percent.setText("25%");
                    Thread.sleep(1000);

                    progress.setProgress(57);
                    percent.setText("57%");
                     load.setText("Check Code History");
                    Thread.sleep(1000);

                   
                    progress.setProgress(68);
                    percent.setText("68%");
                    load.setText("Fetching Data");
                    Thread.sleep(1000);

                    
                    progress.setProgress(10);
                    percent.setText("95%");
                    Thread.sleep(2000);

                    
                    progress.setProgress(25);
                    percent.setText("100%");
                    load.setText("Ready To Deploy");
                    Thread.sleep(1000);
                    
                  Platform.runLater(new Runnable() {
            @Override public void run() {
                   showMain();
            }});
                 

                } catch (Exception ex) {
                    System.out.println("Error" + ex);
                }
            }
        });
        loader.start();
         System.out.println("inihed in run");
        
        
             

              
    }    
    
//    platform.runLater(new Runnable(){
//        public void run(){
//            
//        }
//    });
    
    public void showMain(){
        System.err.println("Show main");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
