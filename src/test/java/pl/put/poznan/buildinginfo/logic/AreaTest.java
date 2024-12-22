package pl.put.poznan.buildinginfo.logic;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaTest {
    private final Building testBuilding = new Building("1","test");
    private final Building testBuildingEmpty = new Building("2","testEmpty");
    @BeforeEach
    void setUp(){
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
    void areaSum_returnSum(){
        float sum = testBuilding.getBuildingArea();
        assertEquals(300.0, sum);
    }
    @Test
    void areaSum_returnSum2(){
        float sum = testBuildingEmpty.getBuildingArea();
        assertEquals(0.0, sum);
    }
}