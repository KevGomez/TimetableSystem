/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SubjectDB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import Model.SubjectModel;
/**
 * FXML Controller class
 *
 * @author Vidula
 */
public class SubjectController implements Initializable {

    
        @FXML
    private TextField year;
    
     
      @FXML
    private TextField subject;
      
       @FXML
    private TextField lecture;
       
        @FXML
    private TextField lab;
        
         @FXML
    private TextField semester;
         
          @FXML
    private TextField code;
          
           @FXML
    private TextField tutorial;
           
            @FXML
    private TextField evaluation;
            
            
            
            @FXML
    private TableColumn<SubjectModel, String> codecol;
             
                    @FXML
    private TableColumn<SubjectModel, String> subjectcol;
                    
                           @FXML
    private TableColumn<SubjectModel, String> yearcol;
                           
                                  @FXML
    private TableColumn<SubjectModel, String> semestercol;
                                  
                                     
                                  @FXML
    private TableColumn<SubjectModel, String> lecturecol;
                                  
                                  @FXML
    private TableColumn tutorialcol;
                                  
                                     @FXML
    private TableColumn labcol;
                                     
                                        @FXML
    private TableColumn evaluationcol;
                                        
                                        
                                        @FXML
    private TableColumn<SubjectModel, String> editcol;
                                        
                                        
                                        @FXML
    private TableColumn<SubjectModel, String> deletecol;
                                  
                                           
                                        @FXML
    private TableView subjecttable;
    @FXML
    private Button submit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
      
