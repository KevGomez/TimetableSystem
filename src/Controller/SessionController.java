/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.LocationPrefernceDAO.TaghasLocationDAO;
import Model.LecturerDB;
import Model.LecturerModel;
import Model.SubjectDB;
import Model.SubjectModel;
import Model.TagData;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        getDataSubjects();
        getTagData();
    }
    
    
    public void getDataLecturers(){
        
        LecturerDB l2=new LecturerDB();
        ObservableList<LecturerModel> list = l2.getAllData();
        
        lecturers.setItems(list);
        
    }
    
    
    public void getDataSubjects(){
        
        
       SubjectDB s1=new SubjectDB();
        ObservableList<SubjectModel> list = s1.getAllSubjectData();
        
        subject.setItems(list);
    }
    
    
    public void getTagData(){
        
        try {
            //        TagsController t1=new TagsController();
//         ObservableList<TagData> list = t1.getAllData();
//         
//         tags.setItems(list);
            
            tags.setItems(TaghasLocationDAO.getObservebleTagList(TaghasLocationDAO.GetAllTags()));
        } catch (SQLException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
 
}
