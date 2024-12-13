package pl.put.poznan.buildinginfo.logic;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeatingTest {
    private final Building testBuilding = new Building("1","test");
    private final Building testBuildingEmpty = new Building("2","testEmpty");
    @BeforeEach
    void setUp(){
        for(int i = 0; i < 3; i++){
            Level level = new Level(String.valueOf(i), "Name"+i);
            for (int j = 10; j < 50; j += 10){
                Room testRoom = new Room(String.valueOf(j), "Name"+j, 1, (float)j / 2, (float)j * (i+1), 1 );
                level.addRoom(testRoom);
            }
            testBuilding.addLevel(level);
        }
    }
    @Test
    void heatingSum_returnSum(){
        float sum = testBuilding.getBuildingHeating();
        assertEquals(4.0, sum);
    }
    @Test
    void heatingSum_returnSum2(){
        float sum = testBuildingEmpty.getBuildingHeating();
        assertEquals(0.0, sum);
    }
}