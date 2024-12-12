package pl.put.poznan.buildinginfo.logic;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
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
        // Check if a building with the same ID already exists
        Building existingBuilding = getBuildingById(building.getId());
        if (existingBuilding != null) {
            throw new IllegalArgumentException("A building with ID " + building.getId() + " already exists.");
        }

        // Check for duplicate level IDs within the building
        for (var level : building.getLevels()) {
            for (var otherLevel : building.getLevels()) {
                if (!level.equals(otherLevel) && Objects.equals(level.getId(), otherLevel.getId())) {
                    throw new IllegalArgumentException("Duplicate level ID " + level.getId() + " found.");
                }
            }

            // Check for duplicate room IDs within the level
            List<String> roomIds = new ArrayList<>();
            for (var room : level.getRooms()) {
                if (roomIds.contains(room.getId())) {
                    throw new IllegalArgumentException("Duplicate room ID " + room.getId() + " found in level " + level.getId());
                }
                roomIds.add(room.getId());
            }
        }

        // If no duplicates are found, add the building to the repository
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
