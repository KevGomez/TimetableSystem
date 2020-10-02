/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.LecturerDB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import Model.LecturerModel;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Vidula
 */
public class LecturerController implements Initializable {
  @FXML
    private TextField name;
    
     
      @FXML
    private TextField faculty;
      
       @FXML
    private TextField center;
       
        @FXML
    private TextField level;
        
         @FXML
    private TextField lectid;
         
          @FXML
    private TextField department;
          
           @FXML
    private TextField building;
           
            @FXML
    private TextField rank;
            
            private String arr[];
            
             @FXML
    private TableColumn<LecturerModel, String> namecol;
             
                    @FXML
    private TableColumn<LecturerModel, String> lectidcol;
                    
                           @FXML
    private TableColumn<LecturerModel, String> facultycol;
                           
                                  @FXML
    private TableColumn<LecturerModel, String> departmentcol;
                                  
                                     
                                  @FXML
    private TableColumn<LecturerModel, String> centercol;
                                  
                                  @FXML
    private TableColumn<LecturerModel, String> buildingcol;
                                  
                                     @FXML
    private TableColumn<LecturerModel, String> levelcol;
                                     
                                        @FXML
    private TableColumn rankcol;
                                        
                                        
                                        @FXML
    private TableColumn<LecturerModel, String> actioncol;
                                        
                                        
                                        @FXML
    private TableColumn<LecturerModel, String> deletecol;
                                  
                                           
                                        @FXML
    private TableView lecttable;
                                  
