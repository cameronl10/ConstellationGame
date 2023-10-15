package persistence;


import model.ListOfQuizSet;
import model.QuizSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfQuizSet lqs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyConstellation() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyConstellation.json");
        try {
            ListOfQuizSet qs = reader.read();
            assertEquals(0, qs.getNumQuizzes());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralConstellation() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralConstellation.json");
        try {
            ListOfQuizSet qs = reader.read();
            List<QuizSet> quizSets = qs.getListOfQuizSet();
            assertEquals(2, quizSets.size());
            checkQuizSet("full set", "Telescopium", quizSets.get(0));
            checkQuizSet("Ursas", "Ursa Major", quizSets.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}