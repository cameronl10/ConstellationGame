package ui.gui;

import model.Event;
import model.EventLog;
import model.ListOfQuizSet;
import model.QuizSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
//Creates the GUI where the user can select which quizset they want to play.

public class SelectSetsGUI extends JFrame {
    JFrame quizSetMenu;
    ListOfQuizSet listOfQuizSet;

    //EFFECTS: creates the GUI with a listofquizset object
    public SelectSetsGUI(ListOfQuizSet listOfQuizSet) {
        this.listOfQuizSet = listOfQuizSet;
        quizSetMenu = new JFrame();

    }

    //MODIFIES: this
    //EFFECTS: sets up the frame with a size and its components
    public void setup() {
        quizSetMenu.setSize(600, 300);
        createButtonList();

        quizSetMenu.getContentPane().setBackground(new Color(7,7,52));
        quizSetMenu.setVisible(true);
        quizSetMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        quizSetMenu.addWindowListener(new java.awt.event.WindowAdapter() {
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
    //EFFECTS: creates the list of buttons that are every quizset that the user creates.
    private void createButtonList() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        buttonPanel.setBackground(new Color(7,7,52));
        quizSetMenu.add(scrollPane, BorderLayout.CENTER);

        for (QuizSet qs: listOfQuizSet.getListOfQuizSet()) {
            JButton button = new JButton(qs.getName());
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    quizSetMenu.dispose();
                    showPlayGameGUI(qs);
                }
            });
            buttonPanel.add(button);
        }


    }


    //MODIFIES: this
    //EFFECTS: creates a new playgamegui and shows it to the user.
    private void showPlayGameGUI(QuizSet qs) {
        PlayGameGUI gameGUI = new PlayGameGUI(qs,listOfQuizSet);
        gameGUI.setup();
    }

}

