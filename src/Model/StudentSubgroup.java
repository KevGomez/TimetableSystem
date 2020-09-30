package Model;

public class StudentSubgroup {
    private int idstudents_grp;
    private  String year_sem;
    private  String programme;
    private  int  grp_no;
    private  int sgrp_no;
    private  String  grp_id;
    private String sgrp_id;

    public StudentSubgroup() {
    }

    public StudentSubgroup(int idstudents_grp, String year_sem, String programme, int grp_no, int sgrp_no, String grp_id, String sgrp_id) {
        this.idstudents_grp = idstudents_grp;
        this.year_sem = year_sem;
        this.programme = programme;
        this.grp_no = grp_no;
        this.sgrp_no = sgrp_no;
        this.grp_id = grp_id;
        this.sgrp_id = sgrp_id;
    }

    public int getIdstudents_grp() {
        return idstudents_grp;
    }

    public void setIdstudents_grp(int idstudents_grp) {
        this.idstudents_grp = idstudents_grp;
    }

    public String getYear_sem() {
        return year_sem;
    }

    public void setYear_sem(String year_sem) {
        this.year_sem = year_sem;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public int getGrp_no() {
        return grp_no;
    }

    public void setGrp_no(int grp_no) {
        this.grp_no = grp_no;
    }

    public int getSgrp_no() {
        return sgrp_no;
    }

    public void setSgrp_no(int sgrp_no) {
        this.sgrp_no = sgrp_no;
    }

    public String getGrp_id() {
        return grp_id;
    }

    public void setGrp_id(String grp_id) {
        this.grp_id = grp_id;
    }

    public String getSgrp_id() {
        return sgrp_id;
    }

    public void setSgrp_id(String sgrp_id) {
        this.sgrp_id = sgrp_id;
    }

    @Override
    public String toString() {
        return this.getYear_sem()+this.getProgramme()+this.getGrp_id()+this.getSgrp_id();
    }
}
