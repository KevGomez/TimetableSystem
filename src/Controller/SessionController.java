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
import Model.Session;
import Model.SessionHasRoom;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
           
           
        @FXML private TableView<Session> sessiontable;
    @FXML private TableColumn<Session,String> Lecturers;
    @FXML private TableColumn<Session,String> Tag;
    @FXML private TableColumn<Session,String> Subject;
    @FXML private TableColumn<Session,String> Group;
    @FXML private TableColumn<Session,String> Students;
    @FXML private TableColumn<Session,String> Duration;
        
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
         
         System.out.println(list.getItems().get(0));
         
         LecturerModel l1=(LecturerModel) list.getItems().get(0);
         int lectureid=l1.getId();
         
         System.out.println(lectureid);
         
         
    }
    
    @FXML
    public void InsertData(MouseEvent event)throws IOException
    {
     int count=0;
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
			
			   for(int i=0;i<list.getItems().size();i++)
        {
            
             LecturerModel l1=(LecturerModel) list.getItems().get(i);
         int lectureid=l1.getId();
            
        

			PreparedStatement st =con.prepareStatement(sql);
			
			st.setInt(1,parseInt(noOfStudents.getText()));
			st.setString(2,duration.getText());
			st.setString(3,"1");
			st.setString(4,"1");
			st.setInt(5,tagid);
                        st.setInt(6,lectureid);
			st.setInt(7,subjid);
			st.setInt(8,studid);
			st.setInt(9,1);
                        st.setInt(10,1);
			
			
			count=st.executeUpdate();
		}	
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
                   
			con.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connection value"+e);
		}
       
        
    
    }
    
    
    
    public void getAllSession(){
        
        
              Lecturers.setCellValueFactory(new PropertyValueFactory<>("idsessions"));
        Tag.setCellValueFactory(new PropertyValueFactory<>("idroom"));
        Subject.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        Group.setCellValueFactory(new PropertyValueFactory<>("semester"));
        Students.setCellValueFactory(new PropertyValueFactory<>("subjects"));
        Duration.setCellValueFactory(new PropertyValueFactory<>("tag"));
        
        
    }
    
    
       public ObservableList<Session> getAllData(){
        ObservableList<Session> sessionlist = FXCollections.observableArrayList();
        Connection conn = connect();
        
        String query = "SELECT * FROM sessions";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Session session;
            while(rs.next()){
               // button[1]=new Button();
                //session = new LecturerModel(rs.getInt("idemployee"),rs.getString("name"),rs.getString("lectureID"),rs.getString("faculty"),rs.getString("department"),rs.getString("center"),rs.getString("building"),rs.getString("level"),rs.getString("rank"));
               // lecturelist.add(lecturers);
            }
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return sessionlist;
    }
   
    
 
}
