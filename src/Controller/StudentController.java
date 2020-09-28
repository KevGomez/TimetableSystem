/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import Model.StudentData;
/**
 * FXML Controller class
 *
 * @author Hp
 */



public class StudentController implements Initializable {
    
    @FXML
    private TextField year;
    @FXML
    private TextField pro;
    @FXML
    private TextField grp_no;
    @FXML
    private TextField sgrp_no;
    @FXML
    private TableColumn<StudentData, String> yearcol;
    @FXML
    private TableColumn<StudentData, String> procol;
    @FXML
    private TableColumn<StudentData, String> grpnocol;
    @FXML
    private TableColumn<StudentData, String> grpidcol;
    @FXML
    private TableColumn<StudentData, String> sgrpnocol;
    @FXML
    private TableColumn<StudentData, String> sgrpidcol;
    @FXML
    private TableColumn<StudentData,String> editcol;
    
    @FXML
    private TableColumn<StudentData,String> deletecol;
    
    @FXML
    private TableView<StudentData> mainTable;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getData();
        editableCols();
    }
        
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timeTableSystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            return conn;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public ObservableList<StudentData> getAllData(){
        ObservableList<StudentData> studentList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        
        String query = "SELECT * FROM students";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            StudentData students = null;
            
            while(rs.next()){
                students = new StudentData(rs.getInt("id"), rs.getString("year"), rs.getString("pro"), rs.getString("grp_no"), rs.getString("grp_id"), rs.getString("sgrp_no"), rs.getString("sgrp_id"));
                studentList.add(students);
            }
            
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return studentList;
    }
    
    public void getData(){
        ObservableList<StudentData> list = getAllData();
        yearcol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("year"));
        procol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("pro"));
        grpnocol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("grp_no"));
        grpidcol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("grp_id"));
        sgrpnocol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("sgrp_no"));
        sgrpidcol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("sgrp_id"));
        
        Callback<TableColumn<StudentData,String>,TableCell<StudentData,String>> cellFactory=(param)->{
                  
                  final TableCell<StudentData,String> cell=new TableCell<StudentData,String>(){
                  
                @Override
                public void updateItem(String item, boolean empty){
                    
                    super.updateItem(item, empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        
                        final Button editButton=new Button("Edit");
                        editButton.setOnAction(event ->{
                        
                        StudentData sd=getTableView().getItems().get(getIndex());
                        
                        String tempGrp_id = sd.getYear() + "." + sd.getPro() + "." + sd.getGrp_no();
                        String tempSgrp_id = sd.getYear() + "." + sd.getPro() + "." + sd.getGrp_no() + "." + sd.getSgrp_no();
                        String query3 = "UPDATE students SET year='"+sd.getYear()+"', pro='"+sd.getPro()+"', grp_no='"+sd.getGrp_no()+"', grp_id='"+tempGrp_id+"', sgrp_no='"+sd.getSgrp_no()+"', sgrp_id='"+tempSgrp_id+"' WHERE id="+sd.getId()+" ";
                        executeQuery(query3);
                        
                        getData();
                            
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Data has been edited");

                        alert.showAndWait();
                        
                        });
                        
                        setGraphic(editButton);
                        setText(null);
                        
                        }
                    
                     };
                  
                   };
                  
                  
                          return cell;
                      };
              
        editcol.setCellFactory(cellFactory);
        mainTable.setItems(list);
        
        Callback<TableColumn<StudentData,String>,TableCell<StudentData,String>> cellFactory1=(param)->{
                  
                  final TableCell<StudentData,String> cell1=new TableCell<StudentData,String>(){
                  
                        @Override
                        public void updateItem(String item, boolean empty){

                            super.updateItem(item, empty);

                            if(empty){
                                setGraphic(null);
                                setText(null);
                            }else{

                                final Button deleteButton=new Button("Delete");
                                deleteButton.setOnAction(event ->{

                                StudentData sd=getTableView().getItems().get(getIndex());

                                String query2 = "DELETE FROM students WHERE id = "+sd.getId()+"";
                                executeQuery(query2);
                                
                                getData();

                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText(null);
                                alert.setContentText("Data has been deleted!");

                                alert.showAndWait();

                                });

                                setGraphic(deleteButton);
                                setText(null);

                                }

                             };

                           };
                  
                  
                          return cell1;
                      };
              
        deletecol.setCellFactory(cellFactory1);
        mainTable.setItems(list);
    }
    
    @FXML
    public void insert(MouseEvent event) throws IOException{
        
        String group_id = year.getText().toString() + "." + pro.getText().toString() + "." + grp_no.getText().toString();
        String sgroup_id = year.getText().toString() + "." + pro.getText().toString() + "." + grp_no.getText().toString() + "." + sgrp_no.getText().toString();
        String query = "INSERT INTO students VALUES (null, '"+year.getText()+"' , '"+pro.getText()+"' , '"+grp_no.getText()+"' , '"+group_id+"' , '"+sgrp_no.getText()+"' , '"+sgroup_id+"')";
        executeQuery(query);
        
        year.setText(" ");
        pro.setText(" ");
        grp_no.setText(" ");
        sgrp_no.setText(" ");
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Data has been saved!");

        alert.showAndWait();
        getData();
    }
    
    private void update(){
    
    }
    
    private void editableCols(){
        yearcol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        yearcol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setYear(e.getNewValue());
        });
        
        procol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        procol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPro(e.getNewValue());
        });
        
        grpnocol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        grpnocol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGrp_no(e.getNewValue());
        });
        
        grpidcol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        grpidcol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGrp_id(e.getNewValue());
        });
        
        sgrpnocol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        sgrpnocol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSgrp_no(e.getNewValue());
        });
        
        sgrpidcol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        sgrpidcol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSgrp_id(e.getNewValue());
        });
        
        
        mainTable.setEditable(true);
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
