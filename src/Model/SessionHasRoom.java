package Model;

public class SessionHasRoom {

    private int idsessions;
    private int idroom;
    private String semester ;
    private String roomName;
    private String tag;
    private String subjects;
    private String year;

    public SessionHasRoom() {
    }

    public SessionHasRoom(int idsessions, int idroom, String semester, String roomName, String tag, String subjects, String year) {
        this.idsessions = idsessions;
        this.idroom = idroom;
        this.semester = semester;
        this.roomName = roomName;
        this.tag = tag;
        this.subjects = subjects;
        this.year = year;
    }


    public int getIdsessions() {
        return idsessions;
    }

    public void setIdsessions(int idsessions) {
        this.idsessions = idsessions;
    }

    public int getIdroom() {
        return idroom;
    }

    public void setIdroom(int idroom) {
        this.idroom = idroom;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return this.getIdsessions()+": Y"+this.getYear()+":S"+this.getSemester()+" "+this.getSubjects()+":"+this.getTag();
    }
}
