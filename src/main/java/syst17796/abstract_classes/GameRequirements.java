package syst17796.abstract_classes;

import java.util.ArrayList;

public abstract class GameRequirements<P extends PlayerRequirements<C>, C extends CardRequirements, D extends DeckRequirements<C>> {
    // requirements for each game
    // Generic types, <P>, <C>, <D> allows for non-standard versions of Player,
    // Card, and Deck to be used

    /*
     * Notes:
     * - Assumes every game will have players, deck
     * 
     * - Requires the following methods:
     * New Game (importing the list of players from the menu)
     * Setup Game (clearing the deck and scores if restarting, building the deck,
     * preliminary deals)
     * Instructions (for each game)
     * The Actual Game (game flow, handling how turns work)
     * Player Turn (turn flow)
     * End Game (for end game results)
     */

    protected ArrayList<P> players;
    protected D gameDeck;

    public abstract void newGame(ArrayList<String> playerNames);

    protected abstract void setupGame();

    protected abstract void instructions();

    protected abstract void runGame();

    protected abstract void playerTurn(P player);

    protected abstract void endGame();
}
