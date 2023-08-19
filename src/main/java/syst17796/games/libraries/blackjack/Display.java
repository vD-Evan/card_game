package syst17796.games.libraries.blackjack;

import java.util.ArrayList;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Player;

public class Display { // handles almost all output
    public static void table(Player currentPlayer, ArrayList<Player> players) { // displays the table
        otherPlayers(currentPlayer, players); // starting with other players
        divider(currentPlayer); // then a divider
        ownTurn(currentPlayer); // then the current player's turn
    }

    // display other player's
    public static void otherPlayers(Player currentPlayer, ArrayList<Player> players) {
        for (Player player : players) { // cycles through players
            if (player == currentPlayer) { // skips current player
                continue;
            }

            // determines the player's score (no including the first card in the player's
            // hand) and outputs it with the name
            int score = Scores.handValue(player, false);
            System.out.print(player.getMiscString1() + " " + player.getName() + ": " + score);

            // displays a hidden card then the rest of the player's hand
            System.out.print("  ||  Cards: [Hidden]");
            for (int i = 1; i < player.getHandSize(); i++) {
                Card card = player.getHand().get(i);
                System.out.print(", " + card);
            }

            // outputs the size of a player's hand
            System.out.println("  ||  Hand Size: " + player.getHandSize());
        }
    }

    // outputs a divider between other players and current player
    public static void divider(Player currentPlayer) {
        System.out.printf("%n%s%n%s%s%n%s%n%n",
                "--------------------",
                currentPlayer.getName(), "'s turn.",
                "--------------------");
    }

    // outputs current player's turn
    public static void ownTurn(Player currentPlayer) {
        // outputs the cards in the player's hand
        System.out.println("Cards in Hand:");
        for (int i = 0; i < currentPlayer.getHandSize(); i++) {
            Card card = currentPlayer.getHand().get(i);
            if (i == 0) {
                System.out.print("Hidden: "); // marks the first card as hidden to note that other players cannot see it
            }
            System.out.print(card);
            if (i != currentPlayer.getHandSize() - 1) { // adds a comma between cards if warranted
                System.out.print(", ");
            }
        }
        System.out.println("");

        // displays a quick summary of player score (hand size, hand value)
        System.out.println("Number of Cards in Hand: " + currentPlayer.getHandSize());
        System.out.println("Current Hand Value: " + Scores.handValue(currentPlayer, true));

        System.out.println("");

        // presents user with options to take for their turn
        System.out.println("Available Actions:");
        System.out.println("[1]: Hit    [2] Stand");
        System.out.print("> ");
    }

    // informs the user of the next active player
    public static void nextActive(Player nextPlayer) {
        System.out.println("Please pass the device to: " + nextPlayer.getName());
    }

    // builds player's stats (name, cards, hand size, etc.) for display purposes
    public static String playerStats(Player player) {
        // starts with name and hand value (score)
        String stats = player.getName();
        stats += " with a score of: " + Scores.handValue(player, true) + " (";

        // adds cards in hand, adds in hand size
        ArrayList<Card> hand = player.getHand();
        for (int i = 0; i < player.getHandSize(); i++) {
            Card card = hand.get(i);
            stats += card;
            if (i != player.getHandSize() - 1) {
                stats += ", ";
            } else {
                stats += " [" + player.getHandSize() + " cards in hand])";
            }
        }

        return stats; // returns the stats
    }
}
