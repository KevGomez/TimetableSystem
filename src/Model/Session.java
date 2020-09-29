package Model;

public class Session {
    private int idsessions;
    private int numberofstudents;
    private String duration;
    private String consecutive;
    private String notavailble;
    private int tag_idtag;
    private int lecturer_idemployee;
    private  int subjects_idsubjects;
    private int students_grp_idstudents_grp;
    private int room_idroom;

    public Session() {
    }

    public Session(int idsessions, int numberofstudents, String duration, String consecutive, String notavailble, int tag_idtag, int lecturer_idemployee, int subjects_idsubjects, int students_grp_idstudents_grp, int room_idroom) {
        this.idsessions = idsessions;
        this.numberofstudents = numberofstudents;
        this.duration = duration;
        this.consecutive = consecutive;
        this.notavailble = notavailble;
        this.tag_idtag = tag_idtag;
        this.lecturer_idemployee = lecturer_idemployee;
        this.subjects_idsubjects = subjects_idsubjects;
        this.students_grp_idstudents_grp = students_grp_idstudents_grp;
        this.room_idroom = room_idroom;
    }

    public int getIdsessions() {
        return idsessions;
    }

    public void setIdsessions(int idsessions) {
        this.idsessions = idsessions;
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

    public String getConsecutive() {
        return consecutive;
    }

    public void setConsecutive(String consecutive) {
        this.consecutive = consecutive;
    }

    public String getNotavailble() {
        return notavailble;
    }

    public void setNotavailble(String notavailble) {
        this.notavailble = notavailble;
    }

    public int getTag_idtag() {
        return tag_idtag;
    }

    public void setTag_idtag(int tag_idtag) {
        this.tag_idtag = tag_idtag;
    }

    public int getLecturer_idemployee() {
        return lecturer_idemployee;
    }

    public void setLecturer_idemployee(int lecturer_idemployee) {
        this.lecturer_idemployee = lecturer_idemployee;
    }

    public int getSubjects_idsubjects() {
        return subjects_idsubjects;
    }

    public void setSubjects_idsubjects(int subjects_idsubjects) {
        this.subjects_idsubjects = subjects_idsubjects;
    }

    public int getStudents_grp_idstudents_grp() {
        return students_grp_idstudents_grp;
    }

    public void setStudents_grp_idstudents_grp(int students_grp_idstudents_grp) {
        this.students_grp_idstudents_grp = students_grp_idstudents_grp;
    }

    public int getRoom_idroom() {
        return room_idroom;
    }

    public void setRoom_idroom(int room_idroom) {
        this.room_idroom = room_idroom;
    }

    @Override
    public String toString() {
        return Integer.toString(this.getIdsessions());
    }
}
