package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
class QuizSetTest {
    QuizSet q1;
    QuizSet q2;
    @BeforeEach
    public void setup(){
        q1 = new QuizSet("set1");
        q2 = new QuizSet("set2");
    }

    @Test
    public void testQuizSetConstructor(){
        assertEquals("set1",q1.getName());
        assertEquals("set2",q2.getName());
        assertEquals(null,q1.getQuestions());
        assertEquals(null,q2.getQuestions());
    }

    @Test
    public void testCreateFullSet(){
        q1.createFullSet();
        assertEquals("Telescopium",q1.getQuestions().get(0));
        assertEquals("Cepheus",q1.getQuestions().get(q1.getQuestions().size() -1 ));
        assertEquals("Ophiuchus",q1.getQuestions().get(44));
    }

    @Test
    public void testSetQuestions(){
        ArrayList<String> testQuestions = new ArrayList<>();
        testQuestions.add("test");

        q1.setQuestions(testQuestions);
        assertEquals(testQuestions,q1.getQuestions());

        ArrayList<String> testQuestions2 = new ArrayList<>();
        testQuestions.add("test2");

        q1.setQuestions(testQuestions2);
        assertEquals(testQuestions2,q1.getQuestions());
    }

}