package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {
    private Zoo testZoo;
    private Habitat forestHabitat;
    private Habitat arcticHabitat;
    private Animal bearAnimal;
    private Animal squirrelAnimal;

    @BeforeEach
    void runBefore() {
        testZoo = new Zoo("Felix's Zoo");
        forestHabitat = new Habitat("Forest", 20, 1);
        arcticHabitat = new Habitat("Arctic", -10, 1);
    }

    @Test
    void testConstructor() {
        assertEquals("Felix's Zoo", testZoo.getName());
        assertTrue(testZoo.getHabitats().isEmpty());
    }

    @Test
    void testAddHabitat() {
        testZoo.addHabitat(forestHabitat);
        assertEquals(forestHabitat, testZoo.getHabitats().get(0));
        testZoo.addHabitat(arcticHabitat);
        assertEquals(arcticHabitat, testZoo.getHabitats().get(1));
    }

    @Test
    void testSetName() {
        testZoo.setName("My Zoo");
        assertEquals("My Zoo", testZoo.getName());
    }

    @Test
    void testMoveAnimal() {
        bearAnimal = new Animal("Bear", 50, "Carnivore");
        squirrelAnimal = new Animal("Squirrel", 20, "Herbivore");
        forestHabitat.addAnimal(bearAnimal);
        forestHabitat.addAnimal(squirrelAnimal);
        arcticHabitat.addAnimal(bearAnimal);
        arcticHabitat.addAnimal(squirrelAnimal);
        testZoo.addHabitat(forestHabitat);
        testZoo.addHabitat(arcticHabitat);
        testZoo.moveAnimal("Bear", "Forest", "Arctic");
        assertEquals(1, testZoo.getHabitats().get(0).getAnimals().size());
        assertEquals("Bear", testZoo.getHabitats().get(1).getAnimals().get(2).getName());
        testZoo.moveAnimal("Panda", "Forest", "Arctic");
        assertEquals(1, testZoo.getHabitats().get(0).getAnimals().size());
        assertEquals("Bear", testZoo.getHabitats().get(1).getAnimals().get(2).getName());
        testZoo.moveAnimal("Bear", "Ocean", "Desert");
        assertEquals(1, testZoo.getHabitats().get(0).getAnimals().size());
        assertEquals("Bear", testZoo.getHabitats().get(1).getAnimals().get(2).getName());
        testZoo.moveAnimal("Bear", "Forest", "Desert");
        assertEquals(1, testZoo.getHabitats().get(0).getAnimals().size());
        assertEquals("Bear", testZoo.getHabitats().get(1).getAnimals().get(2).getName());
    }
}