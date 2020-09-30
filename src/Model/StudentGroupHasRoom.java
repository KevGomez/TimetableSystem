package Model;

public class StudentGroupHasRoom {
    private int idstudents_grp;
    private  String programme;
    private  String year_sem;
    private String grp_id;
    private String sgrp_id;
    private int idroom;
    private String roomName;
    private String buildingsname;

    public StudentGroupHasRoom() {
    }

    public StudentGroupHasRoom(int idstudents_grp, String programme, String year_sem, String grp_id, String sgrp_id, int idroom, String roomName, String buildingsname) {
        this.idstudents_grp = idstudents_grp;
        this.programme = programme;
        this.year_sem = year_sem;
        this.grp_id = grp_id;
        this.sgrp_id = sgrp_id;
        this.idroom = idroom;
        this.roomName = roomName;
        this.buildingsname = buildingsname;
    }

    public int getIdstudents_grp() {
        return idstudents_grp;
    }

    public void setIdstudents_grp(int idstudents_grp) {
        this.idstudents_grp = idstudents_grp;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
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

    public int getIdroom() {
        return idroom;
    }

    public void setIdroom(int idroom) {
        this.idroom = idroom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBuildingsname() {
        return buildingsname;
    }

    public void setBuildingsname(String buildingsname) {
        this.buildingsname = buildingsname;
    }

    public String getYear_sem() {
        return year_sem;
    }

    public void setYear_sem(String year_sem) {
        this.year_sem = year_sem;
    }
}
