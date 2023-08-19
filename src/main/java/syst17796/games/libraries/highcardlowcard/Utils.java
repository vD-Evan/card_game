package syst17796.games.libraries.highcardlowcard;

import java.util.ArrayList;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Deck;
import syst17796.object_classes.standard.Player;
import syst17796.utility.UserInput;

public class Utils {
    public static Card[] dealTwoCards(Deck theDeck) {
        if (theDeck.getSize() == 0) {
            System.out.println("The deck has been exhausted. Reshuffling.");
            theDeck.buildDeck(); // Rebuild the deck by calling the buildDeck() method
            theDeck.shuffle(); // Shuffle the existing deck
        }

        Card faceUp = theDeck.dealCard();
        Card faceDown = theDeck.dealCard();
        System.out.println("Revealed Card is: " + faceUp + ".");
        return new Card[] { faceUp, faceDown };
    }

    public static int playerWager(int score) {
        UserInput get = new UserInput();
        int wager = 0;

        if (score > 1) {
            System.out.printf("%n%s%n%s",
                    "How many points would you like to wager?", "> ");
            wager = get.anInt();

            while (wager < 1 || wager > score) {
                System.out.printf("%s %s %d.%n> ",
                        "Error: Invalid Entry.",
                        "Please enter a number between 1 and",
                        score);
                wager = get.anInt();
            }
        } else if (score == 1) {
            System.out.println("Your current score is 1; a wager of 1 will be assumed.");
            wager = 1;
        }
        return wager;
    }

    public static boolean highLowGuess(Card faceUp) {
        UserInput get = new UserInput();
        System.out.printf("%s %s%n> ",
                "Do you believe that " + faceUp + " is [Higher] or [Lower] than the face down card?",
                "[Higher]/[Lower]");

        return get.aBoolean("Higher", "Lower");
    }

    public static boolean compareTwoCards(Card faceUp, Card faceDown, boolean guess) {
        if (guess) {
            if (faceUp.getRankAsInt() > faceDown.getRankAsInt()) {
                return true;
            } else {
                return false;
            }
        } else if (faceUp.getRankAsInt() < faceDown.getRankAsInt()) {
            return true;
        }
        return false;
    }

    public static ArrayList<Player> sortByScore(ArrayList<Player> players) {
        int n = players.size();
        boolean swapped;

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

        return players;
    }
}
