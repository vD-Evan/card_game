package syst17796.abstract_classes;

import java.util.ArrayList;

public abstract class PlayerRequirements<T> { // requirements for future player classes
    /*
     * Note:
     * - Generic type <T> allows for non-standard versions of Card to be used
     * - Assumes every player has a name, a hand with cards, and a score;
     * - extra variables present if required by the specific game
     */
    protected String name;
    protected ArrayList<T> hand;
    protected int score;
    protected int miscInteger1, miscInteger2, miscInteger3;
    protected String miscString1, miscString2, miscString3;

    // constructor: each player has a name, gives a new player an empty hand
    public PlayerRequirements(String name) {
        this.name = name;
        this.hand = new ArrayList<T>();
    }

    public String getName() {
        return name;
    } // set name handled by constructor

    public ArrayList<T> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public int getHandSize() {
        return hand.size();
    }

    // equivalent of setHand to add a card
    public void addCardToHand(T card) {
        hand.add(card);
    }

    // equivalent of setHand to remove a card
    public void removeCardFromHand(T card) {
        hand.remove(card);
    }

    // equivalent of setHand to clear the hand
    public void clearHand() {
        this.hand = new ArrayList<T>();
    }

    public void setScore(int score) {
        this.score = score;
    }

    // assumes a player can play a card, implementation to change based on specific
    // use case
    public abstract void playCard(T card);

    // misc variables get/set methods:
    public int getMiscInteger1() {
        return miscInteger1;
    }

    public void setMiscInteger1(int miscInteger1) {
        this.miscInteger1 = miscInteger1;
    }

    public int getMiscInteger2() {
        return miscInteger2;
    }

    public void setMiscInteger2(int miscInteger2) {
        this.miscInteger2 = miscInteger2;
    }

    public int getMiscInteger3() {
        return miscInteger3;
    }

    public void setMiscInteger3(int miscInteger3) {
        this.miscInteger3 = miscInteger3;
    }

    public String getMiscString1() {
        return miscString1;
    }

    public void setMiscString1(String miscString1) {
        this.miscString1 = miscString1;
    }

    public String getMiscString2() {
        return miscString2;
    }

    public void setMiscString2(String miscString2) {
        this.miscString2 = miscString2;
    }

    public String getMiscString3() {
        return miscString3;
    }

    public void setMiscString3(String miscString3) {
        this.miscString3 = miscString3;
    }
}
