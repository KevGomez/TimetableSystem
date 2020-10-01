/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //for parallel
        loadSessionData();
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
                session = new Session(rs.getInt("idsessions"), rs.getInt("numberofstudents"), rs.getString("duration"), rs.getString("consecutive"), rs.getString("notavailble"), rs.getInt("tag_idtag"), rs.getInt("lecturer_idemployee"), rs.getInt("subjects_idsubjects"), rs.getInt("students_grp_idstudents_grp"), rs.getInt("room_idroom"));
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
    public void updateParallel(MouseEvent event) throws IOException{
        
        Session sessionGotID =(Session) sessionID.getSelectionModel().getSelectedItem();
        int SessionID = sessionGotID.getIdsessions();
        
        System.out.println("Selected session ID is " + SessionID);
    }
    
}