    private Button button;
         
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     getData();
     editColumns();
        
    }    
    
       
    ObservableList<LecturerModel> studentData = FXCollections.observableArrayList(
//            new LecturerModel("dfdss", "sdfsfs", "sfsdfsdfs", "dsfds", "dfds", "dfsfsd","ddd","ede")
    );
    
     @FXML
    public void insertData(MouseEvent event)throws IOException
    {
        //String rankval=level.getText().toString()+"."+lectid.getText().toString();
        arr=new String[10];
        
        arr[0]=name.getText().toString();
        arr[1]=faculty.getText().toString();
        arr[2]=center.getText().toString();
        arr[3]=level.getText().toString();
        arr[4]=lectid.getText().toString();
        arr[5]=department.getText().toString();
        arr[6]=building.getText().toString();
         arr[7]=rank.getText().toString();
         rank.setText(level.getText().toString()+"."+lectid.getText().toString());
         //System.out.println(level.getText().toString()+"."+lectid.getText().toString());
         
         if(name.getText().toString().equals("")||lectid.getText().toString().equals("")||faculty.getText().toString().equals("")||department.getText().toString().equals("")||center.getText().toString().equals("")||building.getText().toString().equals(""))
         {
             
            
            
           
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("failure");
        alert.setHeaderText(null);
        alert.setContentText("Cannot keep fields empty!");

        alert.showAndWait();
         }else{
             
         
         LecturerDB l1=new LecturerDB();
         
        int value= l1.createPatientAsForm(name.getText().toString(), lectid.getText().toString(), faculty.getText().toString(), department.getText().toString(), center.getText().toString(), building.getText().toString(), level.getText().toString(), level.getText().toString()+"."+lectid.getText().toString());
        
        if(value==1)
        {
           
getData();
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
        }
    
   
    }
    
    public void getData(){
        LecturerDB l2=new LecturerDB();
         ObservableList<LecturerModel> list = l2.getAllData();
          lecttable.setEditable(true);
       
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
         lectidcol.setCellValueFactory(new PropertyValueFactory<>("lectureid"));
          facultycol.setCellValueFactory(new PropertyValueFactory<>("faculty"));
           departmentcol.setCellValueFactory(new PropertyValueFactory<>("department"));
            centercol.setCellValueFactory(new PropertyValueFactory<>("center"));
             buildingcol.setCellValueFactory(new PropertyValueFactory<>("building"));
              levelcol.setCellValueFactory(new PropertyValueFactory<>("level"));
              rankcol.setCellValueFactory(new PropertyValueFactory<>("rank"));
              
//             actioncol.setCellValueFactory(new PropertyValueFactory<>("button"));
//             deletecol.setCellValueFactory(new PropertyValueFactory<>("buttondelete"));
        
              Callback<TableColumn<LecturerModel,String>,TableCell<LecturerModel,String>> cellFactory=(param)->{
                  
                  
                  final TableCell<LecturerModel,String> cell=new TableCell<LecturerModel,String>(){
                  
                @Override
                public void updateItem(String item, boolean empty){
                    
                    super.updateItem(item, empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        
                        final Button editButton=new Button("edit");
                        editButton.setOnAction(event ->{
                        
                        LecturerModel my=getTableView().getItems().get(getIndex());
                        
//                        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Success");
//        alert.setHeaderText(null);
//        alert.setContentText(my.getName());
//        
//        System.out.println("ID: " + my.getId());
//
//        alert.showAndWait();
                        
                       int updateval= l2.UpdatePatient(my.getId(), my.getName(), my.getLectureid(), my.getFaculty(), my.getDepartment(), my.getCenter(), my.getBuilding(), my.getLevel(),   my.getLevel()+"."+my.getLectureid());
                        
                       if(updateval==1)
                       {
                           getData();
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Updated");
        
       //System.out.println("ID: " + my.getId());
//
        alert.showAndWait();
                           
                       }
                       
                       
                        });
                        
                        setGraphic(editButton);
                        setText(null);
                        
                    }
                    
                };
                  
              };
                  
                  
                          return cell;
                      };
              
        actioncol.setCellFactory(cellFactory);
        
        
         
              Callback<TableColumn<LecturerModel,String>,TableCell<LecturerModel,String>> cellFactory2=(param)->{
                  
                  
                  final TableCell<LecturerModel,String> cell2=new TableCell<LecturerModel,String>(){
                  
                @Override
                public void updateItem(String item, boolean empty){
                    
                    super.updateItem(item, empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        
                        final Button editButton=new Button("Delete");
                        editButton.setOnAction(event ->{
                        
                            try{
                            
                           
                        LecturerModel my2=getTableView().getItems().get(getIndex());
                        l2.DeleteLecturer(my2.getId());
                        
                        getData();
//                        System.out.println("The value"+value);
//                        if(value==1)
//                        {
//                            getData();
//                            
//                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                            alert.setTitle("Success");
//                            alert.setHeaderText(null);
//                            alert.setContentText("Successfully deleted"+my2.getName());
//                            
//                            
//                            alert.showAndWait();
//                        }else{
//                            
//                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                            alert.setTitle("Failure");
//                            alert.setHeaderText(null);
//                            alert.setContentText("Error deleting"+my2.getName());
//                            
//                            
//                            alert.showAndWait();
                            
                            
                            
  //                      }
                        
                         }catch(Exception e){
                             
                             
                              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Failure");
                            alert.setHeaderText(null);
                            alert.setContentText("Error deleting "+e);
                            
                            
                            alert.showAndWait();
                                
                            }
                        });
                        
                        setGraphic(editButton);
                        setText(null);
                        
                    }
                    
                };
                  
              };
                  
                  
                          return cell2;
                      };
              
        deletecol.setCellFactory(cellFactory2);
      lecttable.setItems(list);
      

      
      
      
    }
    
    public void editColumns(){
        
          namecol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       namecol.setOnEditCommit(
       

                           (TableColumn.CellEditEvent<LecturerModel, String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setName(t.getNewValue())
       
      
                );
       
       lectidcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       lectidcol.setOnEditCommit(
       

                           (TableColumn.CellEditEvent<LecturerModel, String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLectureid(t.getNewValue())
       
      
                );
       
       
        facultycol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       facultycol.setOnEditCommit(
       

                           (TableColumn.CellEditEvent<LecturerModel, String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setFaculty(t.getNewValue())
       
      
                );
       
         departmentcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       departmentcol.setOnEditCommit(
       

                           (TableColumn.CellEditEvent<LecturerModel, String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDepartment(t.getNewValue())
       
      
                );
       
        centercol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       centercol.setOnEditCommit(
       

                           (TableColumn.CellEditEvent<LecturerModel, String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCenter(t.getNewValue())
       
      
                );
       
         buildingcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       buildingcol.setOnEditCommit(
       

                           (TableColumn.CellEditEvent<LecturerModel, String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setBuilding(t.getNewValue())
       
      
                );
       
         levelcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       levelcol.setOnEditCommit(
       

                           (TableColumn.CellEditEvent<LecturerModel, String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLevel(t.getNewValue())
       
      
                );
    }
    
    
    public void editfields(){
        
        //System.out.println(namecol.getText());
       // LecturerModel m1
        
    }
    
}
