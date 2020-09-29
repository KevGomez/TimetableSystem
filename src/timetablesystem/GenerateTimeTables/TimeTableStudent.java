package timetablesystem.GenerateTimeTables;

public class TimeTableStudent {

    String group;
    int capacity;

    public TimeTableStudent(String group, int capacity) {

        this.group = group;
        this.capacity = capacity;
    }

    public String getGroup() {
        return group;
    }

    public int getCapacity() {
        return capacity;
    }
}
