package pl.put.poznan.buildinginfo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildinginfo.logic.*;

import java.util.List;

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

        if (id == null) {
            logger.error("Invalid building id");
            throw new IllegalArgumentException("Invalid building id");
        }

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

        if (id == null) {
            logger.error("Invalid building id");
            throw new IllegalArgumentException("Invalid building id");
        }

        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        float totalCube = building.getBuildingCube();
        logger.debug("Calculated total cube for building with ID: {} is {}", id, totalCube);
        return totalCube;
    }

    // localhost:8080/Calculate/Heating/B001
    @GetMapping(value = "/Heating/{id}", produces = "application/json")
    public float calculateTotalHeating(@PathVariable String id) {
        logger.debug("Received request to calculate total heating for building with ID: {}", id);

        if (id == null) {
            logger.error("Invalid building id");
            throw new IllegalArgumentException("Invalid building id");
        }

        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        float totalHeating = building.getBuildingHeating();
        logger.debug("Calculated total heating for building with ID: {} is {}", id, totalHeating);
        return totalHeating;
    }

    // localhost:8080/Calculate/Light/B001
    @GetMapping(value = "/Light/{id}", produces = "application/json")
    public float calculateTotalLight(@PathVariable String id) {
        logger.debug("Received request to calculate total light for building with ID: {}", id);

        if (id == null) {
            logger.error("Invalid building id");
            throw new IllegalArgumentException("Invalid building id");
        }

        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        float totalLight = building.getBuildingLight();
        logger.debug("Calculated total light for building with ID: {} is {}", id, totalLight);
        return totalLight;
    }

    // localhost:8080/Calculate/Water/B001
    @GetMapping(value = "/Water/{id}", produces = "application/json")
    public float calculateTotalWater(@PathVariable String id) {
        logger.debug("Received request to calculate total water usage for building with ID: {}", id);

        if (id == null) {
            logger.error("Invalid building id");
            throw new IllegalArgumentException("Invalid building id");
        }

        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        float totalWater = building.getBuildingWater();
        logger.debug("Calculated total water usage for building with ID: {} is {}", id, totalWater);
        return totalWater;
    }

    // localhost:8080/Calculate/Area/B001/L1
    @GetMapping(value = "/Area/{id}/{levelId}", produces = "application/json")
    public float calculateTotalArea(@PathVariable String id, @PathVariable String levelId) {
        logger.debug("Received request to calculate total area for level with ID: {} within building with ID: {}", levelId, id);

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
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        Level level = building.getLevelById(levelId);

        if (level == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        float totalArea = level.getLevelArea();
        logger.debug("Calculated total area for level with ID: {} within building with ID: {} is {}", levelId, id, totalArea);
        return totalArea;
    }

    // localhost:8080/Calculate/OverHeating/B001
    @GetMapping(value = "/OverHeating/{id}", produces = "application/json")
    List<String> calculateOverHeating(@PathVariable String id) {
        logger.debug("Received request to find rooms over the heating limit for building with ID: {}", id);

        if (id == null) {
            logger.error("Invalid building id");
            throw new IllegalArgumentException("Invalid building id");
        }

        Building building = repository.getBuildingById(id);

        if (building == null) {
            logger.error("Building not found with ID: {}", id);
            throw new IllegalArgumentException("Building not found");
        }

        List<String> OverHeating = building.getOverLimitHeating();
        logger.debug("Found rooms over the heating limit for building with ID: {}:  {}", id, OverHeating);
        return OverHeating;
    }

    // localhost:8080/Calculate/Cube/B001/L1
    @GetMapping(value = "/Cube/{id}/{levelId}", produces = "application/json")
    public float calculateTotalCube(@PathVariable String id, @PathVariable String levelId) {
        logger.debug("Received request to calculate total cube for level with ID: {} within building with ID: {}", levelId, id);

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
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        Level level = building.getLevelById(levelId);

        if (level == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        float totalCube = level.getLevelCube();
        logger.debug("Calculated total cube for level with ID: {} within building with ID: {} is {}", levelId, id, totalCube);
        return totalCube;
    }

    // localhost:8080/Calculate/Heating/B001/L1
    @GetMapping(value = "/Heating/{id}/{levelId}", produces = "application/json")
    public float calculateTotalHeating(@PathVariable String id, @PathVariable String levelId) {
        logger.debug("Received request to calculate total heating for level with ID: {} within building with ID: {}", levelId, id);

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
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        Level level = building.getLevelById(levelId);

        if (level == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        float totalHeating = level.getLevelHeating();
        logger.debug("Calculated total heating for level with ID: {} within building with ID: {} is {}", levelId, id, totalHeating);
        return totalHeating;
    }

    // localhost:8080/Calculate/Light/B001/L1
    @GetMapping(value = "/Light/{id}/{levelId}", produces = "application/json")
    public float calculateTotalLight(@PathVariable String id, @PathVariable String levelId) {
        logger.debug("Received request to calculate total light for level with ID: {} within building with ID: {}", levelId, id);

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
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        Level level = building.getLevelById(levelId);

        if (level == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        float totalLight = level.getLevelLight();
        logger.debug("Calculated total light for level with ID: {} within building with ID: {} is {}", levelId, id, totalLight);
        return totalLight;
    }

    // localhost:8080/Calculate/Water/B001/L1
    @GetMapping(value = "/Water/{id}/{levelId}", produces = "application/json")
    public float calculateTotalWater(@PathVariable String id, @PathVariable String levelId) {
        logger.debug("Received request to calculate total water usage for level with ID: {} within building with ID: {}", levelId, id);

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
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        Level level = building.getLevelById(levelId);

        if (level == null) {
            logger.error("Level not found with ID: {} within building with ID: {}", levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        float totalWater = level.getLevelWater();
        logger.debug("Calculated total water usage for level with ID: {} within building with ID: {} is {}", levelId, id, totalWater);
        return totalWater;
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

    // localhost:8080/Calculate/Heating/B001/L1/R1
    @GetMapping(value = "/Heating/{id}/{levelId}/{roomId}", produces = "application/json")
    public float calculateTotalHeating(@PathVariable String id, @PathVariable String levelId, @PathVariable String roomId) {
        logger.debug("Received request to calculate total heating for room with ID: {} within level with ID: {} within building with ID: {}", roomId, levelId, id);

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

        if(!(room instanceof OfficeRoom)){
            logger.error("Specified room does not have heating parameter, choose a room of type with heating");
            throw new IllegalArgumentException("Invalid room type");
        }

        OfficeRoom officeRoom = (OfficeRoom) room;
        float totalHeating = officeRoom.getHeating();

        logger.debug("Calculated total heating for room with ID: {} within level with ID: {} within building with ID: {} is {}", roomId, levelId, id, totalHeating);
        return totalHeating;
    }

    // localhost:8080/Calculate/Light/B001/L1/R1
    @GetMapping(value = "/Light/{id}/{levelId}/{roomId}", produces = "application/json")
    public float calculateTotalLight(@PathVariable String id, @PathVariable String levelId, @PathVariable String roomId) {
        logger.debug("Received request to calculate total light for room with ID: {} within level with ID: {} within building with ID: {}", roomId, levelId, id);

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

        if (room == null) {
            logger.error("Room not found with ID: {} within level with ID: {} within building with ID: {}", roomId, levelId, id);
            throw new IllegalArgumentException("Level not found");
        }

        if (!(room instanceof OfficeRoom) && !(room instanceof Balcony)) {
            logger.error("Specified room does not have light parameter, choose a room of type with light");
            throw new IllegalArgumentException("Invalid room type");
        }
        if(room instanceof OfficeRoom){
            OfficeRoom officeRoom = (OfficeRoom) room;
            float totalLight = officeRoom.getLight();
            logger.debug("Calculated total light for room with ID: {} within level with ID: {} within building with ID: {} is {}", roomId, levelId, id, totalLight);
            return totalLight;
        }
        else {
            Balcony balcony = (Balcony) room;
            float totalLight = balcony.getLight();
            logger.debug("Calculated total light for room with ID: {} within level with ID: {} within building with ID: {} is {}", roomId, levelId, id, totalLight);
            return totalLight;
        }
    }

    // localhost:8080/Calculate/Water/B001/L1/R1
    @GetMapping(value = "/Water/{id}/{levelId}/{roomId}", produces = "application/json")
    public float calculateTotalWater(@PathVariable String id, @PathVariable String levelId, @PathVariable String roomId) {
        logger.debug("Received request to calculate total water usage for room with ID: {} within level with ID: {} within building with ID: {}", roomId, levelId, id);

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

        if(!(room instanceof OfficeRoom)){
            logger.error("Specified room does not have water parameter, choose a room of type with water");
            throw new IllegalArgumentException("Invalid room type");
        }

        OfficeRoom officeRoom = (OfficeRoom) room;
        float totalWater = officeRoom.getWater();

        logger.debug("Calculated total water usage for room with ID: {} within level with ID: {} within building with ID: {} is {}", roomId, levelId, id, totalWater);
        return totalWater;
    }
}
