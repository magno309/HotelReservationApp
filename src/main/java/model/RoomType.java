package model;

public enum RoomType {
    SINGLE("Single bed"),
    DOUBLE("Double bed");

    public final String message;

    RoomType(String message){
        this.message = message;
    }
}
