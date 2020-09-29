package Model;

public class TagHasRooms {
   public int room_idroom;
    public  int tag_idtag;
    public  String roomName;
    public String tag;

   public TagHasRooms(){

   }

    public TagHasRooms(int room_idroom, int tag_idtag, String roomName, String tag) {

        this.room_idroom = room_idroom;
        this.tag_idtag = tag_idtag;
        this.roomName = roomName;
        this.tag = tag;
    }

    public int getRoom_idroom() {
        return room_idroom;
    }

    public void setRoom_idroom(int room_idroom) {
        this.room_idroom = room_idroom;
    }

    public int getTag_idtag() {
        return tag_idtag;
    }

    public void setTag_idtag(int tag_idtag) {
        this.tag_idtag = tag_idtag;
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




}
