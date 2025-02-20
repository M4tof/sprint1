package pl.put.poznan.buildinginfo.logic;

/**
 * Klasa reprezentująca garaż w budynku.
 * <p>
 * Celem klasy {@code Garage} jest przechowywanie oraz umożliwienie manimulacji
 * podstawowymi danymi garażu.
 * </p>
 * Do danych każdego pomieszczenie zaliczają się jego identyfikator, nazwa, powierzchnia, kubatura, oświetlenie oraz jego pojemność.
 * <p>
 * Metody zawarte w klasie umożliwiają zewnętrzny dostęp do tych prywatnych atrybutów oraz w miarę potrzeb do ich zmian.
 * </p>
 */

public class Garage extends Balcony{
    private int garageCapacity; //liczba samochodów do pomieszczenia

    // Constructor
    /**
     * Konstruktor domyślny bez parametrów.
     */
    public Garage(){}

    /**
     * Konstruktor tworzący pomieszczenie o podanych parametrach.
     *
     * @param id unikalny identyfikator pomieszczenia
     * @param name opcjonalna nazwa pomieszczenia
     * @param area powierzchnia pomieszczenia
     * @param cube objętość pomieszczenia
     * @param light oświetlenie pomieszczenia
     * @param garageCapacity liczba samochodów do pomieszczenia
     */
    public Garage(String id, String name, float area, float cube, float light, int garageCapacity) {
        super(id, name, area, cube, light);
        this.garageCapacity = garageCapacity;
    }

    /**
     * Zwraca liczbę samochodów do pomieszczenia.
     *
     * @return liczba samochodów do pomieszczenia
     */
    public float getGarageCapacity() {
        return garageCapacity;
    }

    /**
     * Ustawia nową liczbę samochodów do pomieszczenia.
     *
     * @param garageCapacity liczba samochodów do pomieszczenia
     */
    public void setGarageCapacity(int garageCapacity) {
        this.garageCapacity = garageCapacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", area=" + this.getArea() +
                ", cube=" + this.getCube() +
                ", light=" + this.getLight() +
                ", garage capacity=" + garageCapacity +
                '}';
    }
}
