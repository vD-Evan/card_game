package syst17796.object_classes.standard;

import syst17796.abstract_classes.CardRequirements;

public class Card extends CardRequirements { // standard card
    private final Suit suit; // uses the Suit Enum
    private final Rank rank; // and the Rank Enum

    public Card(Suit suit, Rank rank) { // declares every card has a rank and suit
        this.suit = suit;
        this.rank = rank;
    }

    // Implement abstract methods for CardRequirements (get/set)
    @Override
    public String getSuitAsString() {
        return suit.toString();
    }

    @Override
    public int getSuitAsInt() {
        return suit.ordinal() + 1; // changes from base-0 array to a base-1 value
    }

    @Override
    public String getRankAsString() {
        return rank.toString();
    }

    @Override
    public int getRankAsInt() {
        return rank.ordinal() + 1; // changes from base-0 array to a base-1 value
    }
}
