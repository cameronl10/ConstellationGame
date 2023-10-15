package ui.gui;

import model.Event;
import model.EventLog;
import model.ListOfQuizSet;
import model.QuizSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
//Creates the play game GUI where the user plays the game with their selected quiz set.

public class PlayGameGUI extends JFrame {
    JFrame gameMenu;
    QuizSet curSet;
    ListOfQuizSet listOfQuizSet;
    JPanel imagePanel;
    JLabel currentQuestion;
    String currentAnswer;

    //EFFECTS: creates a play game gui with a listofquizset and a selected quiz set from the user
    public PlayGameGUI(QuizSet qs, ListOfQuizSet listOfQuizSet) {
        this.listOfQuizSet = listOfQuizSet;
        this.curSet = qs;
        currentAnswer = qs.getQuestions().get(0);
        ImageIcon firstQuestion = new ImageIcon(getFirstQuestion());
        Image image = firstQuestion.getImage();
        Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
        firstQuestion = new ImageIcon(newimg);
        currentQuestion = new JLabel(firstQuestion);
        imagePanel = new JPanel();
        gameMenu = new JFrame();

    }

    //MODIFIES: this
    //EFFECTS: sets up the frames size and colors and adds components
    public void setup() {
        gameMenu.setSize(600, 300);
        gameMenu.setLayout(new BorderLayout());
        gameMenu.setTitle(curSet.getName());
        setupImagePanel();
        createAnswerButtons();
        gameMenu.setVisible(true);
        gameMenu.getContentPane().setBackground(new Color(7,7,52));
        gameMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gameMenu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: creates the panel that the image of the constellation will be put.
    private void setupImagePanel() {
        imagePanel.setSize(300,150);
        imagePanel.add(currentQuestion);
        imagePanel.setBackground(new Color(7,7,52));
        gameMenu.getContentPane().add(imagePanel, BorderLayout.CENTER);

    }

    //MODIFIES: this
    //EFFECTS: gets a random question from the quiz set and returns the file path formatted correctly.
    private String getQuestionText() {
        if (curSet.getQuestions().size() == 0) {
            displayEnd();
        } else {
            int randomIndex = (int) (Math.random() * (curSet.getQuestions().size()));
            String chosenConstellation = curSet.getQuestions().get(randomIndex);
            curSet.getQuestions().remove(chosenConstellation);
            currentAnswer = chosenConstellation;
            return "data/images/Constellations/" + chosenConstellation + ".jpg";
        }
        return "";
    }

    //EFFECTS: shows a message at the end of the quiz set to show that the game is done.
    private void displayEnd() {
        JFrame endFrame = new JFrame();
        JLabel endText = new JLabel("End of game");
        endFrame.add(endText);
        endFrame.setVisible(true);
        endFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        endFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: creates the 4 answer buttons at the bottom
    private void createAnswerButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        generateRandomButtons(buttonPanel);
        gameMenu.add(buttonPanel,BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: gives the first question of the set.
    private String getFirstQuestion() {
        String result = "data/images/Constellations/" + curSet.getQuestions().get(0) + ".jpg";
        curSet.getQuestions().remove(0);
        return result;
    }

    //MODFIES: this
    //EFFECTS: creates 4 random buttons that are randomized in order everytime it is clicked with proper game functions.
    private void generateRandomButtons(JPanel buttonPanel) {
        buttonPanel.removeAll();
        ArrayList<JButton> buttons = createButtons();
        Collections.shuffle(buttons);
        for (JButton button: buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    handleAnswer(button.getText());
                    changeLabelImage();
                    buttonPanel.setVisible(false);
                    generateRandomButtons(buttonPanel);
                    buttonPanel.setVisible(true);
                }
            });
            buttonPanel.add(button);
        }


    }

    //EFFECTS: shows a popup when a button is clicked, shows correct if answered correctly, wrong otherwise.
    private void handleAnswer(String input) {
        if (input.equals(currentAnswer)) {
            showPopup("correct");
        } else {
            showPopup("wrong");
        }
    }

    //EFFECTS: creates a popup window that will show a correct/wrong message.
    private void showPopup(String message) {
        JFrame popup = new JFrame();
        JLabel text = new JLabel(message);
        popup.add(text);
        popup.setVisible(true);
    }

    //EFFECTS: creates the 4 buttons and adds them to an arraylist.
    private ArrayList<JButton> createButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton answerButton  = new JButton(currentAnswer);
        ArrayList<String> answerBank = getRandomAnswers();
        for (int i = 0; i < 3; i++) {
            JButton button = new JButton(answerBank.get(i));
            buttons.add(button);
        }
        System.out.println(answerBank);
        buttons.add(answerButton);

        return buttons;
    }

    //EFFECTS: gets 3 random answers from the full quiz set and returns it as an arraylist
    private ArrayList<String> getRandomAnswers() {
        ArrayList<String> randomAnswers = new ArrayList<>();
        ArrayList<String> answerBank = listOfQuizSet.getListOfQuizSet().get(0).getQuestions();
        answerBank.remove(currentAnswer);
        for (int i = 0; i < 3;i++) {
            String randomAnswer = answerBank.get((int) (Math.random() * answerBank.size()));
            if (randomAnswers.contains(randomAnswer)) {
                randomAnswers.add(answerBank.get((int) (Math.random() * answerBank.size() - 1)));
            } else {
                randomAnswers.add(randomAnswer);
            }
        }
        return randomAnswers;
    }

    //MODIFIES: this
    //EFFECTS: changes the image to the next questions constellation.
    private void changeLabelImage() {
        ImageIcon picture = new ImageIcon(getQuestionText());
        Image image = picture.getImage();
        Image newimg = image.getScaledInstance(500, 300,  java.awt.Image.SCALE_SMOOTH);
        picture = new ImageIcon(newimg);
        currentQuestion.setIcon(picture);
    }

    //EFFECTS: prints the event log for the application
    private void printLog() {
        Iterator<Event> it = EventLog.getInstance().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

}
