package syst17796.abstract_classes;

import java.util.ArrayList;

public abstract class GameRequirements<P extends PlayerRequirements<C>, C extends CardRequirements, D extends DeckRequirements<C>> {
    protected ArrayList<P> players;
    protected D gameDeck;

    /*
     * public GameRequirements(ArrayList<String> playerNames) {
     * players = new ArrayList<>();
     * for (String name : playerNames) {
     * P player = createPlayer(name);
     * players.add(player);
     * }
     * gameDeck = createDeck();
     * }
     */

    public abstract void newGame(ArrayList<String> playerNames);

    protected abstract void setupGame();

    protected abstract void instructions();

    protected abstract void runGame();

    protected abstract void playerTurn(P player);

    protected abstract void endGame();
}
