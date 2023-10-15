package ui.gui;

import model.Event;
import model.EventLog;
import model.ListOfQuizSet;
import model.QuizSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
//Creates the set manager GUI that displays all the quiz sets and allows the user to save,load and create a set.


public class SetManagerGUI extends JFrame {
    private JFrame menu;
    private ListOfQuizSet listOfQuizSet;
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/Constellation.json";

    //EFFECTS: creates a setmanagergui with a listofquizset object.
    public SetManagerGUI(ListOfQuizSet listOfQuizSet) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.listOfQuizSet = listOfQuizSet;
        menu = new JFrame();
    }

    //MODIFIES: this
    //EFFECTS: sets up the frame with a size, color and its components
    public void setup() {
        menu.setSize(600,300);
        menu.setLayout(new BorderLayout());
        menu.setBackground(new Color(7,7,52));
        addQuizList();
        addButtons();
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });
    }

    //EFFECTS: prints the event log for the application
    private void printLog() {
        Iterator<Event> it = EventLog.getInstance().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    //MODIFIES: this
    //EFFECTS: creates labels with the name of all the quiz sets in te listofquizsets and adds them to the menu
    private void addQuizList() {
        JPanel quizListPanel = new JPanel();
        quizListPanel.setLayout(new BoxLayout(quizListPanel, BoxLayout.X_AXIS));
        quizListPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JLabel text = new JLabel("Your sets");
        quizListPanel.setBackground(new Color(7,7,52));
        for (String s: getQuizSetName()) {
            JLabel label = new JLabel(s);
            label.setForeground(Color.white);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            quizListPanel.add(Box.createHorizontalStrut(10));
            quizListPanel.add(label);
        }
        menu.add(text,BorderLayout.NORTH);
        menu.add(quizListPanel, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: adds all the buttons to the frame and its functionality.
    private void addButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = createBackButton();
        JButton createButton = createCreateButton();
        JButton saveButton = createSaveButton();
        JButton loadButton = new JButton("Load");


        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadQuizSets();
                SetManagerGUI temp = new SetManagerGUI(listOfQuizSet);
                menu.dispose();
                temp.setup();
                showMessage("Loaded from: " + JSON_STORE);
            }
        });
        buttonPanel.add(backButton);
        buttonPanel.add(createButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        menu.add(buttonPanel,BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: creates the Back button which takes the user back to the main menu if clicked
    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.dispose();
                backToMenu(listOfQuizSet);
            }
        });
        return backButton;
    }

    //MODIFIES: this
    //EFFECTS: creates a main menu object and shows it to the user.
    private void backToMenu(ListOfQuizSet listofquizset) {
        ConstellationGameGUI menu = new ConstellationGameGUI(listofquizset);
        menu.start();
    }

    //MODIFIES: this
    //EFFECTS: creates the Save button that saves the current listofquizset to the json file
    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveQuizSets();
                showMessage("Saved to: " + JSON_STORE);
            }
        });
        return saveButton;
    }

    //MODIFIES: this
    //EFFECTS: creates the Create button that takes the user to the create GUI.
    private JButton createCreateButton() {
        JButton createButton = new JButton("Create set");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.dispose();
                showCreateSetGUI();
            }
        });
        return createButton;
    }

    //EFFECTS: creates a pop up menu that shows up when a user saves or loads.
    private void showMessage(String message) {
        JFrame popup = new JFrame();
        popup.setSize(300,150);
        JLabel text = new JLabel(message);
        popup.add(text,BorderLayout.CENTER);
        popup.setVisible(true);
    }

    //EFFECTS: creates a new CreateSetGUI and shows it to the user.
    private void showCreateSetGUI() {
        CreateSetGUI createGUI = new CreateSetGUI(listOfQuizSet);
        createGUI.setup();
    }

    //EFFECTS: creates an arraylist of all the quiz sets in the listofquizset.
    private ArrayList<String> getQuizSetName() {
        ArrayList<String> result  = new ArrayList<>();
        for (QuizSet qs : listOfQuizSet.getListOfQuizSet()) {
            result.add(qs.getName());
        }
        return result;
    }

    //MODIFIES: this, Constellation.json
    //EFFECTS: saves the listofquizset to the constellation json file
    private  void saveQuizSets() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfQuizSet);
            jsonWriter.close();
            System.out.println("Saved to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads quiz sets from file
    private  void loadQuizSets() {
        try {
            listOfQuizSet = jsonReader.read();
            System.out.println("Loaded from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }
}
