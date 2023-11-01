package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HabitatTest {
    private Habitat testHabitat;
    private Animal bearAnimal;
    private Animal squirrelAnimal;

    @BeforeEach
    void runBefore() {
        testHabitat = new Habitat("Forest", 20, 1);
        bearAnimal = new Animal("Bear", 50, "Carnivore");
        squirrelAnimal = new Animal("Squirrel", 20, "Herbivore");
    }

    @Test
    void testConstructor() {
        assertEquals("Forest", testHabitat.getName());
        assertEquals(20, testHabitat.getAverageTemperature());
        assertEquals(1, testHabitat.getCapacity());
        assertTrue(testHabitat.getAnimals().isEmpty());
    }

    @Test
    void testOvercrowded() {
        assertFalse(testHabitat.overcrowded());
        testHabitat.addAnimal(new Animal("Bear", 50, "Carnivore"));
        assertFalse(testHabitat.overcrowded());
        testHabitat.addAnimal(new Animal("Squirrel", 20, "Herbivore"));
        assertTrue(testHabitat.overcrowded());
    }

    @Test
    void testAddHabitat() {
        testHabitat.addAnimal(squirrelAnimal);
        assertEquals(squirrelAnimal, testHabitat.getAnimals().get(0));
        testHabitat.addAnimal(bearAnimal);
        assertEquals(bearAnimal, testHabitat.getAnimals().get(1));
    }
}
