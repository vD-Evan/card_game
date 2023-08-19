package syst17796.object_classes.standard;

import syst17796.abstract_classes.PlayerRequirements;

public class Player extends PlayerRequirements<Card> { // standard player, uses standard cards
    public Player(String name) { // uses parent constructor with user's name
        super(name);
    }

    // play card logic
    @Override
    public void playCard(Card card) {
        if (hand.contains(card)) {
            removeCardFromHand(card);
        } else {
            System.out.println("The card is not in the player's hand.");
        }
    }
}
