package pl.put.poznan.buildinginfo.logic;

/**
 * Klasa reprezentująca balkon w budynku.
 * <p>
 * Celem klasy {@code Balcon} jest przechowywanie oraz umożliwienie manimulacji
 * podstawowymi danymi tego pokoju.
 * </p>
 * Do danych każdego pomieszczenie zaliczają się jego identyfikator, nazwa, powierzchnia, kubatura oraz oświetlenie.
 * <p>
 * Metody zawarte w klasie umożliwiają zewnętrzny dostęp do tych prywatnych atrybutów oraz w miarę potrzeb do ich zmian.
 * </p>
 */

public class Balcony extends Room{

    private float light; // Oświetlenie

    // Constructor
    /**
     * Konstruktor domyślny bez parametrów.
     */
    public Balcony(){}

    /**
     * Konstruktor tworzący pomieszczenie o podanych parametrach.
     *
     * @param id unikalny identyfikator pomieszczenia
     * @param name opcjonalna nazwa pomieszczenia
     * @param area powierzchnia pomieszczenia
     * @param cube objętość pomieszczenia
     * @param light oświetlenie pomieszczenia
     */
    public Balcony(String id, String name, float area, float cube, float light) {
        super(id, name, area, cube);
        this.light = light;
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
                ", light=" + light +
                '}';
    }
}
