package persistence;

import model.Animal;
import model.Habitat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkHabitat(String name, double averageTemperature, int capacity, Habitat habitat) {
        assertEquals(name, habitat.getName());
        assertEquals(averageTemperature, habitat.getAverageTemperature());
        assertEquals(capacity, habitat.getCapacity());
    }
}
