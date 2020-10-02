/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Lecture;
import Model.LecturerModel;
import Model.NotAvailable;
import Model.Room;
import Model.Session;
import Model.StudentData;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class PsessionsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //parallel
    @FXML  
    private ComboBox sessionID;
    
    @FXML
    private TextField orderID;
    
    @FXML 
    private Button addParallel;
    
    @FXML
    private TableColumn<Session, String> sessionIDCol;
    
    @FXML
    private TableColumn<Session, String> orderIDCol;
    
    @FXML
    private TableView<Session> parTable;
    
    //cons
    @FXML  
    private ComboBox lectureSessionID;
    @FXML  
    private ComboBox tuteSessionID;
    
    @FXML
    private TableView<Session> consTable;
    
    @FXML
    private TableColumn<Session, String> consSessionCol;
    
    @FXML
    private TableColumn<Session, String> consConsCol;
    
    
    //not available
    @FXML  
    private ComboBox categoryID, categoryID1, categoryID2;
    
    @FXML  
    private DatePicker date, date1, date2;
    
    @FXML
    private TextField time, time1, time2;
    
    @FXML
    private TableColumn<NotAvailable, String> naIdCol;
    
    @FXML
    private TableColumn<NotAvailable, String> naCatName;
    
    @FXML
    private TableColumn<NotAvailable, String> naCatID;
    
    @FXML
    private TableColumn<NotAvailable, String> naDate;
    
    @FXML
    private TableColumn<NotAvailable, String> naTime;
    
    @FXML
    private TableView<NotAvailable> naMainTable;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //for parallel
        loadSessionData();
        
        try {
            getParData();
        } catch (SQLException ex) {
            Logger.getLogger(PsessionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //for cons
        loadLectSessionData();
        loadTuteSessionData();
        
        try {
            getConsData();
        } catch (SQLException ex) {
            Logger.getLogger(PsessionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //for not available
        loadSessionCatIds();
        loadLectureCatIds();
        loadGroupCatIds();
        
        try {
            getNADataToTable();
        } catch (SQLException ex) {
            Logger.getLogger(PsessionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:sqlserver://spmservercode4.database.windows.net:1433;database=SPM_TIMETABLE;user=spmcode4@spmservercode4;password=code4@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30");
            return conn;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    //for parallel
    public ObservableList<Session> getSessionData() throws SQLException{
        ObservableList<Session> sessionList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        
        String query = "SELECT * FROM sessions";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Session session = null;
            
            while(rs.next()){
                session = new Session(rs.getInt("idsessions"), rs.getInt("numberofstudents"), rs.getString("duration"), rs.getString("consecutive"), rs.getString("notavailble"), rs.getInt("tag_idtag"), rs.getInt("lecturer_idemployee"), rs.getInt("subjects_idsubjects"), rs.getInt("students_grp_idstudents_grp"), rs.getInt("room_idroom"), rs.getInt("porder"));
                sessionList.add(session);
            }
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return sessionList;
    }
    
    //for parallel
    public void loadSessionData(){
        try {
            sessionID.setItems(getSessionData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    //for parallel
    @FXML
    public void updateParallel(MouseEvent event) throws IOException, SQLException{
        
        Session sessionGotID =(Session) sessionID.getSelectionModel().getSelectedItem();
        int SessionID = sessionGotID.getIdsessions();
        
        String gotID = orderID.getText().toString();
        int idToInt = Integer.parseInt(gotID);
        
        String query = "UPDATE sessions SET porder='"+idToInt+"' WHERE idsessions="+SessionID+" ";
        
        try{
            executeQuery(query);
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        getParData();
        
    }
    
    //for parallel
    public void getParData() throws SQLException{
        ObservableList<Session> sessionList = getSessionData();
        sessionIDCol.setCellValueFactory(new PropertyValueFactory<Session, String>("idsessions"));
        orderIDCol.setCellValueFactory(new PropertyValueFactory<Session, String>("porder"));
        
        parTable.setItems(sessionList);
    }
    
    //for cons
    public ObservableList<Session> getLectSessionData() throws SQLException{
        ObservableList<Session> sessionList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        
        String query = "SELECT * FROM sessions WHERE tag_idtag = 1";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Session session = null;
            
            while(rs.next()){
                session = new Session(rs.getInt("idsessions"), rs.getInt("numberofstudents"), rs.getString("duration"), rs.getString("consecutive"), rs.getString("notavailble"), rs.getInt("tag_idtag"), rs.getInt("lecturer_idemployee"), rs.getInt("subjects_idsubjects"), rs.getInt("students_grp_idstudents_grp"), rs.getInt("room_idroom"), rs.getInt("porder"));
                sessionList.add(session);
            }
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return sessionList;
    }
    
    //for cons
    public void loadLectSessionData(){
        try {
            lectureSessionID.setItems(getLectSessionData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    //for cons
    public ObservableList<Session> getTuteSessionData() throws SQLException{
        ObservableList<Session> sessionList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        
        String query = "SELECT * FROM sessions WHERE tag_idtag = 2";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Session session = null;
            
            while(rs.next()){
                session = new Session(rs.getInt("idsessions"), rs.getInt("numberofstudents"), rs.getString("duration"), rs.getString("consecutive"), rs.getString("notavailble"), rs.getInt("tag_idtag"), rs.getInt("lecturer_idemployee"), rs.getInt("subjects_idsubjects"), rs.getInt("students_grp_idstudents_grp"), rs.getInt("room_idroom"), rs.getInt("porder"));
                sessionList.add(session);
            }
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return sessionList;
    }
    
    //for cons
    public void loadTuteSessionData(){
        try {
            tuteSessionID.setItems(getTuteSessionData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    //for cons
    @FXML
    public void updateConsecutive(MouseEvent event) throws IOException, SQLException{
        
        Session sessionGotID =(Session) lectureSessionID.getSelectionModel().getSelectedItem();
        int SessionID = sessionGotID.getIdsessions();
        
        Session sessionTuteID =(Session) tuteSessionID.getSelectionModel().getSelectedItem();
        int TuteSessionID = sessionTuteID.getIdsessions();
        
        
        String query = "UPDATE sessions SET consecutive="+TuteSessionID+" WHERE idsessions="+SessionID+" ";
        
        try{
            executeQuery(query);
            
             
        }catch(Exception e){
                e.printStackTrace();
        }
        
        getConsData();
        
    }
    
    //for cons
    public void getConsData() throws SQLException{
        ObservableList<Session> sessionList = getSessionData();
        consSessionCol.setCellValueFactory(new PropertyValueFactory<Session, String>("idsessions"));
        consConsCol.setCellValueFactory(new PropertyValueFactory<Session, String>("consecutive"));
        
        consTable.setItems(sessionList);
    }

    //for not available
    public void loadSessionCatIds(){
        try {
            categoryID.setItems(getSessionData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public ObservableList<LecturerModel> getLectureData() throws SQLException{
        ObservableList<LecturerModel> lectureList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        
        String query = "SELECT * FROM lecturers";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            LecturerModel lecture = null;
            
            while(rs.next()){
                lecture = new LecturerModel(rs.getInt("idemployee"), rs.getString("name"), rs.getString("lectureID"), rs.getString("faculty"), rs.getString("department"), rs.getString("center"), rs.getString("building"), rs.getString("level"), rs.getString("rank"));
                lectureList.add(lecture);
            }
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return lectureList;
    }
    
    
    public void loadLectureCatIds(){
        try {
            categoryID2.setItems(getLectureData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public ObservableList<StudentData> getGroupData() throws SQLException{
        ObservableList<StudentData> groupList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        
        String query = "SELECT * FROM students_grp";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            StudentData group = null;
            
            while(rs.next()){
                group = new StudentData(rs.getString("sgrp_id"));
                groupList.add(group);
            }
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return groupList;
    }
    
    public void loadGroupCatIds(){
        try {
            categoryID1.setItems(getGroupData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    
    public void insertSessionNA(MouseEvent event) throws IOException, SQLException{
        
        Session sessionGotID =(Session) categoryID.getSelectionModel().getSelectedItem();
        int SessionID = sessionGotID.getIdsessions();
        
        String stringID = Integer.toString(SessionID);
        String dateSession = date.getValue().toString();
        String timeSession = time.getText();
        
        String query = "INSERT INTO notAvailable values('Session', '"+stringID+"', '"+dateSession+"', '"+timeSession+"')";
        
        try{
            executeQuery(query);
            
              
        }catch(Exception e){
                e.printStackTrace();
        }
        
        getNADataToTable();
        
    }
    
    public ObservableList<NotAvailable> getNAData() throws SQLException{
        ObservableList<NotAvailable> NaList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        
        String query = "SELECT * FROM notAvailable";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            NotAvailable na = null;
            
            while(rs.next()){
                na = new NotAvailable(rs.getInt("id"), rs.getString("catName"), rs.getString("catID"), rs.getString("date"), rs.getString("time"));
                NaList.add(na);
            }
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return NaList;
    }
    
    public void getNADataToTable() throws SQLException{
        ObservableList<NotAvailable> NaList = getNAData();
        naIdCol.setCellValueFactory(new PropertyValueFactory<NotAvailable, String>("id"));
        naCatName.setCellValueFactory(new PropertyValueFactory<NotAvailable, String>("catName"));
        naCatID.setCellValueFactory(new PropertyValueFactory<NotAvailable, String>("catID"));
        naDate.setCellValueFactory(new PropertyValueFactory<NotAvailable, String>("date"));
        naTime.setCellValueFactory(new PropertyValueFactory<NotAvailable, String>("time"));
        
        naMainTable.setItems(NaList);
    }
    
    public void insertGroupNA(MouseEvent event) throws IOException, SQLException{
        
        StudentData groupID =(StudentData) categoryID1.getSelectionModel().getSelectedItem();
        String toGroupID = groupID.getSgrp_id();
        
        String dateSession = date1.getValue().toString();
        String timeSession = time1.getText();
        
        String query = "INSERT INTO notAvailable values('Group', '"+toGroupID+"', '"+dateSession+"', '"+timeSession+"')";
        
        try{
            executeQuery(query);
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        getNADataToTable();
        
    }
    
    public void insertLectureNA(MouseEvent event) throws IOException, SQLException{
        
        LecturerModel lects =(LecturerModel) categoryID2.getSelectionModel().getSelectedItem();
        String name = lects.getName();
        
        String dateSession = date2.getValue().toString();
        String timeSession = time2.getText();
        
        String query = "INSERT INTO notAvailable values('Lecturer', '"+name+"', '"+dateSession+"', '"+timeSession+"')";
        
        try{
            executeQuery(query);
            
      
        }catch(Exception e){
                e.printStackTrace();
        }
        
        getNADataToTable();
        
    }
    

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Success");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Operation Success!");

                                    alert.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Error");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Operation failed!");
                                    alert.showAndWait();
        }
    }
    
}
