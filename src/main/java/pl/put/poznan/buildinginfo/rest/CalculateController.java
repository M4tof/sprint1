package pl.put.poznan.buildinginfo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildinginfo.logic.Building;
import pl.put.poznan.buildinginfo.logic.BuildingRepository;
import pl.put.poznan.buildinginfo.logic.Level;
import pl.put.poznan.buildinginfo.logic.Room;

@RestController
@RequestMapping("/Calculate")
public class CalculateController {
    private static final Logger logger = LoggerFactory.getLogger(CalculateController.class);
    private final BuildingRepository repository;

    public CalculateController(BuildingRepository repository) {
        this.repository = repository;
    }

    // localhost:8080/Calculate/Area/B001
    @GetMapping(value = "/Area/{id}", produces = "application/json")
    public float calculateTotalArea(@PathVariable String id) {
        logger.debug("Received request to calculate total area for building with ID: {}", id);
        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        float totalArea = building.getBuildingArea();
        logger.debug("Calculated total area for building with ID: {} is {}", id, totalArea);
        return totalArea;
    }

    // localhost:8080/Calculate/Cube/B001
    @GetMapping(value = "/Cube/{id}", produces = "application/json")
    public float calculateTotalCube(@PathVariable String id) {
        logger.debug("Received request to calculate total cube for building with ID: {}", id);
        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        float totalCube = building.getBuildingCube();
        logger.debug("Calculated total cube for building with ID: {} is {}", id, totalCube);
        return totalCube;
    }

    // localhost:8080/Calculate/Area/B001/L1
    @GetMapping(value = "/Area/{id}/{levelId}", produces = "application/json")
    public float calculateTotalArea(@PathVariable String id, @PathVariable String levelId) {
        logger.debug("Received request to calculate total area for level with ID: {} within building with ID: {}", levelId, id);
        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        if (levelId == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        Level level = building.getLevelById(levelId);

        float totalArea = level.getLevelArea();
        logger.debug("Calculated total area for level with ID: {} within building with ID: {} is {}", levelId, id, totalArea);
        return totalArea;
    }

    // localhost:8080/Calculate/Cube/B001/L1
    @GetMapping(value = "/Cube/{id}/{levelId}", produces = "application/json")
    public float calculateTotalCube(@PathVariable String id, @PathVariable String levelId) {
        logger.debug("Received request to calculate total cube for level with ID: {} within building with ID: {}", levelId, id);
        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        if (levelId == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        Level level = building.getLevelById(levelId);

        float totalCube = level.getLevelCube();
        logger.debug("Calculated total cube for level with ID: {} within building with ID: {} is {}", levelId, id, totalCube);
        return totalCube;
    }

    // localhost:8080/Calculate/Area/B001/L1/R1
    @GetMapping(value = "/Area/{id}/{levelId}/{roomId}", produces = "application/json")
    public float calculateTotalArea(@PathVariable String id, @PathVariable String levelId, @PathVariable String roomId) {
        logger.debug("Received request to calculate total area for room with ID: {} within level with ID: {} within building with ID: {}", roomId, levelId, id);

        if (id == null) {
            logger.error("Invalid building id");
            throw new IllegalArgumentException("Invalid building id");
        }
        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        if (levelId == null) {
            logger.error("Invalid level id");
            throw new IllegalArgumentException("Invalid level id");
        }

        Level level = building.getLevelById(levelId);

        if (level == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        if (roomId == null) {
            logger.error("Invalid room id");
            throw new IllegalArgumentException("Invalid room id");
        }

        Room room = level.getRoomById(roomId);

        if(room == null){
            logger.error("Room not found with ID: {} within level with ID: {} within building with ID: {}", roomId, levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        float totalArea = room.getArea();
        logger.debug("Calculated total area for room with ID: {} within level with ID: {} within building with ID: {} is {}", roomId, levelId, id, totalArea);
        return totalArea;
    }

    // localhost:8080/Calculate/Cube/B001/L1/R1
    @GetMapping(value = "/Cube/{id}/{levelId}/{roomId}", produces = "application/json")
    public float calculateTotalCube(@PathVariable String id, @PathVariable String levelId, @PathVariable String roomId) {
        logger.debug("Received request to calculate total cube for room with ID: {} within level with ID: {} within building with ID: {}", roomId, levelId, id);

        if (id == null) {
            logger.error("Invalid building id");
            throw new IllegalArgumentException("Invalid building id");
        }
        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        if (levelId == null) {
            logger.error("Invalid level id");
            throw new IllegalArgumentException("Invalid level id");
        }

        Level level = building.getLevelById(levelId);

        if (level == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        if (roomId == null) {
            logger.error("Invalid room id");
            throw new IllegalArgumentException("Invalid room id");
        }

        Room room = level.getRoomById(roomId);

        if(room == null){
            logger.error("Room not found with ID: {} within level with ID: {} within building with ID: {}", roomId, levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        float totalCube = room.getCube();
        logger.debug("Calculated total cube for room with ID: {} within level with ID: {} within building with ID: {} is {}", roomId, levelId, id, totalCube);
        return totalCube;
    }
}
