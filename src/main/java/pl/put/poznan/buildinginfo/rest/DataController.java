package pl.put.poznan.buildinginfo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildinginfo.logic.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Data")
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    private final BuildingRepository repository;

    public DataController(BuildingRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> processBuilding(@RequestBody Building building) {
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
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added building");
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> addLevel(@PathVariable String id, @RequestBody Level newLevel) {
        logger.debug("Received add request for level within Building ID: {} ", id);
        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID received: ID is null or empty");
            return ResponseEntity.badRequest().body("Invalid ID: ID is null or empty");
        }
        if (newLevel == null) {
            logger.error("Level data is null");
            return ResponseEntity.badRequest().body("Level data is null");
        }
        if (newLevel.getId() == null || newLevel.getId().isEmpty()) {
            logger.error("Level ID is missing");
            return ResponseEntity.badRequest().body("Level ID is missing");
        }
        if (newLevel.getName() == null || newLevel.getName().isEmpty()) {
            logger.error("Level name is missing");
            return ResponseEntity.badRequest().body("Level name is missing");
        }

        try {
            Building building = repository.getBuildingById(id);
            if (building == null) {
                logger.warn("No building found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No building found with ID: " + id);
            }

            if (building.getLevelById(newLevel.getId()) != null) {
                logger.warn("Level with ID: {} already exists in building with ID: {}", newLevel.getId(), id);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Level with ID: " + newLevel.getId() + " already exists");
            }

            building.addLevel(newLevel);
            logger.info("Level successfully added to building with ID: {}", id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added level");
        } catch (Exception e) {
            logger.error("Error adding level to building with ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding level: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/{id}/{levelId}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> addRoom(@PathVariable String id, @PathVariable String levelId, @RequestBody Room newRoom) {
        logger.debug("Received add request for room within Level ID: {}, Building ID: {} ", levelId, id);
        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID received: ID is null or empty");
            return ResponseEntity.badRequest().body("Invalid ID: ID is null or empty");
        }
        if (levelId == null || levelId.isEmpty()) {
            logger.error("Invalid LevelID received: LevelID is null or empty");
            return ResponseEntity.badRequest().body("Invalid LevelID: LevelID is null or empty");
        }
        if (newRoom == null) {
            logger.error("Room data is null");
            return ResponseEntity.badRequest().body("Room data is null");
        }
        if (newRoom.getId() == null || newRoom.getId().isEmpty()) {
            logger.error("Room ID is missing");
            return ResponseEntity.badRequest().body("Room ID is missing");
        }
        if (newRoom.getName() == null || newRoom.getName().isEmpty()) {
            logger.error("Room name is missing");
            return ResponseEntity.badRequest().body("Room name is missing");
        }

        try {
            Building building = repository.getBuildingById(id);
            if (building == null) {
                logger.warn("No building found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No building found with ID: " + id);
            }
            Level level = building.getLevelById(levelId);
            if (level == null) {
                logger.warn("No level found with ID: {}", levelId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No level found with ID: " + levelId);
            }
            if (level.getRoomById(newRoom.getId()) != null) {
                logger.warn("Room with ID: {} already exists in level with ID: {}", newRoom.getId(), levelId);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Room with ID: " + newRoom.getId() + " already exists");
            }

            level.addRoom(newRoom);
            logger.info("Successfully added room ID {} to Level ID {} in Building ID {}", newRoom.getId(), levelId, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added room");
        } catch (Exception e) {
            logger.error("Error adding room to building with ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding level: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public List<Building> getAllBuildings() {
        logger.info("Fetching all buildings with full details");
        return repository.getAllBuildings();
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}",
            produces = "application/json")
    public Building getBuilding(@PathVariable String id){
        logger.debug("Received request for ID: {}", id);

        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID received: ID is null or empty");
            return null;
        }
        try {
            return repository.getBuildingById(id);
        }catch (Exception e) {
            logger.error("Error while processing request for building with ID: {}: {}", id, e.getMessage());
            return null;
        }


    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}",
            produces = "application/json")
    public ResponseEntity<?> removeBuilding(@PathVariable String id) {
        logger.debug("Received remove request for ID: {}", id);

        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID received: ID is null or empty");
            return ResponseEntity.badRequest().body("Invalid ID: ID is null or empty");
        }

        try {
            Building toDelete = repository.getBuildingById(id);

            if (toDelete != null) {
                logger.info("Building found for deletion: ID: {}, Name: {}", toDelete.getId(), toDelete.getName());
                repository.removeBuilding(id);
                return ResponseEntity.ok("Building scheduled for deletion: ID: " + id);
            } else {
                logger.warn("No building found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No building found with ID: " + id);
            }
        } catch (Exception e) {
            logger.error("Error while processing delete request for ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing delete request: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}/{levelId}",
            produces = "application/json")
    public ResponseEntity<?> removeLevel(@PathVariable String id, @PathVariable String levelId){
        logger.debug("Received remove request for level ID: {} within Building ID: {} ", levelId,id);

        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID received: ID is null or empty");
            return ResponseEntity.badRequest().body("Invalid ID: ID is null or empty");
        }

        if (levelId == null || levelId.isEmpty()) {
            logger.error("Invalid LevelID received: LevelID is null or empty");
            return ResponseEntity.badRequest().body("Invalid LevelID: LevelID is null or empty");
        }

        try{
            Building toDelete = repository.getBuildingById(id);

            if (toDelete != null) {
                logger.info("Building found for deletion: ID: {}, Name: {}", toDelete.getId(), toDelete.getName());
                Level levelToDelete = toDelete.getLevelById(levelId);
                if (levelToDelete != null){
                    logger.info("Level found for deletion: ID: {}", levelToDelete.getName());
                    toDelete.removeLevel(levelId);
                    return ResponseEntity.ok("Level scheduled for deletion: ID: " + levelId);
                } else {
                    logger.warn("No level found with ID: {}", levelId);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No level found with ID: " + levelId);
                }
            } else {
                logger.warn("No building found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No building found with ID: " + id);
            }

        } catch (Exception e) {
            logger.error("Error while processing delete request for ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing delete request: " + e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}/{levelId}/{roomId}",
            produces = "application/json")
    public ResponseEntity<?> removeRoom(@PathVariable String id, @PathVariable String levelId, @PathVariable String roomId){
        logger.debug("Received remove request for room ID: {} within level ID: {} within Building ID: {} ", roomId,levelId,id);

        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID received: ID is null or empty");
            return ResponseEntity.badRequest().body("Invalid ID: ID is null or empty");
        }

        if (levelId == null || levelId.isEmpty()) {
            logger.error("Invalid LevelID received: LevelID is null or empty");
            return ResponseEntity.badRequest().body("Invalid LevelID: LevelID is null or empty");
        }

        if (roomId == null || roomId.isEmpty()){
            logger.error("Invalid RoomID received: RoomID is null or empty");
            return ResponseEntity.badRequest().body("Invalid RoomID:  RoomID is null or empty");
        }

        try{
            Building toDelete = repository.getBuildingById(id);


            if (toDelete != null) {
                logger.info("Building found for deletion: ID: {}, Name: {}", toDelete.getId(), toDelete.getName());
                Level levelToDelete = toDelete.getLevelById(levelId);

                if (levelToDelete != null){
                    logger.info("Level found for deletion: ID: {}", levelToDelete.getName());
                    Room roomToDelete = levelToDelete.getRoomById(roomId);

                    if (roomToDelete != null){
                        logger.info("Room found for deletion: ID: {}", roomToDelete.getName());
                        levelToDelete.removeRoom(roomId);
                        return ResponseEntity.ok("Room scheduled for deletion: ID: " + roomId);
                    }else {
                        logger.warn("No room found with ID: {}",roomId);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found with ID: " + roomId);
                    }

                } else {
                    logger.warn("No level found with ID: {}", levelId);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No level found with ID: " + levelId);
                }
            } else {
                logger.warn("No building found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No building found with ID: " + id);
            }

        } catch (Exception e) {
            logger.error("Error while processing delete request for ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing delete request: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}/{levelId}/{roomId}",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<?> modifyOrAddRoom(@PathVariable String id, @PathVariable String levelId, @PathVariable String roomId, @RequestBody Room newRoom){
        logger.debug("Received request to add or full replace for room within Level ID: {}, Building ID: {} ", levelId, id);
        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID received: ID is null or empty");
            return ResponseEntity.badRequest().body("Invalid ID: ID is null or empty");
        }
        if (levelId == null || levelId.isEmpty()) {
            logger.error("Invalid LevelID received: LevelID is null or empty");
            return ResponseEntity.badRequest().body("Invalid LevelID: LevelID is null or empty");
        }
        if (newRoom == null) {
            logger.error("Room data is null");
            return ResponseEntity.badRequest().body("Room data is null");
        }
        if (newRoom.getId() == null || newRoom.getId().isEmpty()) {
            logger.error("Room ID is missing");
            return ResponseEntity.badRequest().body("Room ID is missing");
        }
        if (newRoom.getName() == null || newRoom.getName().isEmpty()) {
            logger.error("Room name is missing");
            return ResponseEntity.badRequest().body("Room name is missing");
        }

        try {
            Building building = repository.getBuildingById(id);
            if (building == null) {
                logger.warn("No building found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No building found with ID: " + id);
            }
            Level level = building.getLevelById(levelId);
            if (level == null) {
                logger.warn("No level found with ID: {}", levelId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No level found with ID: " + levelId);
            }

            if (level.getRoomById(roomId) != null){
                level.removeRoom(roomId);
            }

            level.addRoom(newRoom);
            logger.info("Successfully added room ID {} to Level ID {} in Building ID {}", newRoom.getId(), levelId, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added room");
        } catch (Exception e) {
            logger.error("Error adding room to building with ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding level: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PATCH,
            value = "/{id}/{levelId}/{roomId}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> updateRoom(@PathVariable String id, @PathVariable String levelId, @PathVariable String roomId, @RequestBody Map<String, Object> updates){
        logger.debug("Received request to update room properties for Room ID: {}, Level ID: {}, Building ID: {}", roomId, levelId, id);

        if (id == null || id.isEmpty()) {
            logger.error("Invalid ID received: ID is null or empty");
            return ResponseEntity.badRequest().body("Invalid ID: ID is null or empty");
        }

        if (levelId == null || levelId.isEmpty()) {
            logger.error("Invalid LevelID received: LevelID is null or empty");
            return ResponseEntity.badRequest().body("Invalid LevelID: LevelID is null or empty");
        }

        if (roomId == null || roomId.isEmpty()) {
            logger.error("Invalid RoomID received: RoomID is null or empty");
            return ResponseEntity.badRequest().body("Invalid RoomID: RoomID is null or empty");
        }

        if (updates == null || updates.isEmpty()) {
            logger.error("Update data is null or empty");
            return ResponseEntity.badRequest().body("Update data is null or empty");
        }

        try {
            Building building = repository.getBuildingById(id);
            if (building == null) {
                logger.warn("No building found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No building found with ID: " + id);
            }

            Level level = building.getLevelById(levelId);
            if (level == null) {
                logger.warn("No level found with ID: {}", levelId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No level found with ID: " + levelId);
            }

            Room room = level.getRoomById(roomId);
            if (room == null) {
                logger.warn("No room found with ID: {}", roomId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found with ID: " + roomId);
            }
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                switch (key) {
                    case "name":
                        if (value instanceof String) {
                            room.setName((String) value);
                            logger.info("Updated room name to: {}", value);
                        } else {
                            return ResponseEntity.badRequest().body("Unable to modify name");
                        }
                        break;

                    case "area":
                        if (value instanceof Number) {
                            float area = ((Number) value).floatValue();
                                room.setArea(area);
                                logger.info("Updated room area to: {}", area);
                            } else {
                            return ResponseEntity.badRequest().body("Unable to modify area");
                        }
                        break;

                    case "cube":
                        if (value instanceof Number) {
                            float cube = ((Number) value).floatValue();
                                room.setCube(cube);
                                logger.info("Updated room cube to: {}", cube);
                            } else {
                            return ResponseEntity.badRequest().body("Unable to modify 'cube'");
                        }
                        break;

                    case "light":
                        if (room instanceof OfficeRoom && value instanceof Number) {
                            float light = ((Number) value).floatValue();
                                ((OfficeRoom) room).setLight(light);
                                logger.info("Updated room light to: {}", light);
                        }
                        else if (room instanceof Balcony && value instanceof Number) {
                            float light = ((Number) value).floatValue();
                                ((Balcony) room).setLight(light);
                                logger.info("Updated balcony light to: {}", light);
                        }
                        else {
                            return ResponseEntity.badRequest().body("Unable to modify 'light'");
                        }
                        break;

                    case "heating":
                        if (room instanceof OfficeRoom && value instanceof Number) {
                            float heating = ((Number) value).floatValue();
                                ((OfficeRoom) room).setHeating(heating);
                                logger.info("Updated room heating to: {}", heating);
                            } else {
                            return ResponseEntity.badRequest().body("Unable to modify 'heating'");
                        }
                        break;

                    case "poolArea":
                        if (room instanceof Pool && value instanceof Number) {
                            float poolArea = ((Number) value).floatValue();
                                ((Pool) room).setPoolArea(poolArea);
                                logger.info("Updated pool area to: {}", poolArea);
                        } else {
                            return ResponseEntity.badRequest().body("Unable to modify 'poolArea'");
                        }
                        break;

                    case "garageCapacity":
                        if (room instanceof Garage && value instanceof Number) {
                            int capacity = ((Number) value).intValue();
                                ((Garage) room).setGarageCapacity(capacity);
                                logger.info("Updated garage capacity to: {}", capacity);
                            } else {
                            return ResponseEntity.badRequest().body("Unable to modify 'garageCapacity'");
                        }
                        break;

                    default:
                        logger.warn("Unknown property: {}", key);
                        return ResponseEntity.badRequest().body("Unknown property: " + key);
                }
            }

            logger.info("Successfully updated room properties for Room ID: {}, Level ID: {}, Building ID: {}", roomId, levelId, id);
            return ResponseEntity.ok("Successfully updated room properties");
        }catch (Exception e) {
            logger.error("Error updating room properties for Room ID: {}: {}", roomId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating room properties: " + e.getMessage());
        }
    }

}