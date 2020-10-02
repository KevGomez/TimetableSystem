/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Vidula
 */
public class DisplaySession {
    
    
   private String lecturer;
   private  String subjects;
   private String tag;
   private String students_grp;
    private int numberofstudents;
    private String duration;

    public DisplaySession(String lecturer, String subjects, String tag, String students_grp, int numberofstudents, String duration) {
        this.lecturer = lecturer;
        this.subjects = subjects;
        this.tag = tag;
        this.students_grp = students_grp;
        this.numberofstudents = numberofstudents;
        this.duration = duration;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStudents_grp() {
        return students_grp;
    }

    public void setStudents_grp(String students_grp) {
        this.students_grp = students_grp;
    }

    public int getNumberofstudents() {
        return numberofstudents;
    }

    public void setNumberofstudents(int numberofstudents) {
        this.numberofstudents = numberofstudents;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    
    
    
    


    
    
    

  
    
    
}
