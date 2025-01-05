package pl.put.poznan.buildinginfo.logic;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterTest {
    private final Building testBuilding = new Building("1","test");
    private final Building testBuildingEmpty = new Building("2","testEmpty");
    @BeforeEach
    void setUp(){
        for(int i = 0; i < 3; i++){
            Level level = new Level(String.valueOf(i), "Name"+i);
            for (int j = 10; j < 50; j += 10){
                OfficeRoom testRoom = new OfficeRoom(String.valueOf(j), "Name"+j, 1, 10, 20, 30, (float)j * (i+1) );
                level.addRoom(testRoom);
            }
            testBuilding.addLevel(level);
        }
    }
    @Test
    void waterSum_returnSum(){
        float sum = testBuilding.getBuildingWater();
        assertEquals(600.0, sum);
    }
    @Test
    void waterSum_returnSum2(){
        float sum = testBuildingEmpty.getBuildingWater();
        assertEquals(0.0, sum);
    }
}