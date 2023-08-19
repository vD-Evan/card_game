package syst17796.abstract_classes;

public abstract class CardRequirements { // requirements for future card classes
    /*
     * Note:
     * - Assumes every card has a "Suit"
     * This can be Spades, as in a standard deck, or Blue, as in Uno
     * - Assumes every card has a "Rank"
     * This can be King, as in a standard deck, or 3, as in Uno
     * - Stores suits and ranks as a String for user visibility and as an integer
     * for program usage
     */

    public abstract String getSuitAsString();

    public abstract int getSuitAsInt();

    public abstract String getRankAsString();

    public abstract int getRankAsInt();

    // Custom toString method for cards.
    // Special cards, ie Uno, will override this with a more standard convention
    // ie "Blue +2" reading out as: 'return getSuitAsString() + " " +
    // getRankAsString();'
    @Override
    public String toString() {
        return getRankAsString() + " of " + getSuitAsString();
    }
}
