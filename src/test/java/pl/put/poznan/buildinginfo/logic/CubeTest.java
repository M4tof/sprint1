package pl.put.poznan.buildinginfo.logic;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest {
    private final Building testBuilding = new Building("1","test");
    private final Building testBuildingEmpty = new Building("2","testEmpty");
    @BeforeEach
    void setUp(){
        for(int i = 0; i < 4; i++){
            Level level = new Level(String.valueOf(i), "Name"+i);
            for (int j = 10; j < 50; j += 10){
                Room testRoom = new Room(String.valueOf(j), "Name"+j, 1, (float)j, 1,1 );
                level.addRoom(testRoom);
            }
            testBuilding.addLevel(level);
        }
    }
    @Test
    void cubeSum_returnSum(){
        float sum = testBuilding.getBuildingCube();
        assertEquals(400.0, sum);
    }
    @Test
    void cubeSum_returnSum2(){
        float sum = testBuildingEmpty.getBuildingCube();
        assertEquals(0.0, sum);
    }
}