package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a Zoo having a name and list of habitats
public class Zoo implements Writable {

    private String name; // the zoo name
    private List<Habitat> habitats; // list of habitats in zoo

    /*
     * EFFECTS: constructs zoo with given name. Initially zoos, have an
     *          empty list of habitats.
     */
    public Zoo(String name) {
        this.name = name;
        this.habitats = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Habitat> getHabitats() {
        return habitats;
    }

    /*
     * MODIFIES: this
     * EFFECTS: given habitat is added to habitats list
     */
    public void addHabitat(Habitat habitat) {
        habitats.add(habitat);
    }

    /*
     * REQUIRES: Animal name, original habitat, and destination habitat should
     *           all be valid and exist in the zoo.
     * MODIFIES: this
     * EFFECTS: given animal is moved from original habitat to destination habitat
     */
    public void moveAnimal(String name, String origin, String destination) {
        Habitat from;
        Habitat to;
        Animal animal = null;
        from = findHabitat(origin);
        if (from == null) {
            return;
        }
        to = findHabitat(destination);

        if (to == null) {
            return;
        }
        for (Animal atf : from.getAnimals()) {
            if (name.equals(atf.getName())) {
                animal = atf;
                break;
            }
        }
        if (animal == null) {
            return;
        }
        from.getAnimals().remove(animal);
        to.getAnimals().add(animal);
    }

    /*
     * REQUIRES: habitat should be valid and exist in the zoo.
     * EFFECTS: returns habitat if found, null if not found
     */
    public Habitat findHabitat(String habitat) {
        for (Habitat h : this.habitats) {
            if (habitat.equals(h.getName())) {
                return h;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("habitats", habitatsToJson());
        return json;
    }

    // EFFECTS: returns habitats in this zoo as a JSON array
    private JSONArray habitatsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Habitat h : habitats) {
            jsonArray.put(h.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for renaming zoo to EventLog
    public void logNameEvent() {
        EventLog.getInstance().logEvent(new Event("Renamed Zoo."));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for creating habitat to EventLog
    public void logCreateEvent() {
        EventLog.getInstance().logEvent(new Event("Created Habitat for Zoo."));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for saving zoo to EventLog
    public void logSaveEvent() {
        EventLog.getInstance().logEvent(new Event("Saved Zoo."));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for loading zoo to EventLog
    public void logLoadEvent() {
        EventLog.getInstance().logEvent(new Event("Loaded Zoo."));
    }

    // EFFECTS: prints to the console all the events that have been logged since the application started
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }

    }

}
