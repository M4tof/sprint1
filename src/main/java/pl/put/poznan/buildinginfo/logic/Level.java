package pl.put.poznan.buildinginfo.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa reprezentująca piętro w budynku.
 * <p>
 * Celem klasy {@code Level} jest zarządzanie pomieszczeniami znajdującymi się na jednym piętrze budynku.
 * Piętro stanowi część większej struktury, czyli całego budynku, a klasa ta umożliwia przechowywanie i manipulowanie
 * danymi o pomieszczeniach w obrębie tego piętra.
 * </p>
 * <p>
 * Klasa oferuje metody umożliwiające obliczenie istotnych informacji o pomieszczeniach, takich jak powierzchnia,
 * kubatura, zapotrzebowanie grzewcze czy średnie oświetlenie dla całego piętra. Dzięki tym metodom, możliwe jest
 * uzyskanie przeglądu przestrzeni piętra oraz obliczenie parametrów energetycznych.
 * </p>
 * <p>
 * Klasa zapewnia dostęp do tych danych poprzez odpowiednie metody dostępu (gettery/settery) oraz umożliwia ich modyfikację
 * w razie potrzeby. Dodatkowo, zapewnia operacje agregujące, takie jak dodawanie, usuwanie i wyszukiwanie pomieszczeń.
 * </p>
 */
public class Level {
    private String id; // Unikalny identyfikator piętra
    private String name; // Optional nazwa piętra
    private List<Room> rooms; // Lista pomieszczeń na piętrze

    // Constructor
    /**
     * Konstruktor domyślny bez parametrów.
     */
    public Level(){}

    /**
     * Tworzy piętro o podanym identyfikatorze oraz nazwie.
     *
     * @param id unikalny identifikator piętra
     * @param name opcjonalna nazwa piętra
     */
    public Level(String id, String name) {
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    // Methods to manage rooms
    /**
     * Dodaje pomieszczenie do piętra poprzez dodanie go do listy pomieszczeń piętra.
     *
     * @param room pomieszczenie do dodania
     */
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    /**
     * Usuwa pomieszczenie z piętra poprzez usunięcie go z listy pomieszczeń piętra.
     *
     * @param room pomieszczenie do usunięcia
     */
    public void removeRoom(Room room) {
        this.rooms.remove(room);
    }

    // Getters and setters
    /**
     * Zwraca identyfikator piętra.
     *
     * @return identyfikator piętra
     */
    public String getId() {
        return id;
    }

    /**
     * Ustawia identyfikator piętra.
     *
     * @param id identyfikator piętra do ustawienia
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę piętra.
     *
     * @return nazwa piętra
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nazwę piętra.
     *
     * @param name nazwa piętra
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca pomieszczenia na piętrze.
     *
     * @return pomieszczenia na piętrze
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Zmienia pomieszczenia na piętrze na podanae.
     *
     * @param rooms pomieszczenia do ustawienia
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Wyszukuje pomieszczenia o szukanym identyfikatorze.
     *
     * @param Id identyfikator pomieszczenia do wyszukania
     *
     * @return pomieszczenie o szukanym identyfikatorze lub null jeśli nie istnieje
     */
    public Room getRoomById(String Id){
        for (Room i: rooms){
            if ( Objects.equals(Id,i.getId()) ){
                return i;
            }
        }
        return null;
    }

    /**
     * Zwraca powierzchnię piętra.
     *
     * @return powierzchnia piętra
     */
    public float getLevelArea(){
        float sum = 0;
        for(Room room : this.getRooms()){
            sum += room.getArea();
        }
        return sum;
    }

    /**
     * Zwraca kubaturę pomieszczenia.
     *
     * @return objętość pomieszczenia
     */
    public float getLevelCube(){
        float sum = 0;
        for(Room room : this.getRooms()){
            sum += room.getCube();
        }
        return sum;
    }

    /**
     * Oblicza średnii poziom oświetlenia na piętrze.
     *
     * @return średnie oświetlenie na piętrze lub 0 jeśli piętro jest puste
     */
    public float getLevelLight(){
        float lightsum = 0, areasum = 0;
        for(Room room : this.getRooms()){
            if(room instanceof OfficeRoom) {
                areasum += room.getArea();
                OfficeRoom officeRoom = (OfficeRoom) room; // Rzutowanie
                lightsum += officeRoom.getLight();
            }
            else if(room instanceof Balcony) {
                areasum += room.getArea();
                Balcony balcony = (Balcony) room; // Rzutowanie
                lightsum += balcony.getLight();
            }
        }
        if (areasum > 0) {
            return lightsum / areasum;
        }
        return 0;
    }

    /**
     * Zwraca średnie zapotrzebowanie grzewcze.
     *
     * @return średnie zapotrzebowanie grzewcze
     */
    public float getLevelHeating(){
        float heatingsum = 0, cubesum = 0;
        for(Room room : this.getRooms()){
            if(room instanceof OfficeRoom){
                cubesum += room.getCube();
                OfficeRoom officeRoom = (OfficeRoom) room;
                heatingsum += officeRoom.getHeating();
            }
        }
        if (cubesum > 0) {
            return heatingsum / cubesum;
        }
        return 0;
    }

    /**
     * Zwraca łączne zużycie wody.
     *
     * @return łączne zużycie wody
     */
    public float getLevelWater(){
        float sum = 0;
        for(Room room : this.getRooms()){
            if(room instanceof OfficeRoom){
                OfficeRoom officeRoom = (OfficeRoom) room;
                sum += officeRoom.getWater();
            }
        }
        return sum;
    }

//    New
    public void removeRoom(String id){
        Room roomToRemove = getRoomById(id);
        if (roomToRemove != null){
            rooms.remove(roomToRemove);
        } else{
            throw new IllegalArgumentException("No room found with ID " + id);
        }
    }

    /**
     * Zwraca tekstową reprezentację piętra.
     *
     * @return tekstowa reprezentacja piętra
     */
    @Override
    public String toString() {
        return "Level{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
