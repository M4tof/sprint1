package pl.put.poznan.buildinginfo.logic;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private String id;
    private String name; // Optional
    private List<Level> levels;

    // Constructor
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

    @Override
    public String toString() {
        return "Building{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", levels=" + levels +
                '}';
    }
}
