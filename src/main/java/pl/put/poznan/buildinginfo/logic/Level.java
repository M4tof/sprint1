package pl.put.poznan.buildinginfo.logic;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private String id;
    private String name; // Optional
    private List<Room> rooms;

    // Constructor
    public Level(String id, String name) {
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    // Methods to manage rooms
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void removeRoom(Room room) {
        this.rooms.remove(room);
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public float getLevelArea(){
        float sum = 0;
        for(Room room : this.getRooms()){
            sum += room.getArea();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}