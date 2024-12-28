package pl.put.poznan.buildinginfo.logic;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Klasa reprezentująca pomieszczenie w budynku.
 * <p>
 * Celem klasy {@code Room} jest przechowywanie oraz umożliwienie manimulacji
 * podstawowymi danymi pomieszczenia - najmniejszej części każdego budynku.
 * Pomieszczenia te zostaną następnie zostaną zagregowane w większe struktury -
 * piętra i budynki.
 * </p>
 * Do danych każdego pomieszczenie zaliczają się jego identyfikator, nazwa, powierzchnia, kubatura, ogrzewanie oraz oświetlenie.
 * <p>
 * Metody zawarte w klasie umożliwiają zewnętrzny dostęp do tych prywatnych atrybutów oraz w miarę potrzeb do ich zmian.
 * </p>
 */


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Typ będzie określony w polu "type" w JSON
        include = JsonTypeInfo.As.PROPERTY, // Pole "type" będzie właściwością obiektu
        property = "type" // Nazwa pola w JSON, które wskaże typ (np. "Room", "OfficeRoom")
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Room.class, name = "Room"),
        @JsonSubTypes.Type(value = OfficeRoom.class, name = "OfficeRoom"),
        @JsonSubTypes.Type(value = Balcony.class, name = "Balcony"),
        @JsonSubTypes.Type(value = Pool.class, name = "Pool"),
        @JsonSubTypes.Type(value = Garage.class, name = "Garage")
})
public class Room {
    private String id; // Unikalny identyfikator pomieszczenia
    private String name; // Optional nazwa pomieszczenia
    private float area; // Powierzchnia pomieszczenia
    private float cube; // Objętość pomieszczenia

    // Constructor
    /**
     * Konstruktor domyślny bez parametrów.
     */
    public Room(){}

    /**
     * Konstruktor tworzący pomieszczenie o podanych parametrach.
     *
     * @param id unikalny identyfikator pomieszczenia
     * @param name opcjonalna nazwa pomieszczenia
     * @param area powierzchnia pomieszczenia
     * @param cube objętość pomieszczenia
     */
    public Room(String id, String name, float area, float cube) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cube = cube;
    }

    // Getters and setters

    /**
     * Zwraca identyfikator pomieszczenia.
     *
     * @return identyfikator pomieszczenia
     */
    public String getId() {
        return id;
    }

    /**
     * Ustawia identyfikator pomieszczenia.
     *
     * @param id identyfikator pomieszczenia
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę pomieszczenia.
     *
     * @return nazwa pomieszczenia
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nazwę pomieszczenia.
     *
     * @param name nazwa pomieszczenia
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca powierzchnię pomieszczenia.
     *
     * @return powierzchnia pomieszczenia
     */
    public float getArea() {
        return area;
    }

    /**
     * Ustawia powierzchnię pomieszczenia.
     *
     * @param area powierzchnia pomieszczenia
     */
    public void setArea(float area) {
        this.area = area;
    }

    /**
     * Zwraca kubaturę pomieszczenia.
     *
     * @return objętość pomieszczenia
     */
    public float getCube() {
        return cube;
    }

    /**
     * Ustawia powierzchnię pomieszczenia.
     *
     * @param cube powierzchnia pomieszczenia
     */
    public void setCube(float cube) {
        this.cube = cube;
    }


    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", cube=" + cube +
                '}';
    }
}
