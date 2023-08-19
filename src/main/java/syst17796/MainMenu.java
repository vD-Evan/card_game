package syst17796;

import java.util.ArrayList;

import syst17796.games.Blackjack;
import syst17796.games.HighCardLowCard;
import syst17796.utility.UserInput;

public class MainMenu {
    private ArrayList<String> gameList;
    private int numberOfGames;
    private UserInput get = new UserInput();

    public MainMenu() {
        gameList = new ArrayList<>();
        gameList.add("[-1] to Exit");
        gameList.add("High Card Low Card");
        gameList.add("Black Jack");
        // gameList.add("Go Fish");
        // gameList.add("Uno");
        numberOfGames = gameList.size() - 1;
    }

    public void runMainMenu() {

        int userSelection;

        do {
            get.aClearScreen();
            displayGameMenu();
            userSelection = get.anInt();
            if (userSelection > numberOfGames || userSelection < -1 || userSelection == 0) {
                System.out.printf("%nError: Invalid Selection. ");
                System.out.println("To select a game, please enter an integer between 1 and " + numberOfGames + ".");
                System.out.println("To exit, please enter [-1]");
                get.aWait();
            } else if (userSelection != -1) {
                get.aClearScreen();
                gameSwitch(userSelection);
            }
        } while (userSelection != -1);
    }

    private void displayGameMenu() {
        System.out.println("Games Menu:");
        for (int i = 1; i < gameList.size(); i++) {
            System.out.printf("[%2d] %s%n",
                    i, gameList.get(i));
        }
        System.out.println(gameList.get(0));
        System.out.printf("%nPlease make a selection:%n> ");
    }

    private void gameSwitch(int selection) {
        System.out.println("You have selected: " + gameList.get(selection) + ".");
        ArrayList<String> playerNames = new ArrayList<>();
        int playerCount = 1;
        String enteredName;

        System.out.println("Please enter the names of the players (leave blank when all players are added):");
        do {
            System.out.print("Player " + playerCount + ": > ");
            enteredName = get.aString();
            if (enteredName.isEmpty() && playerCount <= 2) {
                System.out.println("Error: Must enter at least 2 players.");
            } else if (!enteredName.isEmpty()) {
                playerNames.add(enteredName);
                playerCount++;
            }
        } while (playerCount <= 2 || !enteredName.isEmpty());

        switch (selection) {
            case 1:
                HighCardLowCard game1 = new HighCardLowCard();
                game1.newGame(playerNames);
                break;
            case 2:
                Blackjack game2 = new Blackjack();
                game2.newGame(playerNames);
        }
    }
}
