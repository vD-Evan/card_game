package syst17796.games.libraries.blackjack;

import java.util.ArrayList;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Player;

public class Check { // handles checks

    public static Player initialWin(ArrayList<Player> players) {
        for (Player player : players) { // cycle through players, determines if player starts with winning hand
            boolean hasAce = false;
            boolean hasFace = false;
            ArrayList<Card> hand = player.getHand();

            // winning hand determined by player having an ace and a face card
            for (Card card : hand) {
                if (card.getRankAsInt() == 1) {
                    hasAce = true;
                } else if (card.getRankAsInt() > 10) {
                    hasFace = true;
                }
            }
            if (hasAce && hasFace) { // returns winning player
                return player;
            }
        }
        return null; // if reached, returns null to state no player has a winning hand
    }

    public static boolean forFinish(ArrayList<Player> players) { // checks if game has finished
        int countActive = 0;
        int countStand = 0;

        // counts the number of players that are active or have elected to stand
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

        // if no players are active, end game
        if (countActive == 0) {
            return true;
        }

        // if only one player active and no player standing, the rest of players have
        // bust
        if (countActive == 1 && countStand == 0) {
            for (Player player : players) {
                if (player.getMiscString1().equals("[Active]")) {
                    player.setMiscString1("[Stand]");
                    return true; // mark active player as stand and end game
                }
            }
        }
        return false; // otherwise, there are 2 or more active players, continue game
    }

    // determine the next active player
    public static Player nextActive(Player currentPlayer, ArrayList<Player> players) {
        int currentIndex = players.indexOf(currentPlayer);

        // cycle through the list starting at current player to find next active player
        for (int i = currentIndex + 1; i < players.size(); i++) {
            Player potentialNextPlayer = players.get(i);
            if (potentialNextPlayer.getMiscString1().equals("[Active]")) {
                return potentialNextPlayer;
            }
        }

        // if first search returns no active players, loop to beginning and search again
        for (int i = 0; i < currentIndex; i++) {
            Player potentialNextPlayer = players.get(i);
            if (potentialNextPlayer.getMiscString1().equals("[Active]")) {
                return potentialNextPlayer;
            }
        }
        return currentPlayer; // no other active players, return current player as the only active player
    }
}
