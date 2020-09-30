package Model;

public class RoomHasLecture {
    private  int idemployee;
    private  String name;
    private int level;
    private int idroom;
    private String roomName;
    private String buildingname;

    public RoomHasLecture() {
    }

    public RoomHasLecture(int idemployee, String name, int level, int idroom, String roomName, String buildingname) {
        this.idemployee = idemployee;
        this.name = name;
        this.level = level;
        this.idroom = idroom;
        this.roomName = roomName;
        this.buildingname = buildingname;
    }

    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        this.idemployee = idemployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }


}
