package pl.put.poznan.buildinginfo.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @Test
    void addRoom() {
        Level level = new Level("1", "First Floor");
        Room room = new Room("101", "Conference Room", 50.0f, 3);

        level.addRoom(room);

        assertNotNull(level.getRoomById("101"));
        assertEquals("Conference Room", level.getRoomById("101").getName());
    }

    @Test
    void removeRoom() {
        Level level = new Level("1", "First Floor");
        Room room = new Room("101", "Conference Room", 50.0f, 3);
        level.addRoom(room);

        level.removeRoom("101");

        assertNull(level.getRoomById("101"));
    }

    @Test
    void mixedSubtypeGet(){
        Level level = new Level("1", "First Floor");
        Room room = new Room("101", "Conference Room", 50.0f, 3);
        Garage garage = new Garage("102","Graż",100.0f,10.0f,5.0f,10);
        OfficeRoom officeRoom = new OfficeRoom("103","Biuro",10.0f,20.0f,30.0f, 40.0f,15.0f);
        Balcony balcony = new Balcony("104","Balkon",1.0f,2.0f,30.0f);
        Pool pool = new Pool("150","Basen",90.0f,80.0f,70.0f,60.0f,50.0f,40.0f);

        level.addRoom(room);
        level.addRoom(garage);
        level.addRoom(officeRoom);
        level.addRoom(balcony);
        level.addRoom(pool);

        assertEquals("Conference Room", level.getRoomById("101").getName());
        assertEquals("Graż", level.getRoomById("102").getName());
        assertEquals(100.0f, ((Garage) level.getRoomById("102")).getArea());
        assertEquals("Biuro", level.getRoomById("103").getName());
        assertEquals(30.0f, ((OfficeRoom) level.getRoomById("103")).getHeating());
        assertEquals("Balkon", level.getRoomById("104").getName());
        assertEquals(30.0f, ((Balcony) level.getRoomById("104")).getLight());
        assertEquals("Basen", level.getRoomById("150").getName());
        assertEquals(90.0f, ((Pool) level.getRoomById("150")).getArea());

        // Validate the total count of rooms added
        assertEquals(5, level.getRooms().size());
    }
}