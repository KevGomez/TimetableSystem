/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.LocationPrefernceDAO.TaghasLocationDAO;
import Model.DisplaySession;
import Model.LecturerDB;
import Model.LecturerModel;
import Model.Room;
import Model.Session;
import Model.SessionHasRoom;
import Model.StudentData;
import Model.SubjectDB;
import Model.SubjectModel;
import Model.TagData;
import Model.TagHasRooms;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import timetablesystem.DataBaseHandler.DBSqlHandler;

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
           
                   @FXML
    private TextField search;
           
        @FXML private TableView<DisplaySession> sessiontable;
    @FXML private TableColumn<DisplaySession,String> Lecturers;
    @FXML private TableColumn<DisplaySession,String> Tag;
    @FXML private TableColumn<DisplaySession,String> Subject;
    @FXML private TableColumn<DisplaySession,String> Group;
    @FXML private TableColumn<DisplaySession,String> Students;
    @FXML private TableColumn<DisplaySession,String> Duration;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getAllSession();
        getDataLecturers();
        getDataSubjects();
        getTagData();
        getStudentData();
        
          FilterData();

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
        
        if(list.getItems().size()==0)
        {
            list.getItems().add(lecturers.getValue());
        }else{
            
        int count=0;
        
        for(int i=0;i<list.getItems().size();i++)
        {
       
            if(list.getItems().get(i).equals(lecturers.getValue()))
            {
                count=count+1;
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("failure");
//        alert.setHeaderText(null);
//        alert.setContentText("lecturer already added!");
//
//        alert.showAndWait();
               
//            }else{
//                
//                 list.getItems().add(lecturers.getValue());
//            }
        }
        
         }
        
        if(count>0)
        {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("failure");
        alert.setHeaderText(null);
        alert.setContentText("lecturer already added!");

        alert.showAndWait();
        }else{
            list.getItems().add(lecturers.getValue());
            
        }
            
        }
         
         
         
//         System.out.println(list.getItems().get(0));
//         
//         LecturerModel l1=(LecturerModel) list.getItems().get(0);
//         int lectureid=l1.getId();
//         
//         System.out.println(lectureid);
         
         
    }
    
    @FXML
    public void InsertData(MouseEvent event)throws IOException
    {
     int count=0;
        String sql="insert into sessions (numberofstudents, duration, consecutive, notavailble , tag_idtag , lecturer_idemployee, subjects_idsubjects, students_grp_idstudents_grp,room_idroom,porder) values (?,?,?,?,?,?,?,?,?,?)";
        String sql2="insert into sessiondisplay (lecturers, subject, code, tag, studgroup , students , duration) values (?,?,?,?,?,?,?)";

        
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
                           
                  PreparedStatement st1 =con.prepareStatement(sql2);
                  
                        st1.setString(1,list.getItems().toString());
			st1.setString(2,subj.getSubject());
			st1.setString(3,subj.getCode());
			st1.setString(4,tag.getName());
			st1.setString(5,stud.getSgrp_id());
                        st1.setString(6,noOfStudents.getText());
			st1.setString(7,duration.getText());
                           
                        st1.executeUpdate();
                           
                        if(count>0)
                        {
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Data has been saved!");

        alert.showAndWait();
         getAllSession();
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
        
        
              Lecturers.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
               Subject.setCellValueFactory(new PropertyValueFactory<>("subjects"));
        Tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        Group.setCellValueFactory(new PropertyValueFactory<>("students_grp"));
        Students.setCellValueFactory(new PropertyValueFactory<>("numberofstudents"));
        Duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        
        
        try{
        sessiontable.setItems(getObservebleSessionDsiplay2(getAllData2()));
       FilterData();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
}
    
    
       public ResultSet getAllData2(){
        
        Connection conn = connect();
        String getDataQuery="\n" +
                " SELECT l.name,sb.subject,t.name,st.sgrp_id,s.numberofstudents,s.duration\n" +
                " FROM  sessions s,lecturers l,tag t,subject sb,students_grp st\n" +
                " WHERE s.tag_idtag=t.idtag AND s.subjects_idsubjects = sb.idsubjects AND s.lecturer_idemployee =l.idemployee AND s.students_grp_idstudents_grp=st.idstudents_grp";
        
        String query = "SELECT * FROM sessiondisplay";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            //DisplaySession session;
            return rs;
      
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return null;
    }
       
       
    
             public static ObservableList<DisplaySession> getObservebleSessionDsiplay2(ResultSet resultSet) throws SQLException {
        ObservableList<DisplaySession> sessions = FXCollections.observableArrayList();

        while (resultSet.next()){
            sessions.add(new DisplaySession(resultSet.getString("lecturers"),resultSet.getString("subject")+" -"+resultSet.getString("code"),resultSet.getString("studgroup"),resultSet.getString("tag"),parseInt(resultSet.getString("students")),resultSet.getString("duration")));
            
        }

        return  sessions;
    }
           
           
           
           public ResultSet getAllData(){
        
        Connection conn = connect();
        String getDataQuery="\n" +
                " SELECT l.name,sb.subject,t.name,st.sgrp_id,s.numberofstudents,s.duration\n" +
                " FROM  sessions s,lecturers l,tag t,subject sb,students_grp st\n" +
                " WHERE s.tag_idtag=t.idtag AND s.subjects_idsubjects = sb.idsubjects AND s.lecturer_idemployee =l.idemployee AND s.students_grp_idstudents_grp=st.idstudents_grp";
        
        String query = "SELECT * FROM sessions";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(getDataQuery);
            //DisplaySession session;
            return rs;
      
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return null;
    }
       
       
           public static ObservableList<DisplaySession> getObservebleSessionDsiplay(ResultSet resultSet) throws SQLException {
        ObservableList<DisplaySession> sessions = FXCollections.observableArrayList();

        while (resultSet.next()){
            sessions.add(new DisplaySession(resultSet.getString("name"),resultSet.getString("subject"),resultSet.getString(3),resultSet.getString("sgrp_id"),resultSet.getInt("numberofstudents"),resultSet.getString("duration")));
            
        }

        return  sessions;
    }
           
           
           
           
           
           
            public void FilterData(){
        try {
            FilteredList<DisplaySession> sessionFilteredList =new FilteredList<DisplaySession>(getObservebleSessionDsiplay2(getAllData2()),b->true);
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                sessionFilteredList.setPredicate(session->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();

        
                    if(session.getLecturer().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(session.getSubjects().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    else if(session.getStudents_grp().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else{
                        return false;
                    }


                });

                SortedList<DisplaySession> sortedList=new SortedList<>(sessionFilteredList);
                sortedList.comparatorProperty().bind(sessiontable.comparatorProperty());
                sessiontable.setItems(sessionFilteredList);

            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    
 
}
