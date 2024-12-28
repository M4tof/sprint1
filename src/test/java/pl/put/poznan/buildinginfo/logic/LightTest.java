package pl.put.poznan.buildinginfo.logic;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightTest {
    private final Building testBuilding = new Building("1","test");
    private final Building testBuildingEmpty = new Building("2","testEmpty");
    @BeforeEach
    void setUp(){
        for(int i = 0; i < 3; i++){
            Level level = new Level(String.valueOf(i), "Name"+i);
            for (int j = 10; j < 50; j += 10){
                OfficeRoom testRoom = new OfficeRoom(String.valueOf(j), "Name"+j, (float)j / 2, 1, 1, (float)j * (i+1) );
                level.addRoom(testRoom);
            }
            testBuilding.addLevel(level);
        }
    }
    @Test
    void lightSum_returnSum(){
        float sum = testBuilding.getBuildingLight();
        assertEquals(4.0, sum);
    }
    @Test
    void lightSum_returnSum2(){
        float sum = testBuildingEmpty.getBuildingLight();
        assertEquals(0.0, sum);
    }
}