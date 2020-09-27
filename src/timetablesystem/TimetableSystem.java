/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablesystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import timetablesystem.DataBaseHandler.DBHandler;

/**
 *
 * @author Vidula
 */
public class TimetableSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        Scene scene = new Scene(root,1100,650);
        
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        //stage.centerOnScreen()
        // ;
        stage.setTitle("Time Table Management");

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DBHandler dbHandler = new DBHandler();
        dbHandler.createTables();
        dbHandler.insertSampleData();
        launch(args);
    }
    
}
