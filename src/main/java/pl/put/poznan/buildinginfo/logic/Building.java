package pl.put.poznan.buildinginfo.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Building {
    private String id;
    private String name; // Optional
    private List<Level> levels;

    // Constructor
    public Building(){}
    public Building(String id, String name) {
        this.id = id;
        this.name = name;
        this.levels = new ArrayList<>();
    }

    // Methods to manage levels
    public void addLevel(Level level) {
        this.levels.add(level);
    }

    public void removeRoom(Level level) {
        this.levels.remove(level);
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

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public Level getLevelById(String Id){
        for (Level i: levels){
            if ( Objects.equals(Id,i.getId()) ){
                return i;
            }
        }
        return null;
    }

    public float getBuildingArea(){
        float sum = 0;
        for(Level level : this.getLevels()){
            sum += level.getLevelArea();
        }
        return sum;
    }

    public float getBuildingCube(){
        float sum = 0;
        for(Level level : this.getLevels()){
            sum += level.getLevelCube();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", levels=" + levels +
                '}';
    }
}
