package ui;
//Main class for constellation app

import ui.gui.ConstellationGameGUI;

public class Main {
    //EFFECTS: initialize and start the game
    public static void main(String[] args) {
        ConstellationGameGUI game = new ConstellationGameGUI();
        game.start();
    }
}
