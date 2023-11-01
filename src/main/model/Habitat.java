package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a habitat having a name, average temperature, capacity of the habitat, and list of animals
public class Habitat implements Writable {

    private String name;                   // the habitat name
    private double averageTemperature;                // the average temperature of the habitat
    private int capacity;                        // capacity of the habitat
    private List<Animal> animals;   // list of animals in habitat

    /*
     * EFFECTS: constructs habitat with given name, averageTemperature and capacity.
     *          Initially, habitats have an empty list of animals.
     */
    public Habitat(String name, double averageTemperature, int capacity) {
        this.name = name;
        this.averageTemperature = averageTemperature;
        this.capacity = capacity;
        this.animals = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    /*
     * MODIFIES: this
     * EFFECTS: given animal is added to animal list
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Is the habitat overcrowded?
    /*
     * EFFECTS: returns true if habitat is overcrowded
     */
    public boolean overcrowded() {
        return (animals.size() > this.capacity);
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for viewing habitat to EventLog
    public void logViewEvent() {
        EventLog.getInstance().logEvent(new Event("Viewed Habitats in Zoo."));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("average temperature", averageTemperature);
        json.put("capacity", capacity);
        json.put("animals", animalsToJson());

        return json;
    }

    // EFFECTS: returns animals in this habitat as a JSON array
    private JSONArray animalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Animal a : animals) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

}
