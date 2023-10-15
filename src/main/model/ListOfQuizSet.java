package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a class for a list of quiz sets that default starts with a set that has all 88 official constellations
// but can also add distinct Quiz Sets
public class ListOfQuizSet implements Writable {
    private ArrayList<QuizSet> listOfQuizSets = new ArrayList<>();

    //MODIFIES: this
    //EFFECTS: creates the full set constellation set as a default quiz set for the game.
    public void setUp() {
        QuizSet fullSet = new QuizSet("full set");
        fullSet.createFullSet();
        listOfQuizSets.add(fullSet);
        EventLog.getInstance().logEvent(new Event("set up the default quiz set"));
    }

    //MODIFIES: this
    //EFFECTS: adds given QuizSet q to the listOfQuizSets
    public void addQuizToList(QuizSet q) {
        listOfQuizSets.add(q);
        EventLog.getInstance().logEvent(new Event("added " + q.getName() + " to  the list of quizzes"));
    }

    //EFFECTS: returns the ArrayList listOfQuizSet
    public ArrayList<QuizSet> getListOfQuizSet() {
        return this.listOfQuizSets;
    }

    //EFFECTS: prints the listOfQuizSet's individual QuizSet's name with index next to it.
    public String formatListOfQuizSet() {
        String result = "";
        for (int i = 0; i < listOfQuizSets.size(); i++) {
            result += listOfQuizSets.get(i).getName() + ("[" + i + "] ");
        }
        EventLog.getInstance().logEvent(new Event("formatted list of quizzes in order: " + result));
        return result;
    }

    //EFFECTS: returns the number of quizzes in
    public int getNumQuizzes() {
        return this.listOfQuizSets.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Quizzes", quizzesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray quizzesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (QuizSet q : listOfQuizSets) {
            jsonArray.put(q.toJson());
        }

        return jsonArray;
    }
}


