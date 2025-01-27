package pl.put.poznan.buildinginfo.logic;

/**
 * Klasa reprezentująca basen w budynku.
 * <p>
 * Celem klasy {@code Pool} jest przechowywanie oraz umożliwienie manimulacji
 * podstawowymi danymi tego pokoju.
 * </p>
 * Do danych każdego pomieszczenie zaliczają się jego identyfikator, nazwa, powierzchnia, kubatura, oświetlenie oraz ogrzewanie.
 * <p>
 * Metody zawarte w klasie umożliwiają zewnętrzny dostęp do tych prywatnych atrybutów oraz w miarę potrzeb do ich zmian.
 * </p>
 */

public class Pool extends OfficeRoom{

    private float poolArea; // Powierzchnia samego basenu

    // Constructor
    /**
     * Konstruktor domyślny bez parametrów.
     */
    public Pool(){}

    /**
     * Konstruktor tworzący pomieszczenie o podanych parametrach.
     *
     * @param id unikalny identyfikator pomieszczenia
     * @param name opcjonalna nazwa pomieszczenia
     * @param area powierzchnia pomieszczenia
     * @param cube objętość pomieszczenia
     * @param heating ogrzewanie pomieszczenia
     * @param light oświetlenie pomieszczenia
     * @param poolArea powierzchnia basenu
     */
    public Pool(String id, String name, float area, float cube, float heating, float light, float water, float poolArea) {
        super(id, name, area, cube, heating, light, water);
        this.poolArea = poolArea;
    }

    /**
     * Zwraca powierzchnię basenową.
     *
     * @return oświetlenie
     */
    public float getPoolArea() {
        return poolArea;
    }

    /**
     * Ustawia nową powierzchnię basenową.
     *
     * @param poolArea oświetlenie
     */
    public void setPoolArea(float poolArea) {
        this.poolArea = poolArea;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", area=" + this.getArea() +
                ", cube=" + this.getCube() +
                ", heating=" + this.getHeating() +
                ", light=" + this.getLight() +
                ", pool area=" + poolArea +
                '}';
    }
}
