/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Hp
 */
public class StudentData {
    private int id;
    private String year;
    private String pro;
    private String grp_no;
    private String grp_id;
    private String sgrp_no;
    private String sgrp_id;
    private Button edit;
    private Button delete;
    
    public StudentData(int id, String year, String pro, String grp_no, String grp_id, String sgrp_no, String sgrp_id){
            this.id = id;
            this.year = year;
            this.pro = pro;
            this.grp_no = grp_no;
            this.grp_id = grp_id;
            this.sgrp_no = sgrp_no;
            this.sgrp_id = sgrp_id;
            this.edit = new Button("Edit");
            this.delete = new Button("Delete");
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

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getGrp_no() {
        return grp_no;
    }

    public void setGrp_no(String grp_no) {
        this.grp_no = grp_no;
    }

    public String getGrp_id() {
        return grp_id;
    }

    public void setGrp_id(String grp_id) {
        this.grp_id = grp_id;
    }

    public String getSgrp_no() {
        return sgrp_no;
    }

    public void setSgrp_no(String sgrp_no) {
        this.sgrp_no = sgrp_no;
    }

    public String getSgrp_id() {
        return sgrp_id;
    }

    public void setSgrp_id(String sgrp_id) {
        this.sgrp_id = sgrp_id;
    }

    @Override
    public String toString() {
        return this.getSgrp_id(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
