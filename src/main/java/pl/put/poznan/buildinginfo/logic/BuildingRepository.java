package pl.put.poznan.buildinginfo.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Building getBuildingById(String Id){
        for (Building i: buildings){
            if ( Objects.equals(Id,i.getId()) ){
                return i;
            }
        }
        return null;
    }

}
