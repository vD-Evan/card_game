package syst17796.object_classes.standard;

import syst17796.abstract_classes.CardRequirements;

public class Card extends CardRequirements {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Implement abstract methods for CardRequirements
    @Override
    public String getSuitAsString() {
        return suit.toString();
    }

    @Override
    public int getSuitAsInt() {
        return suit.ordinal() + 1;
    }

    @Override
    public String getRankAsString() {
        return rank.toString();
    }

    @Override
    public int getRankAsInt() {
        return rank.ordinal() + 1;
    }
}
