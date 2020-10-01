/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.LocationPrefernceDAO.TaghasLocationDAO;
import Model.LecturerDB;
import Model.LecturerModel;
import Model.Room;
import Model.StudentData;
import Model.SubjectDB;
import Model.SubjectModel;
import Model.TagData;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
           
           @FXML
    private ListView list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getDataLecturers();
        getDataSubjects();
        getTagData();
        getStudentData();
        
       
    }
    
        private static Connection connect() {
       
       Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:sqlserver://spmservercode4.database.windows.net:1433;database=SPM_TIMETABLE;user=spmcode4@spmservercode4;password=code4@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30");
            return conn;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }

	}
    
    public void getDataLecturers(){
        
        LecturerDB l2=new LecturerDB();
        ObservableList<LecturerModel> list = l2.getAllData();
        
        lecturers.setItems(l2.getAllData());
        
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
    
    
    public void getStudentData(){
        
        
        StudentController s1=new StudentController();
        
       
        
        groups.setItems( s1.getAllData());
    }
    
    
    public void OnLectureSelect(){
        
        
         list.getItems().add(lecturers.getValue());
    }
    
    @FXML
    public void InsertData(MouseEvent event)throws IOException
    {
        
        String sql="insert into sessions (numberofstudents, duration, consecutive, notavailble , tag_idtag , lecturer_idemployee, subjects_idsubjects, students_grp_idstudents_grp,room_idroom,porder) values (?,?,?,?,?,?,?,?,?,?)";
    
        
           TagData tag =(TagData) tags.getSelectionModel().getSelectedItem();
                int tagid=tag.getId();
                
                 LecturerModel lecturer =(LecturerModel) lecturers.getSelectionModel().getSelectedItem();
                int lectid=lecturer.getId();
                
                 SubjectModel subj =(SubjectModel) subject.getSelectionModel().getSelectedItem();
                int subjid=subj.getId();
                
                StudentData stud =(StudentData) groups.getSelectionModel().getSelectedItem();
                int studid=stud.getId();
        
           try {
        
        			Connection con = connect();
			
			

			PreparedStatement st =con.prepareStatement(sql);
			
			st.setInt(1,parseInt(noOfStudents.getText()));
			st.setString(2,duration.getText());
			st.setString(3,"1");
			st.setString(4,"1");
			st.setInt(5,tagid);
                        st.setInt(6,lectid);
			st.setInt(7,subjid);
			st.setInt(8,studid);
			st.setInt(9,1);
                        st.setInt(10,1);
			
			
			int count=st.executeUpdate();
			
                        if(count>0)
                        {
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Data has been saved!");

        alert.showAndWait();
                        }else{
                            
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("failure");
        alert.setHeaderText(null);
        alert.setContentText("error saving data!");

        alert.showAndWait();
                        }
                   
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connection value"+e);
		}
       
        
    
    }
    
    
    
 
}
