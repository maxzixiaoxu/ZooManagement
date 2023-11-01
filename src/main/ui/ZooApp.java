package ui;

import model.Animal;
import model.Habitat;
import model.Zoo;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ZooApp {
    private static final String JSON_STORE = "./data/myFile.json";
    private Scanner input;
    private Zoo zoo;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs zoo and runs application
    public ZooApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        zoo = new Zoo("default");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        System.out.println("Do you want to load zoo from file if a saved file exists?");
        while (true) {
            System.out.println("Select from:");
            System.out.println("\ty -> yes");
            System.out.println("\tn -> no");
            String command = input.next();
            if (command.equals("y")) {
                loadZoo();
                break;
            } else if (command.equals("n")) {
                System.out.println("\nWelcome to the Zoo!");
                break;
            } else {
                System.out.println("Selection not valid...");
            }
        }
        runZoo();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runZoo() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu1();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand1(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand1(String command) {
        if (command.equals("n")) {
            changeZooName();
        } else if (command.equals("c")) {
            createHabitat();
        } else if (command.equals("e")) {
            editHabitat();
        } else if (command.equals("m")) {
            moveAnimal();
        } else if (command.equals("s")) {
            saveZoo();
        } else if (command.equals("l")) {
            loadZoo();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu1() {
        System.out.println("Select from:");
        System.out.println("\tn -> enter/change zoo name");
        System.out.println("\tc -> create habitat");
        System.out.println("\te -> edit habitat");
        System.out.println("\tm -> move animal");
        System.out.println("\ts -> save zoo to file");
        System.out.println("\tl -> load zoo from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand2(String command, Habitat edit) {
        if (command.equals("c")) {
            createAnimal(edit);
        } else if (command.equals("v")) {
            viewAnimal(edit);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu2() {
        System.out.println("Select from:");
        System.out.println("\tc -> create animal");
        System.out.println("\tv -> view animal");
    }

    // MODIFIES: this
    // EFFECTS: changes zoo name
    private void changeZooName() {
        System.out.println("The current name of the zoo is: " + zoo.getName());
        System.out.println("Please enter the new name for the zoo:");
        System.out.println("Name:");
        String n = input.next();
        zoo.setName(n);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds a habitat
    private void createHabitat() {
        System.out.println("Please enter the following information for your habitat:");
        System.out.println("Name:");
        String name = input.next();
        System.out.println("Average Temperature:");
        double averageTemperature = input.nextDouble();
        System.out.println("Capacity:");
        int capacity = input.nextInt();
        zoo.addHabitat(new Habitat(name, averageTemperature, capacity));
    }

    // REQUIRES: can't be called until habitat exists
    // MODIFIES: this
    // EFFECTS: edits the habitats
    private void editHabitat() {
        System.out.println("All Habitats:");
        for (Habitat h : zoo.getHabitats()) {
            System.out.println(h.getName());
        }
        System.out.println("Choose habitat to edit: ");
        String habitat = input.next();
        Habitat edit = null;
        for (Habitat h : zoo.getHabitats()) {
            if (habitat.equals(h.getName())) {
                edit = h;
                break;
            }
        }
        System.out.println("Habitat chosen:" + edit.getName());
        displayMenu2();
        String command = input.next();
        command = command.toLowerCase();
        processCommand2(command, edit);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds an animal to given habitat
    private void createAnimal(Habitat edit) {
        System.out.println("Please enter the following information for your animal:");
        System.out.println("Name:");
        String name = input.next();
        System.out.println("Weight:");
        double weight = input.nextDouble();
        System.out.println("Diet:");
        String diet = input.next();
        edit.addAnimal(new Animal(name, weight, diet));
        if (edit.overcrowded()) {
            System.out.println("This habitat is overcrowded!");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays all the animals
    private void viewAnimal(Habitat edit) {
        System.out.println("All Animals:");
        for (Animal a : edit.getAnimals()) {
            System.out.println("\nName: " + a.getName());
            System.out.println("Weight: " + a.getWeight());
            System.out.println("Diet: " + a.getDiet());
            if (a.getLivingStatus()) {
                System.out.println("Status: Alive");
            } else {
                System.out.println("Status: Dead");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: moves animal to desired location
    private void moveAnimal() {
        System.out.println("Please enter the following information:");
        System.out.println("Animal to move:");
        String name = input.next();
        System.out.println("Animal Original Location:");
        String origin = input.next();
        System.out.println("Animal Destination");
        String destination = input.next();
        zoo.moveAnimal(name, origin, destination);
    }

    // EFFECTS: saves the zoo to file
    private void saveZoo() {
        try {
            jsonWriter.open();
            jsonWriter.write(zoo);
            jsonWriter.close();
            System.out.println("Saved " + zoo.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads zoo from file
    private void loadZoo() {
        try {
            zoo = jsonReader.read();
            System.out.println("Loaded " + zoo.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}


