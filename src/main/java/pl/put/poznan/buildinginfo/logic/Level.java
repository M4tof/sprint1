package pl.put.poznan.buildinginfo.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level {
    private String id;
    private String name; // Optional
    private List<Room> rooms;

    // Constructor
    public Level(){}
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

    public Room getRoomById(String Id){
        for (Room i: rooms){
            if ( Objects.equals(Id,i.getId()) ){
                return i;
            }
        }
        return null;
    }

    public float getLevelArea(){
        float sum = 0;
        for(Room room : this.getRooms()){
            sum += room.getArea();
        }
        return sum;
    }

    public float getLevelCube(){
        float sum = 0;
        for(Room room : this.getRooms()){
            sum += room.getCube();
        }
        return sum;
    }

    public float getLevelLight(){
        float lightsum = 0, areasum = 0;
        for(Room room : this.getRooms()){
            lightsum += room.getLight();
            areasum += room.getArea();
        }
        if (areasum > 0) {
            return lightsum / areasum;
        }
        return 0;
    }

    public float getLevelHeating(){
        float heatingsum = 0, cubesum = 0;
        for(Room room : this.getRooms()){
            heatingsum += room.getHeating();
            cubesum += room.getCube();
        }
        if (cubesum > 0) {
            return heatingsum / cubesum;
        }
        return 0;
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