/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Button;

/**
 *
 * @author Vidula
 */
public class LecturerModel {
    private int id;
 private String name;
 private String lectureid;
 private String faculty;
 private String department;
 private String center;
 private String building;
 private String level;
  private String rank;
  private Button button;
  private Button buttondelete;

    public LecturerModel(int id,String name, String lectureid, String faculty, String department, String center, String building, String level, String rank) {
        this.id=id;
        this.name = name;
        this.lectureid = lectureid;
        this.faculty = faculty;
        this.department = department;
        this.center = center;
        this.building = building;
        this.level = level;
        this.rank = rank;
       
        this.button = new Button("Edit");
        this.buttondelete = new Button("Delete");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLectureid() {
        return lectureid;
    }

    public void setLectureid(String lectureid) {
        this.lectureid = lectureid;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButtondelete() {
        return buttondelete;
    }

    public void setButtondelete(Button buttondelete) {
        this.buttondelete = buttondelete;
    }

    @Override
    public String toString() {
        return this.getName(); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
   
  
 
  

  
  
  

 
  
  
  
 
 
    
}
