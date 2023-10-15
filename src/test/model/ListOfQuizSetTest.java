package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ListOfQuizSetTest {
    ListOfQuizSet c1;
    @BeforeEach
    public void before(){
        c1 = new ListOfQuizSet();
    }

    @Test
    public void testSetup(){
        QuizSet fullTest = new QuizSet("full test");
        fullTest.createFullSet();
        c1.setUp();
        QuizSet c1FullSet = c1.getListOfQuizSet().get(0);
        assertEquals(fullTest.getQuestions().get(0),c1FullSet.getQuestions().get(0));
        assertEquals(fullTest.getQuestions().get(23),c1FullSet.getQuestions().get(23));
        assertEquals(fullTest.getQuestions().get(87),c1FullSet.getQuestions().get(87));
    }

    @Test
    public void testFormatListOfQuizSet(){
        assertEquals("", c1.formatListOfQuizSet());
        c1.addQuizToList(new QuizSet("q1"));
        assertEquals("q1[0] ", c1.formatListOfQuizSet());
        c1.addQuizToList(new QuizSet("q2"));
        assertEquals("q1[0] q2[1] ", c1.formatListOfQuizSet());
    }

    @Test
    public void testAddQuizToList(){
        QuizSet test = new QuizSet("Test");
        assertEquals(0,c1.getListOfQuizSet().size());
        c1.addQuizToList(test);
        assertEquals(1,c1.getListOfQuizSet().size());
        assertEquals(test,c1.getListOfQuizSet().get(0));
    }

}
