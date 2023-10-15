package persistence;

import model.ListOfQuizSet;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
//JSON class that saves a listofquizset object into a json listofquizsetobject to be saved at destination string

public class JsonWriter {
    private String destination;
    private PrintWriter writer;
    private static final int TAB = 4;

    //EFFECTS: creates writer to write data to destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of quiz sets  to file
    public void write(ListOfQuizSet qs) {
        JSONObject json = qs.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
