package syst17796.object_classes.standard;

import java.util.ArrayList;
import syst17796.abstract_classes.DeckRequirements;

public class Deck extends DeckRequirements<Card> { // standard deck, uses standard cards
    public Deck() { // creates a new deck,
        theDeck = new ArrayList<Card>();
        buildDeck(); // builds deck from below logic
    }

    // takes each rank for each suit and each suit, adds new card to deck
    public void buildDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                theDeck.add(card);
            }
        }
    }
}
