package pl.put.poznan.buildinginfo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildinginfo.logic.Building;
import pl.put.poznan.buildinginfo.logic.BuildingRepository;
import pl.put.poznan.buildinginfo.logic.OfficeRoom;
import pl.put.poznan.buildinginfo.logic.Balcony;
import pl.put.poznan.buildinginfo.logic.Pool;
import pl.put.poznan.buildinginfo.logic.Garage;

import java.util.List;

@RestController
@RequestMapping("/Data")
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    private final BuildingRepository repository;

    public DataController(BuildingRepository repository) {
        this.repository = repository;
    }

    // localhost:8080/Data/Add-building  <- JSON
    @RequestMapping(method = RequestMethod.POST,
            value = "/Add-building",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> proccesBuilding(@RequestBody Building building) {
        logger.debug("Received building data: {}", building);

        // Check if the building object is null or missing required fields
        if (building == null) {
            logger.error("Received null building data");
            return ResponseEntity.badRequest().body("Building data is null");
        }

        if (building.getId() == null || building.getId().isEmpty()) {
            logger.error("Building ID is missing");
            return ResponseEntity.badRequest().body("Building ID is missing");
        }

        if (building.getName() == null || building.getName().isEmpty()) {
            logger.error("Building name is missing");
            return ResponseEntity.badRequest().body("Building name is missing");
        }

        if (building.getLevels() == null || building.getLevels().isEmpty()) {
            logger.error("Building levels are missing or empty");
            return ResponseEntity.badRequest().body("Building levels are missing or empty");
        }

        // Iterate through levels and rooms for any missing data
        for (var level : building.getLevels()) {
            if (level.getId() == null || level.getId().isEmpty()) {
                logger.error("Level ID is missing for level: {}", level.getName());
                return ResponseEntity.badRequest().body("Level ID is missing for level: " + level.getName());
            }

            if (level.getRooms() == null || level.getRooms().isEmpty()) {
                logger.error("Rooms are missing for level: {}", level.getName());
                return ResponseEntity.badRequest().body("Rooms are missing for level: " + level.getName());
            }

            for (var room : level.getRooms()) {
                if (room.getId() == null || room.getId().isEmpty()) {
                    logger.error("Room ID is missing for room: {}", room.getName());
                    return ResponseEntity.badRequest().body("Room ID is missing for room: " + room.getName());
                }

                if (room.getName() == null || room.getName().isEmpty()) {
                    logger.error("Room name is missing for room with ID: {}", room.getId());
                    return ResponseEntity.badRequest().body("Room name is missing for room with ID: " + room.getId());
                }

                // Optionally, check for valid area, cube, heating, and light values
                if (room.getArea() <= 0) {
                    logger.error("Invalid area for room: {}", room.getName());
                    return ResponseEntity.badRequest().body("Invalid area for room: " + room.getName());
                }
                if (room.getCube() <= 0) {
                    logger.error("Invalid cube for room: {}", room.getName());
                    return ResponseEntity.badRequest().body("Invalid cube for room: " + room.getName());
                }
                if(room instanceof OfficeRoom){
                    OfficeRoom officeRoom = (OfficeRoom) room;
                    if (officeRoom.getHeating() <= 0) {
                        logger.error("Invalid heating for room: {}", room.getName());
                        return ResponseEntity.badRequest().body("Invalid heating for room: " + room.getName());
                    }
                    if (officeRoom.getLight() <= 0) {
                        logger.error("Invalid light for room: {}", room.getName());
                        return ResponseEntity.badRequest().body("Invalid light for room: " + room.getName());
                    }
                    if(room instanceof Pool){
                        Pool pool = (Pool) room;
                        if(pool.getPoolArea() <=0){
                            logger.error("Invalid pool size for room: {}", room.getName());
                            return ResponseEntity.badRequest().body("Invalid pool size for room: " + room.getName());
                        }
                        if(pool.getPoolArea() >= pool.getArea()){
                            logger.error("Invalid pool size (larger than room itself) for room: {}", room.getName());
                            return ResponseEntity.badRequest().body("Invalid pool size (larger than room itself) for room: " + room.getName());
                        }
                    }
                }
                else if(room instanceof Balcony){
                    Balcony balcony = (Balcony) room;
                    if(balcony.getLight() <= 0){
                        logger.error("Invalid light for room: {}", room.getName());
                        return ResponseEntity.badRequest().body("Invalid light for room: " + room.getName());
                    }
                    if(room instanceof Garage){
                        Garage garage = (Garage) room;
                        if(garage.getGarageCapacity() <= 0){
                            logger.error("Invalid garage capacity for room: {}", room.getName());
                            return ResponseEntity.badRequest().body("Invalid garage capacity for room: " + room.getName());
                        }
                    }
                }
            }
        }

        logger.info("Processing building with ID: {} and Name: {}", building.getId(), building.getName());

        try {
            repository.addBuilding(building);
        } catch (Exception e) {
            logger.error("Error processing building data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing building data: " + e.getMessage());
        }

        logger.info("Building successfully added");
        return ResponseEntity.status(HttpStatus.CREATED).body(building);
    }

    // localhost:8080/Data/Get-buildings
    @RequestMapping(method = RequestMethod.GET,
            value = "/Get-buildings",
            produces = "application/json")
    public List<Building> getAllBuildings() {
        logger.info("Fetching all buildings with full details");

        return repository.getAllBuildings(); // Return the full building objects
    }
}
