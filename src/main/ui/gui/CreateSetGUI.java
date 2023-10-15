package ui.gui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
// The GUI that allows the user to create a set by clicking buttons and adding a name.

public class CreateSetGUI extends JFrame {
    JFrame menu;
    FullConstellationSet fullSet = new FullConstellationSet();
    ArrayList<String> questions = new ArrayList<>();
    ListOfQuizSet listOfQuizSet;
    String quizName = "";

    //MODIFIES: this
    //EFFECTS: creates a GUI with a given listofquizset object
    public CreateSetGUI(ListOfQuizSet listOfQuizSet) {
        this.listOfQuizSet = listOfQuizSet;
        menu = new JFrame();
    }

    //MODIFIES: this
    //EFFECTS: sets up the main frame and all its components
    public void setup() {
        menu.setSize(600,300);
        menu.setLayout(new BorderLayout());
        menu.getContentPane().setBackground(new Color(7,7,52));
        createEveryButton();
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

    //MODIFIES: this
    //EFFECTS: creates every clickable constellation button and adds the constellation to a list if clicked.
    private void createEveryButton() {
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create Set");
        buttonPanel.setLayout(new GridLayout(0,3,10,10));
        for (String s: getConstellationName()) {
            JButton button = new JButton(s);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    questions.add(s);
                }
            });
            buttonPanel.add(button);
        }
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                popupSet();
            }
        });
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        menu.add(createButton,BorderLayout.SOUTH);
        menu.add(scrollPane, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: creates a pop up frame that is the last step in creating a quiz set, prompts the user for a name and
    // creates the quizset
    private void popupSet() {
        JFrame frame = new JFrame();
        JPanel popup = new JPanel();
        frame.setSize(300,150);
        popup.setLayout(new BoxLayout(popup,BoxLayout.Y_AXIS));
        JButton createButton = createCreateButton();
        popup.add(Box.createVerticalGlue());
        JPanel textBoxPanel = createTextInput();
        JTextField textInput = createTextField();
        textBoxPanel.add(textInput);
        addToPopup(popup,textBoxPanel,createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                quizName = textInput.getText();
                addQuiz(quizName);
                menu.dispose();
                frame.dispose();
                backToMenu(listOfQuizSet);
            }
        });

        frame.add(popup);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds the text field, and button component  to the pop up frame
    private void addToPopup(JPanel popup, JPanel textBoxPanel, JButton createButton) {
        popup.add(textBoxPanel);
        popup.add(createButton);
        popup.add(Box.createVerticalGlue());
    }

    //MODIFIES: this
    //EFFECTS: creates a button that when clicked creates the pop up.
    private JButton createCreateButton() {
        JButton createButton = new JButton("Create Quiz");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return createButton;
    }

    //MODIFIES: this
    //EFFECTS: creates the text label that is next to the text input field.
    private JTextField createTextField() {
        JTextField textInput = new JTextField(1);
        textInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, textInput.getMinimumSize().height));
        return textInput;
    }

    //MODIFIES: this, listOfQuizSet
    //EFFECTS: adds the created quizset to the listofquizset object
    private void addQuiz(String name) {
        QuizSet temp = new QuizSet(name);
        temp.setQuestions(questions);
        listOfQuizSet.addQuizToList(temp);
    }

    //MODIFIES: this
    //EFFECTS: creates a new menu with the listofquizset object and starts the GUI from the main.
    private void backToMenu(ListOfQuizSet listofquizset) {
        ConstellationGameGUI menu = new ConstellationGameGUI(listofquizset);
        menu.start();
    }

    //MODIFIES: this
    //EFFECTS: creates the text field input that asks the user to input the quizset name.
    private JPanel createTextInput() {
        JPanel textBoxPanel = new JPanel();
        textBoxPanel.setLayout(new BoxLayout(textBoxPanel, BoxLayout.X_AXIS));

        JLabel quizText = new JLabel("Enter quiz name");
        quizText.setAlignmentY(Component.CENTER_ALIGNMENT);
        textBoxPanel.add(quizText);


        return textBoxPanel;
    }

    //EFFECTS: returns all the constellation names from the full quiz set.
    private ArrayList<String> getConstellationName() {
        ArrayList<String> result  = new ArrayList<>();
        for (String s: fullSet.getAllNames()) {
            result.add(s);
        }
        return result;
    }

    //EFFECTS: prints the event log for the application
    private void printLog() {
        Iterator<Event> it = EventLog.getInstance().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

}
