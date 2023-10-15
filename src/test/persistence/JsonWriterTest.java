package persistence;

import model.ListOfQuizSet;
import model.QuizSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            ListOfQuizSet qs = new ListOfQuizSet();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyConstellation() {
        try {
           ListOfQuizSet qs = new ListOfQuizSet();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyConstellation.json");
            writer.open();
            writer.write(qs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyConstellation.json");
            qs = reader.read();
            assertEquals(0, qs.getNumQuizzes());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralConstellation() {
        try {
            ArrayList<String> questions = new ArrayList<>();
            questions.add("Ursa Minor");
            ListOfQuizSet qs = new ListOfQuizSet();
            qs.addQuizToList(new QuizSet("test1",questions));
            ArrayList<String> questions2 = new ArrayList<>();
            questions2.add("Ursa Major");
            questions2.add("Ursa Minor");
            qs.addQuizToList(new QuizSet("test2",questions2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralConstellation.json");
            writer.open();
            writer.write(qs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralConstellation.json");
            qs = reader.read();
            List<QuizSet> quizSets = qs.getListOfQuizSet();
            assertEquals(2, quizSets.size());
            checkQuizSet("test1", "Ursa Minor", quizSets.get(0));
            checkQuizSet("test2", "Ursa Major", quizSets.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
