package pl.put.poznan.buildinginfo.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa reprezentująca budynek, który może zawierać wiele pięter.
 * <p>
 * Celem klasy {@code Building} jest zarządzanie piętrami znajdującymi się w budynku.
 * </p>
 * <p>
 * Klasa umożliwia obliczanie ogólnych danych dla wszystkich pomieszczeń, na wszystkich poziomach znajdujących się w budynku.
 * </p>
 * Budynek jest identyfikowany przez unikalny identyfikator oraz opcjonalną nazwę.
 * Każdy budynek składa się z listy pięter, które mogą zawierać pomieszczenia.
 * <p>
 */

public class Building {
    private String id; // Unikalny identyfikator budynku
    private String name; // Optional nazwa budynku
    private List<Level> levels; // Lista wszystkich pięter będących w budynku

    // Constructor
    /**
     * Konstruktor domyślny - tworzy budynek bez pięter.
     */
    public Building(){}

    /**
     * Konstruktor tworzący budynek o podanym id oraz nazwie.
     *
     * @param id   unikalny identyfikator budynku
     * @param name opcjonalna nazwa budynku
     */
    public Building(String id, String name) {
        this.id = id;
        this.name = name;
        this.levels = new ArrayList<>();
    }

    // Methods to manage levels
    /**
     * Dodaje piętro do budynku.
     *
     * @param level piętro do dodania
     */
    public void addLevel(Level level) {
        this.levels.add(level);
    }

    /**
     * Usuwa piętro z budynku.
     *
     * @param level piętro do usunięcia
     */
    public void removeRoom(Level level) {
        this.levels.remove(level);
    }

    // Getters and setters
    /**
     * Pobiera identyfikator budynku.
     *
     * @return identyfikator budynku
     */
    public String getId() {
        return id;
    }

    /**
     * Ustawia identyfikator budynku.
     *
     * @param id nowy identyfikator budynku
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Pobiera nazwę budynku.
     *
     * @return nazwa budynku
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nazwę budynku.
     *
     * @param name nowa nazwa budynku
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Pobiera listę pięter w budynku.
     *
     * @return lista pięter
     */
    public List<Level> getLevels() {
        return levels;
    }

    /**
     * Ustawia piętra budynku na nowe
     *
     * @param levels nowa lista pięter
     */
    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    /**
     * Wyszukuje piętro w budynku na podstawie jego identyfikatora.
     *
     * @param Id identyfikator poszukiwanego piętra
     *
     * @return piętro o szukanym identyfikatorze lub null, jeśli nie zostało znalezione
     */
    public Level getLevelById(String Id){
        for (Level i: levels){
            if ( Objects.equals(Id,i.getId()) ){
                return i;
            }
        }
        return null;
    }

    /**
     * Oblica całkowitą powierzchnię budynku - sumę powierzchni wszystkich piętr, które się w nim znajdują.
     *
     * @return całkowita powierzchnia budynku
     */
    public float getBuildingArea(){
        float sum = 0;
        for(Level level : this.getLevels()){
            sum += level.getLevelArea();
        }
        return sum;
    }

    /**
     * Oblicza całkowitą objętość budynku - sumę objętości wszystkich pięter budynki.
     *
     * @return całkowita objętość budynku
     */
    public float getBuildingCube(){
        float sum = 0;
        for(Level level : this.getLevels()){
            sum += level.getLevelCube();
        }
        return sum;
    }

    /**
     * Oblicza średnią efektywność oświetlenia w całym budynku dla wszystkich pięter.
     *
     * @return średnia efektywność oświetlenia całego budynku lub 0 jeżeli budynek nie ma pięter
     */
    public float getBuildingLight(){
        float sum = 0, counter = 0;
        for(Level level : this.getLevels()){
            sum += level.getLevelLight();
            counter++;
        }
        if (counter > 0) {
            return sum/counter;
        }
        return 0;
    }

    /**
     * Oblicza średnie zużycie ciepła w budynku dla wszystkich pięter.
     *
     * @return śrenie zużycie ciepłą w budynku lub 0 jeżeli budynek nie ma pięter
     */
    public float getBuildingHeating(){
        float sum = 0, counter = 0;
        for(Level level : this.getLevels()){
            sum += level.getLevelHeating();
            counter++;
        }
        if (counter > 0) {
            return sum/counter;
        }
        return 0;
    }

//    NEW
    public void removeLevel(String id){
        Level levelToRemove = getLevelById(id);
        if (levelToRemove != null){
            levels.remove(levelToRemove);
        }else {
            throw new IllegalArgumentException("No level found with ID " + id);
        }
    }

    /**
     * Zwraca tekstową reprezentację budynku
     *
     * @return tekstowa reprezentacja budynku
     */
    @Override
    public String toString() {
        return "Building{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", levels=" + levels +
                '}';
    }
}
