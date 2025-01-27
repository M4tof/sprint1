package pl.put.poznan.buildinginfo.rest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import pl.put.poznan.buildinginfo.logic.Building;
import pl.put.poznan.buildinginfo.logic.BuildingRepository;
import pl.put.poznan.buildinginfo.logic.Level;
import pl.put.poznan.buildinginfo.logic.Room;

import java.util.Collections;

class DataControllerTest {
    @Mock
    private BuildingRepository repository;

    @InjectMocks
    private DataController dataController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initializes mocks and injects them
    }

    @Test
    void testProcessBuilding_success() {
        // Arrange
        Building building = new Building("1","Building 1");

        Level level = new Level("L1","Level 1");

        Room room = new Room("R1","Room1",50,100);

        level.addRoom(room);
        building.addLevel(level);

        // Act
        ResponseEntity<?> response = dataController.processBuilding(building);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        verify(repository, times(1)).addBuilding(building);
    }
}
