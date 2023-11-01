package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {
    private Animal testAnimal;

    @BeforeEach
    void runBefore() {
        testAnimal = new Animal("Bear", 50, "Carnivore");
    }

    @Test
    void testConstructor() {
        assertEquals("Bear", testAnimal.getName());
        assertEquals(50, testAnimal.getWeight());
        assertEquals("Carnivore", testAnimal.getDiet());
        assertTrue(testAnimal.getLivingStatus());
    }
}
