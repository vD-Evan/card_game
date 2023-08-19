package syst17796.games.libraries.blackjack;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Player;

public class Scores {
    public static int handValue(Player player, boolean currentPlayer) {
        int handScore = 0;
        int numOfAces = 0;

        for (Card card : player.getHand()) {
            if (!currentPlayer) {
                currentPlayer = true;
                continue;
            }
            if (card.getRankAsInt() > 10) {
                handScore += 10;
            } else if (card.getRankAsInt() == 1) {
                handScore += 11;
                numOfAces += 1;
            } else {
                handScore += card.getRankAsInt();
            }
        }

        while (handScore > 21 && numOfAces > 0) {
            handScore -= 10;
            numOfAces -= 1;
        }

        return handScore;
    }
}
