package syst17796.abstract_classes;

import java.util.ArrayList;
import java.util.Collections;

public abstract class DeckRequirements<T> {
    protected ArrayList<T> theDeck = new ArrayList<T>();

    public abstract void buildDeck();

    public void shuffle() {
        Collections.shuffle(theDeck);
    }

    public T dealCard() {
        if (!theDeck.isEmpty()) {
            return theDeck.remove(0);
        }
        System.out.println("There are no remaining cards in the deck to deal.");
        return null;
    }

    public int getSize() {
        return theDeck.size();
    }

    @Override
    public String toString() {
        return "Number of cards remaining in the deck: " + theDeck.size();
    }
}