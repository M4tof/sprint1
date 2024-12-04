package pl.put.poznan.buildinginfo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildinginfo.logic.Building;
import pl.put.poznan.buildinginfo.logic.BuildingRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Data")
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    private final BuildingRepository repository = BuildingRepository.getInstance();

//    localhost:8080/Data/Add-building  <- JSON
    @RequestMapping(method = RequestMethod.POST,
            value = "/Add-building",
            consumes = "application/json",
            produces = "application/json")
    public Building proccesBuilding(@RequestBody Building building) {
        logger.debug("Received building data: {}", building);

        logger.info("Processing building with ID: {} and Name: {}", building.getId(), building.getName());

        repository.addBuilding(building);

        return building;
    }

//    localhost:8080/Data/Get-buildings
    @RequestMapping(method = RequestMethod.GET,
            value = "/Get-buildings",
            produces = "application/json")
    public List<Building> getAllBuildings() {
        logger.info("Fetching all buildings with full details");

        return repository.getAllBuildings(); // Return the full building objects
    }


}
