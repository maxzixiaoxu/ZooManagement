package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents an animal having a name, weight, diet, and living status
public class Animal implements Writable {

    private String name; // the animal name
    private double weight; // the animal's weight
    private String diet; // the animal's diet
    private boolean livingStatus; // the living status of the animal (alive or dead)

    /*
     * EFFECTS: constructs animal with given name, weight and diet.
     *          Initially, animals have true for their livingStatus;
     */
    public Animal(String name, double weight, String diet) {
        this.name = name;
        this.weight = weight;
        this.diet = diet;
        this.livingStatus = true;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getDiet() {
        return diet;
    }

    public boolean getLivingStatus() {
        return livingStatus;
    }

    public void setLivingStatus(boolean livingStatus) {
        this.livingStatus = livingStatus;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weight", weight);
        json.put("diet", diet);
        json.put("living status", livingStatus);
        return json;
    }

}

