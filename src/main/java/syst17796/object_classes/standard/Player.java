package syst17796.object_classes.standard;

import syst17796.abstract_classes.PlayerRequirements;

public class Player extends PlayerRequirements<Card> {
    public Player(String name) {
        super(name);
    }

    @Override
    public void playCard(Card card) {
        if (hand.contains(card)) {
            removeCardFromHand(card);
        } else {
            System.out.println("The card is not in the player's hand.");
        }
    }
}
