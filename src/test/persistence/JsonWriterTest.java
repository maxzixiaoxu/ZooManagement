package persistence;

import model.Animal;
import model.Habitat;
import model.Zoo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Zoo z = new Zoo("My Zoo");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyZoo() {
        try {
            Zoo z = new Zoo("default");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyZoo.json");
            writer.open();
            writer.write(z);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyZoo.json");
            z = reader.read();
            assertEquals("default", z.getName());
            assertEquals(0, z.getHabitats().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralZoo() {
        try {
            Zoo z = new Zoo("My Zoo");
            z.addHabitat(new Habitat("desert", 20, 2));
            z.addHabitat(new Habitat("ocean", 5, 1));
            z.getHabitats().get(0).addAnimal(new Animal("bird", 20, "herbivore"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralZoo.json");
            writer.open();
            writer.write(z);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralZoo.json");
            z = reader.read();
            assertEquals("My Zoo", z.getName());
            List<Habitat> habitats = z.getHabitats();
            assertEquals(2, habitats.size());
            checkHabitat("desert", 20, 2, habitats.get(0));
            checkHabitat("ocean", 5, 1, habitats.get(1));
            assertEquals("bird", habitats.get(0).getAnimals().get(0).getName());
            assertEquals(20, habitats.get(0).getAnimals().get(0).getWeight());
            assertEquals("herbivore", habitats.get(0).getAnimals().get(0).getDiet());
            assertEquals(true, habitats.get(0).getAnimals().get(0).getLivingStatus());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
