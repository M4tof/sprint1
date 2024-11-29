package pl.put.poznan.buildinginfo.logic;

public class Room {
    private String id;
    private String name; // Optional
    private float area;
    private float cube;
    private float heating;
    private float light;

    // Constructor
    public Room(String id, String name, float area, float cube, float heating, float light) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
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

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getCube() {
        return cube;
    }

    public void setCube(float cube) {
        this.cube = cube;
    }

    public float getHeating() {
        return heating;
    }

    public void setHeating(float heating) {
        this.heating = heating;
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", cube=" + cube +
                ", heating=" + heating +
                ", light=" + light +
                '}';
    }
}