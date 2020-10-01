package timetablesystem.GenerateTimeTables;

public class SubjectsV2 {
    String sid,temID;
    int year, semester,lhours,thours,labhours,ehours;

    public SubjectsV2(String sid, String temID, int year, int semester, int lhours, int thours, int labhours, int ehours) {
        this.sid = sid;
        this.temID=temID;
        this.year = year;
        this.semester = semester;
        this.lhours = lhours;
        this.thours = thours;
        this.labhours = labhours;
        this.ehours = ehours;
    }

    public String getTemID() {
        return temID;
    }

    public String getSid() {
        return sid;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public int getLhours() {
        return lhours;
    }

    public int getThours() {
        return thours;
    }

    public int getLabhours() {
        return labhours;
    }

    public int getEhours() {
        return ehours;
    }
}
