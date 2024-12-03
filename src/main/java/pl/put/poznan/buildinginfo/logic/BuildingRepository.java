package pl.put.poznan.buildinginfo.logic;

import java.util.ArrayList;
import java.util.List;

public class BuildingRepository {
    private static BuildingRepository instance;
    private final List<Building> buildings;

    private BuildingRepository() {
        this.buildings = new ArrayList<>();
    }

    public static synchronized BuildingRepository getInstance() {
        if (instance == null) {
            instance = new BuildingRepository();
        }
        return instance;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public List<Building> getAllBuildings() {
        return new ArrayList<>(buildings); // Return a copy to avoid direct manipulation
    }
}
