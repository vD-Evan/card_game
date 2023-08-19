package syst17796.games.libraries.blackjack;

import java.util.ArrayList;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Player;

public class Sort { // handles the end game sorting using simple bubble sorts (rather than built-in
                    // collection methods)
    public static void byHighCard(ArrayList<Player> players) { // sorts based on highest card in hand
        int comparedPlayers = players.size() - 1;

        // Outer loop for iterations
        for (int i = 0; i < comparedPlayers; i++) {
            // Inner loop for comparisons and swapping
            for (int j = 0; j < comparedPlayers - i; j++) {
                // Get the two adjacent players
                Player player_1 = players.get(j);
                Player player_2 = players.get(j + 1);

                // Get their respective hands
                ArrayList<Card> hand_1 = new ArrayList<>(player_1.getHand());
                ArrayList<Card> hand_2 = new ArrayList<>(player_2.getHand());

                // Sort hand_1 and hand_2 in descending order based on card value
                sortHand(hand_1);
                sortHand(hand_2);

                // Compare the sorted hands card by card
                int maxSize = Math.max(hand_1.size(), hand_2.size());
                int winner = 0; // 0 indicates no winner yet, 1 for hand_1, 2 for hand_2

                for (int x = 0; x < maxSize; x++) {
                    if (x >= hand_1.size()) {
                        winner = 2;
                        break; // hand_1 is smaller, so hand_2 wins
                    } else if (x >= hand_2.size()) {
                        winner = 1;
                        break; // hand_2 is smaller, so hand_1 wins
                    }

                    int rank_1 = hand_1.get(x).getRankAsInt();
                    int rank_2 = hand_2.get(x).getRankAsInt();

                    if (rank_1 < rank_2) {
                        winner = 2; // hand_2 wins
                        break;
                    } else if (rank_1 > rank_2) {
                        winner = 1; // hand_1 wins
                        break;
                    }
                }

                // Swap players within the players ArrayList based on the comparison result
                if (winner == 1) {
                    players.set(j, player_2);
                    players.set(j + 1, player_1);
                }
            }
        }
    }

    public static void byHandSize(ArrayList<Player> players) { // sorts by hand size
        int comparedPlayers = players.size() - 1;

        // Outer loop for iterations
        for (int i = 0; i < comparedPlayers; i++) {
            // Inner loop for comparisons and swapping
            for (int j = 0; j < comparedPlayers - i; j++) {
                // Get the two adjacent players
                Player player_1 = players.get(j);
                Player player_2 = players.get(j + 1);

                int handSize_1 = player_1.getHandSize();
                int handSize_2 = player_2.getHandSize();

                // Compare hand sizes and swap players if necessary
                if (handSize_1 > handSize_2) {
                    swapPlayer(players, i, i);
                }
            }
        }
    }

    public static void byHandValue(ArrayList<Player> players) { // sorts by hand value
        int comparedPlayers = players.size() - 1;

        // Outer loop for iterations
        for (int i = 0; i < comparedPlayers; i++) {
            // Inner loop for comparisons and swapping
            for (int j = 0; j < comparedPlayers - i; j++) {
                // Get the two adjacent players
                Player player_1 = players.get(j);
                Player player_2 = players.get(j + 1);

                int handValue_1 = Scores.handValue(player_1, true);
                int handValue_2 = Scores.handValue(player_2, true);

                // Compare hand values and swap players if necessary
                if (handValue_1 < handValue_2) {
                    swapPlayer(players, j, j + 1);
                }
            }
        }
    }

    private static void sortHand(ArrayList<Card> hand) { // for use in sort by highcard
        int size = hand.size();
        for (int k = 0; k < size - 1; k++) {
            for (int m = 0; m < size - k - 1; m++) {
                if (hand.get(m).getRankAsInt() < hand.get(m + 1).getRankAsInt()) {
                    swapCard(hand, m, m + 1);
                }
            }
        }
    }

    private static void swapCard(ArrayList<Card> hand, int index1, int index2) { // swaps the cards compared
        Card tempCard = hand.get(index2);
        hand.set(index2, hand.get(index1));
        hand.set(index1, tempCard);
    }

    private static void swapPlayer(ArrayList<Player> players, int index1, int index2) { // swaps the players compared
        Player tempPlayer = players.get(index2);
        players.set(index2, players.get(index1));
        players.set(index1, tempPlayer);
    }
}
