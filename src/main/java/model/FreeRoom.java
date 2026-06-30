package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0D, enumeration);
    }

    @Override
    public String toString() {
        return "Room: " + this.getRoomNumber() + " - " + this.getRoomType().message + " - " + "Price: Free";
    }
}