       getData();
       editcolumns();
       
      
       
      
    }    
    
     
       @FXML
    public void insertData(MouseEvent event)throws IOException
    {
        SubjectDB sub=new SubjectDB();
        
        int value=sub.createSubject(year.getText().toString(), semester.getText().toString(), subject.getText().toString(), code.getText().toString(), lecture.getText().toString(), tutorial.getText().toString(), lab.getText().toString(), evaluation.getText().toString());
         
         
         
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
    
    
    public void getData(){
        
        SubjectDB s1=new SubjectDB();
        ObservableList<SubjectModel> list = s1.getAllSubjectData();
          subjecttable.setEditable(true);
          
          
        codecol.setCellValueFactory(new PropertyValueFactory<>("code"));
         subjectcol.setCellValueFactory(new PropertyValueFactory<>("subject"));
          yearcol.setCellValueFactory(new PropertyValueFactory<>("year"));
           semestercol.setCellValueFactory(new PropertyValueFactory<>("semester"));
            lecturecol.setCellValueFactory(new PropertyValueFactory<>("lecture"));
             tutorialcol.setCellValueFactory(new PropertyValueFactory<>("tutorial"));
              labcol.setCellValueFactory(new PropertyValueFactory<>("lab"));
              evaluationcol.setCellValueFactory(new PropertyValueFactory<>("evaluation"));
              
             //editcol.setCellValueFactory(new PropertyValueFactory<>("button"));
             //deletecol.setCellValueFactory(new PropertyValueFactory<>("buttondelete"));
              
              
               
              Callback<TableColumn<SubjectModel,String>,TableCell<SubjectModel,String>> cellFactory=(param)->{
                  
                  
                  final TableCell<SubjectModel,String> cell=new TableCell<SubjectModel,String>(){
                  
                @Override
                public void updateItem(String item, boolean empty){
                    
                    super.updateItem(item, empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        
                        final Button editButton=new Button("edit");
                        editButton.setOnAction(event ->{
                        
                        SubjectModel my=getTableView().getItems().get(getIndex());
                        
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Success");
//        alert.setHeaderText(null);
//        alert.setContentText(my.getLecture());
//        
//        System.out.println("ID: " + my.getId());
//
//        alert.showAndWait();
                        
                      int value=  s1.UpdateSubject(my.getId(), my.getYear(), my.getSemester(), my.getSubject(), my.getCode(), my.getLecture(), my.getTutorial(), my.getLab(), my.getEvaluation());
                        
                        
                        if(value==1)
                        {
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Successfully updated");
        
       // System.out.println("ID: " + my.getId());

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
              
        editcol.setCellFactory(cellFactory);
        
        
        
        
        
             Callback<TableColumn<SubjectModel,String>,TableCell<SubjectModel,String>> cellFactory2=(param)->{
                  
                  
                  final TableCell<SubjectModel,String> cell2=new TableCell<SubjectModel,String>(){
                  
                @Override
                public void updateItem(String item, boolean empty){
                    
                    super.updateItem(item, empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        
                        final Button editButton=new Button("Delete");
                        editButton.setOnAction(event ->{
                        
                        SubjectModel my2=getTableView().getItems().get(getIndex());
                        
                              subjecttable.getItems().removeAll(subjecttable.getSelectionModel().getSelectedItem());
                        
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Successfully deleted"+my2.getLecture());
        
        
        alert.showAndWait();

//                        int value=s1.DeleteSubject(my2.getId());
//                        
//                        if(value==1)
//                        {
//                            getData();
//                            
// Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Success");
//        alert.setHeaderText(null);
//        alert.setContentText("Successfully deleted"+my2.getLecture());
//        
//        
//        alert.showAndWait();
//                        }else{
//                            
//                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Failure");
//        alert.setHeaderText(null);
//        alert.setContentText("Error deleting"+my2.getLecture());
//        
//        
//        alert.showAndWait();
//                            
//                            
//                            
//                        }
                        
                        });
                        
                        setGraphic(editButton);
                        setText(null);
                        
                    }
                    
                };
                  
              };
                  
                  
                          return cell2;
                      };
              
        deletecol.setCellFactory(cellFactory2);
        
         subjecttable.setItems(list);
    }
      
     
    public void editcolumns(){
        
        
         
        codecol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       codecol.setOnEditCommit(

       
       new EventHandler<TableColumn.CellEditEvent<SubjectModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SubjectModel, String> t) {
                    ((SubjectModel)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setCode(t.getNewValue());
                }
            });
       
       
       
       
       
         subjectcol.setCellValueFactory(new PropertyValueFactory<>("subject"));
          yearcol.setCellValueFactory(new PropertyValueFactory<>("year"));
           semestercol.setCellValueFactory(new PropertyValueFactory<>("semester"));
            lecturecol.setCellValueFactory(new PropertyValueFactory<>("lecture"));
             tutorialcol.setCellValueFactory(new PropertyValueFactory<>("tutorial"));
              labcol.setCellValueFactory(new PropertyValueFactory<>("lab"));
              evaluationcol.setCellValueFactory(new PropertyValueFactory<>("evaluation"));
              
              
              
       
          subjectcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       subjectcol.setOnEditCommit(

       
       new EventHandler<TableColumn.CellEditEvent<SubjectModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SubjectModel, String> t) {
                    ((SubjectModel)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSubject(t.getNewValue());
                }
            });
        
       
 
        
           yearcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       yearcol.setOnEditCommit(

       
       new EventHandler<TableColumn.CellEditEvent<SubjectModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SubjectModel, String> t) {
                    ((SubjectModel)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setYear(t.getNewValue());
                }
            });
       
       
           semestercol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       semestercol.setOnEditCommit(

       
       new EventHandler<TableColumn.CellEditEvent<SubjectModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SubjectModel, String> t) {
                    ((SubjectModel)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSemester(t.getNewValue());
                }
            });
        
       
           lecturecol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       lecturecol.setOnEditCommit(

       
       new EventHandler<TableColumn.CellEditEvent<SubjectModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SubjectModel, String> t) {
                    ((SubjectModel)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setLecture(t.getNewValue());
                }
            });
       
       
       
        tutorialcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       tutorialcol.setOnEditCommit(

       
       new EventHandler<TableColumn.CellEditEvent<SubjectModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SubjectModel, String> t) {
                    ((SubjectModel)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setTutorial(t.getNewValue());
                }
            });
       
       
          labcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       labcol.setOnEditCommit(

       
       new EventHandler<TableColumn.CellEditEvent<SubjectModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SubjectModel, String> t) {
                    ((SubjectModel)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setLab(t.getNewValue());
                }
            });
       
       
                 evaluationcol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        
       evaluationcol.setOnEditCommit(

       
       new EventHandler<TableColumn.CellEditEvent<SubjectModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SubjectModel, String> t) {
                    ((SubjectModel)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEvaluation(t.getNewValue());
                }
            });
  
        
    }
      
    
}
