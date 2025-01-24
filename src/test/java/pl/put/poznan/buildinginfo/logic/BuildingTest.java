package pl.put.poznan.buildinginfo.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    private final Building testBuilding = new Building("1","test");
    private final Building testBuildingEmpty = new Building("2","testEmpty");

    @BeforeEach
    void setUp() {
        for(int i = 0; i <3; i++){
            Level level = new Level(String.valueOf(i), "Name"+i);
            for (int j = 10; j < 50; j += 10){
                Room testRoom = new Room(String.valueOf(j), "Name"+j, (float)j, 1);
                level.addRoom(testRoom);
            }
            testBuilding.addLevel(level);
        }
    }

    @Test
    void getLevelById() {
        Level foundLevel = testBuilding.getLevelById("1");
        assertNotNull(foundLevel);
        assertEquals("Name1", foundLevel.getName());

        Level notFoundLevel = testBuilding.getLevelById("99");
        assertNull(notFoundLevel);
    }

    @Test
    void addLevel() {
        Level testLevel = new Level("10","PoziomTestowy");
        testBuilding.addLevel(testLevel);
        assertEquals(testLevel, testBuilding.getLevelById("10"));
    }

    @Test
    void removeLevel() {
        testBuilding.removeLevel("1");
        assertNull(testBuilding.getLevelById("1"));
    }
}