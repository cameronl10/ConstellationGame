package ui;

import model.FullConstellationSet;
import model.ListOfQuizSet;
import model.QuizSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Constellation game application, holds the user input scanner and the list of quizzes from the user.
public class ConstellationApp {
    private static final String JSON_STORE = "./data/Constellation.json";
    private static Scanner scanner = new Scanner(System.in);
    private static ListOfQuizSet gameList = new ListOfQuizSet();
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;

    //EFFECTS: starts the game UI, gives the user two options to either start playing the game
    // with the default full set or to create a set. If the user chooses to create a set they
    // can create the set then play the game with it.
    public static void start() throws FileNotFoundException      {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        gameList.setUp();
        scanner = new Scanner(System.in);

        while (true) {
            displayOptions();
            int userInput = scanner.nextInt();
            if (userInput == 0) {
                break;
            }
            handleMenu(userInput);
        }
        System.out.println("End ):");
    }

    //EFFECTS: displays options for users to select
    private static void displayOptions() {
        System.out.println("What would you like to do?:");
        System.out.println("Play game (1), Create a new quiz set (2), Save quizzes (3), Load quizzes(4)");
        System.out.println("Please enter the number input for option, or enter 0 to exit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private static void handleMenu(int option) {
        if (option == 1) {
            System.out.println("You chose to play game!");
            System.out.println("These are your quiz sets:");
            System.out.println(gameList.formatListOfQuizSet());
            System.out.println("Please choose your quiz set number");
            int quizSetInput = scanner.nextInt();
            playGame(quizSetInput);
        } else if (option == 2) {
            askToCreateSet();
        } else if (option == 3) {
            saveQuizSets();
        } else if (option == 4) {
            loadQuizSets();
        }
    }

    //REQUIRES: given index is in range of the size of the listofQuizSets
    //MODIFIES: this
    //EFFECTS: plays the trivia game by looping through each question in the QuizSet and giving feedback if the
    //         answer is correct or wrong. After the full set is complete, it tells the user how many answers they got
    //         correct and shows them a list of all the questions they got wrong.
    private static void playGame(int quizSetIndex) {
        FullConstellationSet info = new FullConstellationSet();
        QuizSet curQuizSet = gameList.getListOfQuizSet().get(quizSetIndex);
        ArrayList<String> wrongAnswers = new ArrayList<>();
        int curQuizSetSize = curQuizSet.getQuestions().size();
        System.out.println(curQuizSet.getName() + ":playing game");
        int correct = 0;

        for (int i = 0; i < curQuizSetSize; i++) {
            int randomIndex = (int) (Math.random() * (curQuizSet.getQuestions().size()));
            String chosenConstellation = curQuizSet.getQuestions().get(randomIndex);
            System.out.println(info.getMatchingDescription(chosenConstellation));
            curQuizSet.getQuestions().remove(chosenConstellation);
            ArrayList<String> answers = generateAnswers(chosenConstellation);
            printAnswers(answers);
            if (handleQuestion(answers,chosenConstellation)) {
                correct++;
            } else {
                wrongAnswers.add(chosenConstellation);
            }
            System.out.println(correct + " correct so far!");
        }
        printEndMessage(correct,curQuizSetSize,wrongAnswers);
    }


    //EFFECTS: takes the total correct number of questions answered, the size of the set and the list of wrong answers
    //         and prints the number of correct answers and the arraylist of answers that were wrong during the quiz.
    private static void printEndMessage(int correct, int curQuizSetSize,ArrayList<String> wrongAnswers) {
        System.out.println("You got " + correct + "/" + curQuizSetSize);
        System.out.println("These are the ones you got wrong" + wrongAnswers);
        if ((correct / curQuizSetSize) == 1) {
            System.out.println("You got 100%, you are REALLY cool! 😎");
        }
    }

    //EFFECTS: takes the list of possible answers and the actual answer and returns true if it is correct and
    //         false if it is wrong and prints the correct answer.
    private static boolean handleQuestion(ArrayList<String> answers, String chosenConstellation) {
        if (handleAnswers(answers, chosenConstellation)) {
            return true;
        } else {
            System.out.println("Correct answer is: " + chosenConstellation);
            return false;
        }
    }


    //REQUIRES: given answer follows either the yes or no convention.
    //EFFECTS: Asks the user if they want to create a quiz set, if they say no, break the function and continue
    //         the program. If they say yes, call the other functions to actually create the set then at the end
    //         calls the question again.
    private static void askToCreateSet() {
        System.out.println("These are your quiz sets:");
        System.out.println(gameList.formatListOfQuizSet());
        System.out.println("Do you want to add a set? (Y or N)");

        char answer = scanner.next().toLowerCase().charAt(0);
        if (answer == 'y') {
            System.out.println("What is the title of the new set?");
            getTitleOfSet();
            createNewSet();
        } else if (answer == 'n') {
            System.out.println("These are your quiz sets:");
            System.out.println(gameList.formatListOfQuizSet());
            System.out.println("Please choose your quiz set number");
            return;
        }
    }

    //REQUIRES: user input is in range from [0,4]
    //EFFECTS: takes a user input index for answer and returns true if the user is correct otherwise returns false
    private static boolean handleAnswers(ArrayList<String> answers, String correctAnswer) {
        System.out.println("Enter your answer (in index of question)");
        int indexAnswer = scanner.nextInt();
        if (answers.get(indexAnswer).equals(correctAnswer)) {

            System.out.println("Correct! :D");
            return true;
        } else {
            System.out.println("Wrong ):");
            return false;
        }
    }

    //EFFECTS: randomly generates 3 wrong answers from the answer bank then adds the correct answer,
    //         then shuffles the order of the answers.
    private static ArrayList<String> generateAnswers(String answer) {
        QuizSet answerBank = new QuizSet("answers");
        answerBank.createFullSet();
        ArrayList<String> answerBankSet = answerBank.getQuestions();
        answerBankSet.remove(answer);
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int randomIndex = (int) (Math.random() * 4);
            answers.add(answerBankSet.get(randomIndex));
            answerBankSet.remove(randomIndex);
        }
        answers.add(answer);
        Collections.shuffle(answers);
        return answers;
    }

