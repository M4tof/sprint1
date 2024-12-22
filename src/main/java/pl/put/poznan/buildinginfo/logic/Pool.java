package pl.put.poznan.buildinginfo.logic;

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
    public Pool(String id, String name, float area, float cube, float heating, float light, float poolArea) {
        super(id, name, area, cube, heating, light);
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
