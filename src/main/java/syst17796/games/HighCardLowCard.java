package syst17796.games;

import java.util.ArrayList;

import syst17796.abstract_classes.GameRequirements; // requires the requirements for a game
import syst17796.games.libraries.highcardlowcard.Utils; // game library of extra methods
import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Deck;
import syst17796.object_classes.standard.Player;
import syst17796.utility.UserInput;

public class HighCardLowCard extends GameRequirements<Player, Card, Deck> { // uses standard player, card, deck
    protected Deck theDeck;
    protected ArrayList<Player> players;
    protected UserInput get = new UserInput();

    @Override
    public void newGame(ArrayList<String> playerNames) {
        get.aClearScreen();

        // imports player names and creates a player for each name
        players = new ArrayList<>();
        for (String name : playerNames) {
            Player player = new Player(name);
            players.add(player);
            System.out.println("Added " + player.getName() + " to the game.");
        }
        System.out.println("");
        setupGame(); // sets the game up,
        runGame(); // runs the game
    }

    @Override
    protected void setupGame() { // sets up the (new) game

        // instructions with an option to view
        System.out.println("Would you like to view the instructions? [Yes]/[No]");
        boolean choice = get.aBoolean("Yes", "No");
        if (choice) {
            instructions();
        }

        // builds a (new) deck
        if (theDeck != null) {
            get.aClearScreen();
            System.out.println("Resetting the deck for a new game.");

        } else {
            System.out.println("Creating a new deck to use.");
        }
        theDeck = new Deck();

        // shuffles the deck
        System.out.printf("Shuffling the deck.%n%n");
        theDeck.shuffle();

        // (re)sets the player score to 2
        for (Player player : players) {
            player.setScore(2);
            System.out.println(player.getName() + ": " + player.getScore() + " points.");
        }
        get.aWait(); // holds the output to allow players to view the messages
    }

    // instructions for the game
    @Override
    protected void instructions() {
        get.aClearScreen();
        System.out.printf("%s%n%n%s%n%s%s%n",
                "Welcome to High Card Low Card!",
                "Goal:",
                "The goal of the game is to guess whether the face-up card is ",
                "higher or lower than the face-down card to earn points.");
        get.aWait(); // waits with above output to allow for player to read
                     // without presenting too much information at once

        System.out.printf("%s%n%s%n%s%n%s%s%n%s%n%s%n%s%n%s%n",
                "Instructions:",
                "1. Each player starts with 2 points.",
                "2. The dealer reveals one card face-up and one face-down.",
                "3. The player must guess whether the face-up card is higher or lower than ",
                "the face-down card.",
                "4. Players can wager any number of points they have (between 1 and their total points).",
                "5. If the player guesses correctly, they earn points equal to their wager.",
                "6. If the player guesses incorrectly, they lose points equal to their wager.",
                "7. The game continues until all players decide to stop or runs out of points to bet.");
        get.aWait();

        System.out.printf("%s%n%s%n%s%n",
                "Card Ranks:",
                "The ranks of the cards are in ascending order from Ace to King (A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K).",
                "Ace is considered the lowest rank in this game.");
        get.aWait();

        System.out.printf("%s%n%s%n%s%n%n%s%n",
                "Scoring:",
                "Players will be ranked based on their total points in descending order.",
                "The player with the highest score wins!",
                "Good Luck and Have Fun!");
        get.aWait();
        get.aClearScreen(); // clears instructions from screen to allow for the game
    }

    // flow of the game
    @Override
    protected void runGame() {
        boolean playAgain = true;
        while (playAgain) { // loops while players want to play
            for (Player player : players) { // gives each player a turn, then ends the game
                playerTurn(player);
            }
            endGame(); // runs end game logic

            // allows players the chance to play again
            System.out.println("Do you want to play again? [Yes]/[No]");
            System.out.print("> ");
            playAgain = get.aBoolean("Yes", "No");
            if (playAgain) { // if players want to play again, resets the game then loops
                setupGame();
            }
        }
    }

    // flow of the player's turn
    @Override
    protected void playerTurn(Player currentPlayer) {
        boolean continueTurn = true;
        while (continueTurn) {
            get.aClearScreen(); // clears screen to reduce clutter

            // displays current player and current player's score
            System.out.print(currentPlayer.getName() + "'s turn. || ");
            System.out.printf("Current Point Score: %d%n%n", currentPlayer.getScore());

            System.out.println("Dealing two cards...");
            // Deal cards and update this.theDeck
            Card faceDown;
            Card faceUp;
            do { // deals two cards, loops while the cards are of equal rank
                Card[] dealtCards = Utils.dealTwoCards(this.theDeck);
                faceUp = dealtCards[0];
                faceDown = dealtCards[1];
                if (faceUp.getRankAsInt() == faceDown.getRankAsInt()) { // allows players to see what cards were dealt
                                                                        // in case of equal rank
                    System.out.println("Notice: previous deal was two identical cards.");
                    System.out.println("   Face Up Card was: " + faceUp);
                    System.out.println("   Face Down Card was: " + faceDown);
                    System.out.println("Dealing two new cards...");
                }
            } while (faceUp.getRankAsInt() == faceDown.getRankAsInt());

            // gets the user's bet, gets the users choice (higher or lower)
            int playerBet = Utils.playerWager(currentPlayer.getScore());
            boolean highLow = Utils.highLowGuess(faceUp);

            // compares the results, either adds or subtracts bet from score, informs player
            // what hidden card was
            boolean comparedResults = Utils.compareTwoCards(faceUp, faceDown, highLow);
            if (comparedResults) {
                currentPlayer.setScore(currentPlayer.getScore() + playerBet);
                System.out.println("Congratulations! You guessed correctly.");
            } else {
                currentPlayer.setScore(currentPlayer.getScore() - playerBet);
                System.out.println("You guessed incorrectly.");
            }
            System.out.printf("The face down card was " + faceDown + ".%n");

            // ends turn if user has no points to bet with
            if (currentPlayer.getScore() == 0) {
                System.out.printf("%nYou have no more points left to bet. ");
                continueTurn = false;

            } else { // allows user option to bet again
                System.out.printf("%n%s%n> ",
                        "Would you like to make another bet?");
                continueTurn = get.aBoolean("Yes", "No");
            }
        }
        // display final score and ends the turn
        System.out.println("You have ended your turn with a score of " + currentPlayer.getScore() + ".");
        get.aWait();
    }

    // end game logic
    @Override
    protected void endGame() {
        players = Utils.sortByScore(players); // sorts players by score

        System.out.println("Results:"); // displays results (name, amount of points)
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore() + " points.");
        }
    }

}
