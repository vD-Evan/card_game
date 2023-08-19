package syst17796.object_classes.standard;

public enum Suit { // stores the standard card suits as an enum
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    @Override
    public String toString() { // provides suits in a more readable format than in all caps
        switch (this) {
            case CLUBS:
                return "Clubs";
            case DIAMONDS:
                return "Diamonds";
            case HEARTS:
                return "Hearts";
            case SPADES:
                return "Spades";
            default:
                return null;
        }
    }
}
