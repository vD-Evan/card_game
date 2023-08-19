package syst17796.games.libraries.highcardlowcard;

import java.util.ArrayList;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Deck;
import syst17796.object_classes.standard.Player;
import syst17796.utility.UserInput;

public class Utils { // utility methods for High Card Low Card game

    // deal two cards, one face up, one face down
    public static Card[] dealTwoCards(Deck theDeck) {
        if (theDeck.getSize() == 0) {
            System.out.println("The deck has been exhausted. Reshuffling.");
            theDeck.buildDeck(); // Rebuild the deck by calling the buildDeck() method
            theDeck.shuffle(); // Shuffle the existing deck
        }

        // deal cards, output the face up card
        Card faceUp = theDeck.dealCard();
        Card faceDown = theDeck.dealCard();
        System.out.println("Revealed Card is: " + faceUp + ".");
        return new Card[] { faceUp, faceDown };
    }

    // allows player to make a wager
    public static int playerWager(int score) {
        UserInput get = new UserInput(); // for user input
        int wager = 0;

        if (score > 1) { // allows a user to bet specific points instead of "double or nothing"
            System.out.printf("%n%s%n%s",
                    "How many points would you like to wager?", "> ");
            wager = get.anInt();

            // ensure wager entry is within range of 1 and current score
            while (wager < 1 || wager > score) {
                System.out.printf("%s %s %d.%n> ",
                        "Error: Invalid Entry.",
                        "Please enter a number between 1 and",
                        score);
                wager = get.anInt();
            }
        } else if (score == 1) { // if score is 1, assumes a wager of 1
            System.out.println("Your current score is 1; a wager of 1 will be assumed.");
            wager = 1;
        } // if score = 0, method isn't called
        return wager;
    }

    // player guess
    public static boolean highLowGuess(Card faceUp) {
        UserInput get = new UserInput();
        System.out.printf("%s%n> ", // outputs the current cards and asks user to make their guess
                "Do you believe that " + faceUp + " is [Higher] or [Lower] than the face down card?");

        return get.aBoolean("Higher", "Lower"); // returns their guess
    }

    // compares the card face up with card face down, determines if guess is correct
    public static boolean compareTwoCards(Card faceUp, Card faceDown, boolean guess) {
        if (guess) { // if guess is higher
            if (faceUp.getRankAsInt() > faceDown.getRankAsInt()) {
                return true; // and face up is higher
            } else {
                return false; // or face up is lower
            }
        } else if (faceUp.getRankAsInt() < faceDown.getRankAsInt()) { // otherwise guess is lower
            return true; // and face up is also lower
        }
        return false; // return false (guess is lower, card is higher)
    }

    // sorts players for endgame
    public static ArrayList<Player> sortByScore(ArrayList<Player> players) {
        int n = players.size();
        boolean swapped;

        // uses simple bubble sort
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (players.get(j).getScore() < players.get(j + 1).getScore()) {
                    // Swap players[j] and players[j+1]
                    Player temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                    swapped = true;
                }
            }

            // If no two elements were swapped in the inner loop, the array is already
            // sorted
            if (!swapped) {
                break;
            }
        }

        return players; // returns the sorted list
    }
}
