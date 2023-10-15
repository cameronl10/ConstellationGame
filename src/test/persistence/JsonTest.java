package persistence;


import model.QuizSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkQuizSet(String name, String question, QuizSet qs) {
        assertEquals(name, qs.getName());
        assertEquals(question, qs.getQuestions().get(0));
    }
}
