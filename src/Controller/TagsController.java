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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import Model.TagData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import timetablesystem.MainController;
/**
 * FXML Controller class
 *
 * @author Hp
 */



public class TagsController implements Initializable {
    
    
    @FXML
    private TextField name;
   
    @FXML
    private TableColumn<TagData, String> namecol;
    @FXML
    private TableColumn<TagData, String> editcol;
    
    @FXML
    private TableColumn<TagData, String> deletecol;
    
    @FXML
    private TableView<TagData> mainTable;
    
    private String tempData;

    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getData();
        editableCols();
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
    
    public ObservableList<TagData> getAllData(){
        ObservableList<TagData> tagList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        
        String query = "SELECT * FROM tag";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            TagData tags = null;
            
            while(rs.next()){
                tags = new TagData(rs.getInt("idtag"), rs.getString("name"));
                tagList.add(tags);
            }
            
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return tagList;
    }
    
    public void getData(){
        ObservableList<TagData> list = getAllData();
        namecol.setCellValueFactory(new PropertyValueFactory<TagData, String>("name"));
        
        Callback<TableColumn<TagData,String>,TableCell<TagData,String>> cellFactory=(param)->{
                  
                  final TableCell<TagData,String> cell=new TableCell<TagData,String>(){
                  
                    @Override
                    public void updateItem(String item, boolean empty){

                        super.updateItem(item, empty);

                        if(empty){
                            setGraphic(null);
                            setText(null);
                        }else{

                            final Button editButton=new Button("Edit");
                            editButton.setOnAction(event ->{

                            TagData sd=getTableView().getItems().get(getIndex());
                            
                            String query3 = "UPDATE tag SET name='"+sd.getName()+"' WHERE idtag="+sd.getId()+" ";
                            executeQuery(query3);
                            
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
        
        Callback<TableColumn<TagData,String>,TableCell<TagData,String>> cellFactory1=(param)->{
                  
                  final TableCell<TagData,String> cell1=new TableCell<TagData,String>(){
                  
                        @Override
                        public void updateItem(String item, boolean empty){

                            super.updateItem(item, empty);

                            if(empty){
                                setGraphic(null);
                                setText(null);
                            }else{

                                final Button deleteButton=new Button("Delete");
                                deleteButton.setOnAction(event ->{

                                TagData sd=getTableView().getItems().get(getIndex());

                                String query2 = "DELETE FROM tag WHERE idtag = "+sd.getId()+"";
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
        
        String query2 = "select max(idtag) from tag";
        int key, pkey;
        Connection conn = getConnection();
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query2);
            
            while(rs.next()){
             key = rs.getInt(1);
                
            pkey = key + 1;
            String query = "INSERT INTO tag(name) VALUES ('"+name.getText()+"')";
            executeQuery(query);
       
            }
            }catch(Exception e){
                e.printStackTrace();
        }
        
        name.setText(" ");
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Data has been saved!");

        alert.showAndWait();
        getData();
    }
    
    private void editableCols(){
        namecol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        namecol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
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
