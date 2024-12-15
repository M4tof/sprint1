package pl.put.poznan.buildinginfo.logic;

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
public class Room {
    private String id; // Unikalny identyfikator pomieszczenia
    private String name; // Optional nazwa pomieszczenia
    private float area; // Powierzchnia pomieszczenia
    private float cube; // Objętość pomieszczenia
    private float heating; // Ogrzewanie
    private float light; // Oświetlenie

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
     * @param heating ogrzewanie pomieszczenia
     * @param light oświetlenie pomieszczenia
     */
    public Room(String id, String name, float area, float cube, float heating, float light) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
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

    /**
     * Zwraca parametry grzewcze pomieszczenia.
     *
     * @return parametry grzewcze
     */
    public float getHeating() {
        return heating;
    }

    /**
     * Ustawia nowe parametry grzewcze.
     *
     * @param heating parametry grzewcze
     */
    public void setHeating(float heating) {
        this.heating = heating;
    }

    /**
     * Zwraca oświetlenie pomieszczenia.
     *
     * @return oświetlenie
     */
    public float getLight() {
        return light;
    }

    /**
     * Ustawia nowe oświetlenie pomieszczenia.
     *
     * @param light oświetlenie
     */
    public void setLight(float light) {
        this.light = light;
    }

    /**
     * Zwraca tekstową reprezentację pomieszczenia.
     *
     * @return tekstowa reprezentacja pomieszczenia
     */
    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", cube=" + cube +
                ", heating=" + heating +
                ", light=" + light +
                '}';
    }
}
