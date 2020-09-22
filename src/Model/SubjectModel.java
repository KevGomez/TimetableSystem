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
public class SubjectModel {
    
    
      private int id;
     private String year;
 private String subject;
 private String lecture;
 private String lab;
 private String semester;
 private String code;
 private String tutorial;
  private String evaluation;
  private Button button;
  private Button buttondelete;
  

    public SubjectModel(int id, String year, String subject, String lecture, String lab, String semester, String code, String tutorial, String evaluation) {
        this.id = id;
        this.year = year;
        this.subject = subject;
        this.lecture = lecture;
        this.lab = lab;
        this.semester = semester;
        this.code = code;
        this.tutorial = tutorial;
        this.evaluation = evaluation;
        this.button = button;
        this.buttondelete = buttondelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
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

    
}
