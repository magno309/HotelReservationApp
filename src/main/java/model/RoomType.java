package model;

public enum RoomType {
    SINGLE("SINGLE", "Single bed"),
    DOUBLE("DOUBLE", "Double bed");

    public final String value;
    public final String message;

    RoomType(String value, String message){
        this.value = value;
        this.message = message;
    }
}
