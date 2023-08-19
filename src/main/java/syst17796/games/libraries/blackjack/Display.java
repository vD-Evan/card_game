package syst17796.games.libraries.blackjack;

import java.util.ArrayList;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Player;

public class Display {
    public static void table(Player currentPlayer, ArrayList<Player> players) {
        otherPlayers(currentPlayer, players);
        divider(currentPlayer);
        ownTurn(currentPlayer);
    }

    public static void otherPlayers(Player currentPlayer, ArrayList<Player> players) {
        for (Player player : players) {
            if (player == currentPlayer) {
                continue;
            }

            int score = Scores.handValue(player, false);
            System.out.print(player.getMiscString1() + " " + player.getName() + ": " + score);

            System.out.print("  ||  Cards: [Hidden]");
            for (int i = 1; i < player.getHandSize(); i++) {
                Card card = player.getHand().get(i);
                System.out.print(", " + card);
            }

            System.out.println("  ||  Hand Size: " + player.getHandSize());
        }
    }

    public static void divider(Player currentPlayer) {
        System.out.printf("%n%s%n%s%s%n%s%n%n",
                "--------------------",
                currentPlayer.getName(), "'s turn.",
                "--------------------");
    }

    public static void ownTurn(Player currentPlayer) {
        System.out.println("Cards in Hand:");
        for (int i = 0; i < currentPlayer.getHandSize(); i++) {
            Card card = currentPlayer.getHand().get(i);
            if (i == 0) {
                System.out.print("Hidden: ");
            }
            System.out.print(card);
            if (i != currentPlayer.getHandSize() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("");
        System.out.println("Number of Cards in Hand: " + currentPlayer.getHandSize());
        System.out.println("Current Hand Value: " + Scores.handValue(currentPlayer, true));

        System.out.println("");
        System.out.println("Available Actions:");
        System.out.println("[1]: Hit    [2] Stand");
        System.out.print("> ");
    }

    public static void nextActive(Player nextPlayer) {
        System.out.println("Please pass the device to: " + nextPlayer.getName());
    }

    public static String playerStats(Player player) {
        String stats = player.getName();
        stats += " with a score of: " + Scores.handValue(player, true) + " (";

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

        return stats;
    }
}
