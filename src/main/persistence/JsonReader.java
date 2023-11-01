package persistence;

import model.Animal;
import model.Habitat;
import model.Zoo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads zoo from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads zoo from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Zoo read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseZoo(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses zoo from JSON object and returns it
    private Zoo parseZoo(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Zoo z = new Zoo(name);
        addHabitats(z, jsonObject);
        return z;
    }

    // MODIFIES: z
    // EFFECTS: parses habitats from JSON object and adds them to zoo
    private void addHabitats(Zoo z, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("habitats");
        for (Object json : jsonArray) {
            JSONObject nextHabitat = (JSONObject) json;
            addHabitat(z, nextHabitat);
        }
    }

    // MODIFIES: z
    // EFFECTS: parses habitat from JSON object and adds it to zoo
    private void addHabitat(Zoo z, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double averageTemperature = jsonObject.getDouble("average temperature");
        int capacity = jsonObject.getInt("capacity");
        Habitat h = new Habitat(name, averageTemperature, capacity);
        addAnimals(h, jsonObject);
        z.addHabitat(h);
    }

    // MODIFIES: h
    // EFFECTS: parses animals from JSON object and adds them to habitat
    private void addAnimals(Habitat h, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("animals");
        for (Object json : jsonArray) {
            JSONObject nextAnimal = (JSONObject) json;
            addAnimal(h, nextAnimal);
        }
    }

    // MODIFIES: h
    // EFFECTS: parses animal from JSON object and adds it to habitat
    private void addAnimal(Habitat h, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double weight = jsonObject.getDouble("weight");
        String diet = jsonObject.getString("diet");
        boolean livingStatus = jsonObject.getBoolean("living status");
        Animal a = new Animal(name, weight, diet);
        a.setLivingStatus(livingStatus);
        h.addAnimal(a);
    }

}
