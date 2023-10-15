package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a single quiz set with a name and an arraylist of questions
public class QuizSet implements Writable {
    private final String name;
    private ArrayList<String> questions;


    public QuizSet(String name) {
        this.name = name;
    }

    //EFFECTS: constructs a QuizSet with given title and empty set of questions
    public QuizSet(String name, ArrayList<String> questions) {
        this.name = name;
        this.questions = questions;
    }


    //EFFECTS: returns the name of the QuizSet
    public String getName() {
        return this.name;
    }

    //EFFECTS: returns the question set of the QuizSet
    public ArrayList<String> getQuestions() {
        return this.questions;
    }

    //MODIFIES: this
    //EFFECTS: changes the current quiz sets question list to the given one
    public void setQuestions(ArrayList<String> questions) {
        EventLog.getInstance().logEvent(new Event("set questions at quiz set: " + this.name));
        this.questions = questions;
    }

    //MODIFIES: this
    //EFFECTS: changes the QuizSet questions arraylist to have all the names of the 88 constellations.
    public void createFullSet() {
        FullConstellationSet temp = new FullConstellationSet();
        EventLog.getInstance().logEvent(new Event("changed this quiz sets question to the full set"));
        this.questions = temp.getAllNames();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Questions", questions);
        return json;
    }
}