    //EFFECTS: prints the 4 answers from the given ArrayList in a format with an index next to it.
    private static void printAnswers(ArrayList<String> answers) {
        for (int i = 0; i < answers.size(); i++) {
            System.out.print(answers.get(i) + "[" + i + "] ");
        }
    }

    //EFFECTS: returns the title of the set from the given user input.
    private static String getTitleOfSet() {
        String title = scanner.nextLine();
        return title;
    }

    //REQUIRES: user inputs the name of a constellation correctly
    //MODIFIES: this.gameList
    //EFFECTS: asks the user to input the constellation names they want to add to their set
    //         when the user signals that they are done, add that set into the list of quiz sets
    private static void createNewSet() {

        String title = getTitleOfSet();
        QuizSet newSet = new QuizSet(title);

        System.out.println("List of all constellations: "
                + "https://starchild.gsfc.nasa.gov/docs/StarChild/questions/88constellations.html\n"
                + "Add names of constellations or type done when finished");
        ArrayList<String> input = new ArrayList<>();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("done")) {
                System.out.println("You added:" + input);
                break;
            } else {
                input.add(userInput);
            }
        }
        newSet.setQuestions(input);
        gameList.addQuizToList(newSet);
    }

    // EFFECTS: saves the quiz sets to file
    private static void saveQuizSets() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameList);
            jsonWriter.close();
            System.out.println("Saved to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads quiz sets from file
    private static void loadQuizSets() {
        try {
            gameList = jsonReader.read();
            System.out.println("Loaded from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

