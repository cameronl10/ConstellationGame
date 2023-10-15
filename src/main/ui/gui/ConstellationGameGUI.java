package ui.gui;

import model.EventLog;
import model.ListOfQuizSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
// Main menu JFrame GUI class for the Constellation game, takes in a ListOfQuizSet data that is passed between every GUI

public class ConstellationGameGUI extends JPanel {
    JFrame menu;
    JLabel label;
    ListOfQuizSet listOfQuizSet;

    //EFFECTS: Creates the main menu GUI with no given listofquizset object.
    public ConstellationGameGUI() {
        listOfQuizSet = new ListOfQuizSet();
        listOfQuizSet.setUp();
        setupMenu();
        setLabel();
        addButton();

    }

    //EFFECTS: Creates the main menu GUI with a given listofquizset object
    public ConstellationGameGUI(ListOfQuizSet listofquizset) {
        this.listOfQuizSet = listofquizset;
        setupMenu();
        setLabel();
        addButton();

    }

    //MODIFIES: this
    //EFFECTS: Starts the game by setting the JFrame to be visible.
    public void start() {
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
    //EFFECTS: sets the main frame's size, layout and colour.
    private void setupMenu() {
        menu = new JFrame();
        menu.setSize(600,300);
        menu.setLayout(new BorderLayout());
        menu.getContentPane().setBackground(new Color(7, 7, 52));

    }

    //EFFECTS: prints the event log for the application
    private void printLog() {
        Iterator<model.Event> it = EventLog.getInstance().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    //MODIFIES: this
    //EFFECTS: sets the main text seen on the frame
    private void setLabel() {
        label = new JLabel("Constellation Game");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.white);
        menu.add(label, BorderLayout.CENTER);

    }

    //MODIFIES: this
    //EFFECTS: adds two buttons on the bottom that lead to a play game menu and a manager menu
    private void addButton() {
        JButton button1 = new JButton("Play game");
        JButton button2 = new JButton("Quiz Set Manager");
        JPanel buttonLayout = new JPanel();
        buttonLayout.setLayout(new GridLayout(1,2));
        buttonLayout.add(button1);
        buttonLayout.add(button2);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.dispose();
                showSelectSetsGUI();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.dispose();
                showSetManagerGUI();
            }
        });
        menu.add(buttonLayout,BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: creates a new SetManagerGUI and sets it up.
    private void showSetManagerGUI() {
        SetManagerGUI setGUI = new SetManagerGUI(listOfQuizSet);
        setGUI.setup();
    }

    //MODIFIES: this
    //EFFECTS: creates a new SelectSetsGUI and sets it up.
    private void showSelectSetsGUI() {
        SelectSetsGUI setsGUI = new SelectSetsGUI(listOfQuizSet);
        setsGUI.setup();
    }


}
