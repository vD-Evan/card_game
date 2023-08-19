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
        // list of games
        gameList = new ArrayList<>();
        gameList.add("[-1] to Exit");
        gameList.add("High Card Low Card");
        gameList.add("Black Jack");

        // future planned games
        // gameList.add("Go Fish");
        // gameList.add("Uno");
        numberOfGames = gameList.size() - 1; // size is one smaller as exit note is stored here
    }

    public void runMainMenu() { // handles the flow of the menu

        int userSelection;

        do {
            get.aClearScreen();
            displayGameMenu();
            userSelection = get.anInt();
            if (userSelection > numberOfGames || userSelection < -1 || userSelection == 0) { // runs on invalid input
                System.out.printf("%nError: Invalid Selection. "); // reprompts for input
                System.out.println("To select a game, please enter an integer between 1 and " + numberOfGames + ".");
                System.out.println("To exit, please enter [-1]");
                get.aWait();
            } else if (userSelection != -1) { // runs on valid entry
                get.aClearScreen();
                gameSwitch(userSelection); // calls the method with the appropriate game
            }
        } while (userSelection != -1); // repeat until exit code received
    }

    private void displayGameMenu() { // displays the main menu
        System.out.println("Games Menu:");
        for (int i = 1; i < gameList.size(); i++) { // cycle through list, displays games
            System.out.printf("[%2d] %s%n", // [%2d] displays formatted selection options
                    i, gameList.get(i));
        }
        System.out.println(gameList.get(0));
        System.out.printf("%nPlease make a selection:%n> ");
    }

    private void gameSwitch(int selection) { // takes the game choice, runs appropriate game
        System.out.println("You have selected: " + gameList.get(selection) + ".");
        ArrayList<String> playerNames = new ArrayList<>();
        int playerCount = 1;
        String enteredName;

        System.out.println("Please enter the names of the players (leave blank when all players are added):");
        do { // asks for player names; handled here instead of within games as logic remains
             // the same
            System.out.print("Player " + playerCount + ": > "); // future modification needed if games have fixed player
                                                                // sizes
            enteredName = get.aString(); // ie. Euchre with either 3 or 4 players
            if (enteredName.isEmpty() && playerCount <= 2) {
                System.out.println("Error: Must enter at least 2 players.");
            } else if (!enteredName.isEmpty()) {
                playerNames.add(enteredName);
                playerCount++;
            }
        } while (playerCount <= 2 || !enteredName.isEmpty()); // loops until no player entered, minimum limit of 2

        switch (selection) { // runs appropriate games
            case 1:
                HighCardLowCard game1 = new HighCardLowCard();
                game1.newGame(playerNames);
                break;
            case 2:
                Blackjack game2 = new Blackjack();
                game2.newGame(playerNames);
        }
        /*
         * Notes:
         * - As previously mentioned, this method does note handle games with fixed
         * sizes
         * - This method does not compare against previously entered names;
         * duplicate names are possible and may cause confusion for players
         * - This method allows for entries of " " or "   " as valid player names;
         * should refactor to remove leading spaces,
         * removing trailing spaces is another potential change
         * Would then output a message to the user stating that names with only spaces
         * are invalid
         */
    }
}
