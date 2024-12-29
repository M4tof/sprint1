package pl.put.poznan.buildinginfo.logic;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa reprezentująca repozytorium budynków.
 * <p>
 * Celem klasy {@code BuildingRepository} jest zarządzanie kolekcją budynków w systemie,
 * umożliwiając ich dodawanie, wyszukiwanie oraz utrzymywanie unikalności identyfikatorów budynków,
 * poziomów i pomieszczeń w obrębie repozytorium.
 * </p>
 */
@Repository
public class BuildingRepository {
    private static BuildingRepository instance; // Jedyna instancja repozytorium
    private final List<Building> buildings; // Lista przechowująca budynki

    /**
     * Prywatny konstruktor, inicjalizujący pustą listę budynków.
     */
    private BuildingRepository() {
        this.buildings = new ArrayList<>();
    }

    /**
     * Zwraca instancję klasy BuildingRepository.
     * Jeżeli instancja nie istnieje tworzy ją.
     *
     * @return instancja klasy BuildingRepository
     */
    public static synchronized BuildingRepository getInstance() {
        if (instance == null) {
            instance = new BuildingRepository();
        }
        return instance;
    }

    /**
     * Dodaje nowy budynek do repozytorium budynków.
     * Sprawdza czy podany budynek nie istnieje już w repozytorium oraz czy piętra i pomieszczenia nie mają nieunikalnych identyfikatorów.
     *
     * @param building budynek do dodania
     *
     * @throws IllegalArgumentException jeżeli budynek o takim identyfikatorze już istnieje w repozytorium lub gdy identyfikatory poziomów lub pomieszczeń są nieunikalne
     */
    public void addBuilding(Building building) {
        // Check if a building with the same ID already exists
        Building existingBuilding = getBuildingById(building.getId());
        if (existingBuilding != null) {
            throw new IllegalArgumentException("A building with ID " + building.getId() + " already exists.");
        }

        // Check for duplicate level IDs within the building
        for (var level : building.getLevels()) {
            for (var otherLevel : building.getLevels()) {
                if (!level.equals(otherLevel) && Objects.equals(level.getId(), otherLevel.getId())) {
                    throw new IllegalArgumentException("Duplicate level ID " + level.getId() + " found.");
                }
            }

            // Check for duplicate room IDs within the level
            List<String> roomIds = new ArrayList<>();
            for (var room : level.getRooms()) {
                if (roomIds.contains(room.getId())) {
                    throw new IllegalArgumentException("Duplicate room ID " + room.getId() + " found in level " + level.getId());
                }
                roomIds.add(room.getId());
            }
        }

        // If no duplicates are found, add the building to the repository
        buildings.add(building);
    }

    /**
     * Tworzy kopię repozytorium budynków, celem uniknięcia bezpośredniej modyfikacji repozytorium.
     *
     * @return kopia listy budynków w repozytorium
     */
    public List<Building> getAllBuildings() {
        return new ArrayList<>(buildings); // Return a copy to avoid direct manipulation
    }

    /**
     * Wyszkukuje budynek na podstawie jego identyfikatora.
     *
     * @param Id identyfikator poszukiwanego budynku
     *
     * @return budynek o szukanym identyfikatorze lub null jeśli nie istnieje
     */
    public Building getBuildingById(String Id){
        for (Building i: buildings){
            if ( Objects.equals(Id,i.getId()) ){
                return i;
            }
        }
        return null;
    }

    //NEW
    public void removeBuilding(String Id) {
        Building buildingToRemove = getBuildingById(Id);
        if (buildingToRemove != null) {
            buildings.remove(buildingToRemove);
        } else {
            throw new IllegalArgumentException("No building found with ID " + Id);
        }
    }

}
