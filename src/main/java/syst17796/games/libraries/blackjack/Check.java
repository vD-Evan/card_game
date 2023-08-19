package syst17796.games.libraries.blackjack;

import java.util.ArrayList;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Player;

public class Check {

    public static Player initialWin(ArrayList<Player> players) {
        for (Player player : players) {
            boolean hasAce = false;
            boolean hasFace = false;
            ArrayList<Card> hand = player.getHand();

            for (Card card : hand) {
                if (card.getRankAsInt() == 1) {
                    hasAce = true;
                } else if (card.getRankAsInt() > 10) {
                    hasFace = true;
                }
            }
            if (hasAce && hasFace) {
                return player;
            }
        }
        return null;
    }

    public static boolean forFinish(ArrayList<Player> players) {
        int countActive = 0;
        int countStand = 0;

        for (Player player : players) {
            switch (player.getMiscString1()) {
                case "[Active]":
                    countActive++;
                    break;
                case "[Stand]":
                    countStand++;
                    break;
            }
        }

        if (countActive == 0) {
            return true;
        }
        if (countActive == 1 && countStand == 0) {
            for (Player player : players) {
                if (player.getMiscString1().equals("[Active]")) {
                    player.setMiscString1("[Stand]");
                    return true;
                }
            }
        }

        return false;
    }

    public static Player nextActive(Player currentPlayer, ArrayList<Player> players) {
        int currentIndex = players.indexOf(currentPlayer);
        for (int i = currentIndex + 1; i < players.size(); i++) {
            Player potentialNextPlayer = players.get(i);
            if (potentialNextPlayer.getMiscString1().equals("[Active]")) {
                return potentialNextPlayer;
            }
        }
        for (int i = 0; i < currentIndex; i++) {
            Player potentialNextPlayer = players.get(i);
            if (potentialNextPlayer.getMiscString1().equals("[Active]")) {
                return potentialNextPlayer;
            }
        }
        return currentPlayer;
    }
}
