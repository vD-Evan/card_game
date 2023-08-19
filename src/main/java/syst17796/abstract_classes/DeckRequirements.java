package syst17796.abstract_classes;

import java.util.ArrayList;
import java.util.Collections;

public abstract class DeckRequirements<T> { // requirements for future deck classes
    protected ArrayList<T> theDeck = new ArrayList<T>(); // uses <T> as a type placeholder as decks can be made of
                                                         // standard or special cards

    public abstract void buildDeck(); // Assumes all decks must be be built (ie all cards added to it

    public void shuffle() { // uses Collections to shuffle the Array List
        Collections.shuffle(theDeck);
    }

    public T dealCard() { // Deals a card, prompts users if there's an error.
        if (!theDeck.isEmpty()) {
            return theDeck.remove(0);
        }
        System.out.println("There are no remaining cards in the deck to deal.");
        return null; // up to each game to determine how to handle an empty deck (game over,
                     // reshuffle, etc.)
    }

    public int getSize() { // if games require the deck size (ie. for scoring purposes)
        return theDeck.size();
    }

    @Override
    public String toString() { // returns the size of the deck
        return "Number of cards remaining in the deck: " + theDeck.size();
    }
}