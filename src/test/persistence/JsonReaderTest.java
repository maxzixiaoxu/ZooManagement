package persistence;

import model.Habitat;
import model.Zoo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Zoo z = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyZoo() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyZoo.json");
        try {
            Zoo z = reader.read();
            assertEquals("default", z.getName());
            assertEquals(0, z.getHabitats().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralZoo() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralZoo.json");
        try {
            Zoo z = reader.read();
            assertEquals("Felix's Zoo", z.getName());
            List<Habitat> habitats = z.getHabitats();
            assertEquals(2, habitats.size());
            checkHabitat("desert", 20, 2, habitats.get(0));
            checkHabitat("ocean", 5, 1, habitats.get(1));
            assertEquals("bird", habitats.get(0).getAnimals().get(0).getName());
            assertEquals(20, habitats.get(0).getAnimals().get(0).getWeight());
            assertEquals("herbivore", habitats.get(0).getAnimals().get(0).getDiet());
            assertEquals(true, habitats.get(0).getAnimals().get(0).getLivingStatus());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
