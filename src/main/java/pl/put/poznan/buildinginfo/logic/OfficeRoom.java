package pl.put.poznan.buildinginfo.logic;

/**
 * Klasa reprezentująca pokój biurowy w budynku.
 * <p>
 * Celem klasy {@code Garage} jest przechowywanie oraz umożliwienie manimulacji
 * podstawowymi danymi tego pokoju.
 * </p>
 * Do danych każdego pomieszczenie zaliczają się jego identyfikator, nazwa, powierzchnia, kubatura, oświetlenie oraz ogrzewanie.
 * <p>
 * Metody zawarte w klasie umożliwiają zewnętrzny dostęp do tych prywatnych atrybutów oraz w miarę potrzeb do ich zmian.
 * </p>
 */

public class OfficeRoom extends Room {


    private float heating; // Ogrzewanie
    private float light; // Oświetlenie
    private float water; // Zużycie wody

    // Constructor
    /**
     * Konstruktor domyślny bez parametrów.
     */
    public OfficeRoom(){}

    /**
     * Konstruktor tworzący pomieszczenie o podanych parametrach.
     *
     * @param id unikalny identyfikator pomieszczenia
     * @param name opcjonalna nazwa pomieszczenia
     * @param area powierzchnia pomieszczenia
     * @param cube objętość pomieszczenia
     * @param heating ogrzewanie pomieszczenia
     * @param light oświetlenie pomieszczenia
     * @param water zużycie wody na pomieszczenie
     */
    public OfficeRoom(String id, String name, float area, float cube, float heating, float light, float water) {
        super(id, name, area, cube);
        this.heating = heating;
        this.light = light;
        this.water = water;
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
     * Zwraca zużycie wody pomieszczenia.
     *
     * @return zużycie wody
     */
    public float getWater() {
        return water;
    }

    /**
     * Ustawia nowe zużycie wody pomieszczenia.
     *
     * @param water zużycie wody
     */
    public void setWater(float water) {
        this.water = water;
    }

    /**
     * Zwraca tekstową reprezentację pomieszczenia.
     *
     * @return tekstowa reprezentacja pomieszczenia
     */

    @Override
    public String toString() {
        return "Room{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", area=" + this.getArea() +
                ", cube=" + this.getCube() +
                ", heating=" + heating +
                ", light=" + light +
                ", water=" + water +
                '}';
    }
}
