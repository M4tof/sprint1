package pl.put.poznan.buildinginfo.logic;

public class OfficeRoom extends Room {


    private float heating; // Ogrzewanie
    private float light; // Oświetlenie

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
     */
    public OfficeRoom(String id, String name, float area, float cube, float heating, float light) {
        super(id, name, area, cube);
        this.heating = heating;
        this.light = light;
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
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", area=" + this.getArea() +
                ", cube=" + this.getCube() +
                ", heating=" + heating +
                ", light=" + light +
                '}';
    }
}
