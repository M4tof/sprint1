package pl.put.poznan.buildinginfo.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingRepositoryTest {

    private static BuildingRepository repo;
    private static Building office;

    @BeforeAll
    static void setUp() {
        repo = BuildingRepository.getInstance();

        // Clear any previous buildings in the repository (if singleton persists across tests)
        repo.getAllBuildings().clear();

        office = new Building("B001", "Office Building");
        Level level1 = new Level("L001", "Ground Floor");
        Level level2 = new Level("L002", "First Floor");

        office.addLevel(level1);
        office.addLevel(level2);

        repo.addBuilding(office);
    }

    @Test
    void getBuildingById_existingId_returnsCorrectBuilding() {
        Building retrievedBuilding = repo.getBuildingById("B001");

        assertNotNull(retrievedBuilding, "The building should not be null.");
        assertEquals("B001", retrievedBuilding.getId(), "The building ID should match.");
        assertEquals("Office Building", retrievedBuilding.getName(), "The building name should match.");
        assertEquals(2, retrievedBuilding.getLevels().size(), "The building should have 2 levels.");
    }

    @Test
    void getBuildingById_nonExistingId_returnsNull() {
        Building retrievedBuilding = repo.getBuildingById("B999");

        assertNull(retrievedBuilding, "The building should be null for a non-existing ID.");
    }
}