package syst17796.abstract_classes;

public abstract class CardRequirements {
    public abstract String getSuitAsString();

    public abstract int getSuitAsInt();

    public abstract String getRankAsString();

    public abstract int getRankAsInt();

    // Custom toString method for cards.
    @Override
    public String toString() {
        return getRankAsString() + " of " + getSuitAsString();
    }
}
