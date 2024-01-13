
package project;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String roomName;
    private int roomId;
    private float roomPrice;
    private boolean isAvailable; 
    public static List<Room> rooms = new ArrayList<>();

    public Room(String roomName, int roomId, float roomPrice, boolean isAvailable) {
        this.roomName = roomName;
        this.roomId = roomId;
        this.roomPrice = roomPrice;
        this.isAvailable = isAvailable;
        rooms.add(this);
    }

    public static Room returnRoomById(int id) {
        for (Room room : rooms) {
            if (room.getRoomId() == id) {
                return room;
            }
        }
        return null; 
    }

    public float getPricePerHour() {
        return this.roomPrice;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomId() {
        return roomId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
