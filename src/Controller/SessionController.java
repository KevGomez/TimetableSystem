/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.LecturerDB;
import Model.LecturerModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vidula
 */
public class SessionController implements Initializable {

    @FXML
    
    private ComboBox lecturers;
    
      @FXML
    
    private ComboBox tags;
      
        @FXML
    
    private ComboBox groups;
        
          @FXML
    
    private ComboBox subject;
    
          
             @FXML
    
    private ComboBox subgroups;
    
    
      @FXML
    private TextField noOfStudents;
      
           @FXML
    private TextField duration;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getDataLecturers();
    }
    
    
    public void getDataLecturers(){
        
        LecturerDB l2=new LecturerDB();
        ObservableList<LecturerModel> list = l2.getAllData();
        
        lecturers.setItems(list);
        
    }
    
}
