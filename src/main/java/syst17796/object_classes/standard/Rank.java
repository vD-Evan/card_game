package syst17796.object_classes.standard;

public enum Rank { // stores the standard card ranks as an enum
    ACE, TWO, THREE, FOUR,
    FIVE, SIX, SEVEN, EIGHT,
    NINE, TEN, JACK, QUEEN,
    KING;

    @Override
    public String toString() { // provides ranks in a more readable format than in all caps
        switch (this) {
            case ACE:
                return "Ace";
            case TWO:
                return "Two";
            case THREE:
                return "Three";
            case FOUR:
                return "Four";
            case FIVE:
                return "Five";
            case SIX:
                return "Six";
            case SEVEN:
                return "Seven";
            case EIGHT:
                return "Eight";
            case NINE:
                return "Nine";
            case TEN:
                return "Ten";
            case JACK:
                return "Jack";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            default:
                return null;
        }
    }
}
