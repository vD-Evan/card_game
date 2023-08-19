package syst17796.object_classes.standard;

import java.util.ArrayList;
import syst17796.abstract_classes.DeckRequirements;

public class Deck extends DeckRequirements<Card> {
    public Deck() {
        theDeck = new ArrayList<Card>();

        buildDeck();
    }

    public void buildDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                theDeck.add(card);
            }
        }
    }
}
