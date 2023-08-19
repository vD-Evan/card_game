package syst17796.games.libraries.blackjack;

import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Player;

public class Scores { // handles scores
    public static int handValue(Player player, boolean currentPlayer) { // makes note if player is current player
        int handScore = 0;
        int numOfAces = 0;

        for (Card card : player.getHand()) { // cycles through cards in hand, adds value of ranks
            if (!currentPlayer) { // if not calculating current player's hand, skip the first card
                currentPlayer = true;
                continue;
            }
            if (card.getRankAsInt() > 10) { // sets face cards to 10, adds the value
                handScore += 10;
            } else if (card.getRankAsInt() == 1) { // sets aces to 1, adds the value and notes the amount of aces
                handScore += 11;
                numOfAces += 1;
            } else {
                handScore += card.getRankAsInt(); // adds the value
            }
        }

        // aces can be 1 or 11 to the player's benefit,
        // if handscore exceeds 21 but there are aces in hand, reduce score by 10
        while (handScore > 21 && numOfAces > 0) { // and repeat until either:
            handScore -= 10; // hand score is less than 21 or
            numOfAces -= 1; // the player has no more aces to reduce in value
        }

        return handScore; // return
    }
}
