package timetablesystem.DateTimeManagement;

public class TimeSlots {
    private String time;
    private int id;

    public TimeSlots(String time, int id) {
        this.time = time;
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
}
