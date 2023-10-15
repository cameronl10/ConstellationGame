package persistence;

import model.ListOfQuizSet;
import model.QuizSet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
// Json class that takes a Json listofquizset object and puts it into a java object.

public class JsonReader {
    private String source;

    //EFFECTS: set the source file for the json reader to read from
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfQuizSet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfQuizSet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfQuizSet(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfQuizSet from JSON object and returns it
    private ListOfQuizSet parseListOfQuizSet(JSONObject jsonObject) {
        ListOfQuizSet qs = new ListOfQuizSet();
        addQuizSets(qs,jsonObject);
        return qs;
    }

    // MODIFIES: ListOfQuizSet
    // EFFECTS: parses QuizSets from JSON object and adds them to the list of quiz sets
    private void addQuizSets(ListOfQuizSet qs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Quizzes");
        for (Object json : jsonArray) {
            JSONObject nextQuiz = (JSONObject) json;
            addQuizSet(qs, nextQuiz);
        }
    }

    // MODIFIES: ListOfQuizSet
    // EFFECTS: parses QuizSet from JSON object and adds it to the list of quiz sets
    private void addQuizSet(ListOfQuizSet qs, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ArrayList<String> questions = new ArrayList<>();
        JSONArray jsonQuestions = jsonObject.getJSONArray("Questions");
        for (int i = 0; i < jsonQuestions.length();i++) {
            questions.add(jsonQuestions.getString(i));
        }
        QuizSet quiz = new QuizSet(name, questions);
        qs.addQuizToList(quiz);
    }
}
