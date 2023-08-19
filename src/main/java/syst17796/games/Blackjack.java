package syst17796.games;

import java.util.ArrayList;

import syst17796.abstract_classes.GameRequirements; // requires the requirements for a game
import syst17796.games.libraries.blackjack.*; // game library of extra classes/methods
import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Deck;
import syst17796.object_classes.standard.Player;
import syst17796.utility.UserInput;

public class Blackjack extends GameRequirements<Player, Card, Deck> { // uses standard player, card, deck
    Deck theDeck;
    ArrayList<Player> players;
    boolean blackjackWin;
    UserInput get = new UserInput();

    @Override
    public void newGame(ArrayList<String> playerNames) {
        get.aClearScreen();

        // imports player names and creates a player for each name
        players = new ArrayList<>();
        for (String name : playerNames) {
            Player player = new Player(name);
            players.add(player);
            System.out.println("Added " + player.getName() + " to the game.");
        }
        System.out.println("");
        setupGame(); // sets the game up, runs game from within set up
    }

    protected void setupGame() { // sets up the game

        // instructions with option to view
        System.out.println("Would you like to view the instructions? [Yes]/[No]");
        boolean choice = get.aBoolean("Yes", "No");
        if (choice) {
            instructions();
        }
        get.aClearScreen();

        // (re)sets the deck
        if (theDeck == null) {
            System.out.println("Compiling a new deck for the game.");
        } else {
            System.out.println("Resetting the deck for a new game.");
        }
        theDeck = new Deck();
        System.out.println("Shuffling the deck.");
        theDeck.shuffle();

        // clears all players' hand and marks player as active
        for (Player player : players) {
            player.clearHand();
            player.setMiscString1("[Active]");
        }

        // preliminary deal
        System.out.printf("%nDealing first card.%n");
        for (Player player : players) {
            player.addCardToHand(theDeck.dealCard());
        }
        System.out.println("Dealing second card.");
        for (Player player : players) {
            player.addCardToHand(theDeck.dealCard());
        }

        // wishes players good luck, waits, then runs the game
        System.out.printf("%nGood Luck!%n");
        get.aWait();
        runGame();
    }

    // instructions for the game
    @Override
    protected void instructions() {
        get.aClearScreen();
        System.out.printf("%s%n%n%s%n%s%n",
                "Welcome to the game of Blackjack!",
                "Goal:",
                "To have the combined value of cards as close to, or matching, without exceeding 21.");
        get.aWait(); // waits for above output to allow for player to read
                     // without presenting too much information at once

        System.out.printf("%s%n%s%n%s%n%s%n%s%n",
                "Instructions:",
                "Player States:",
                "  [Active] - Players are able to take actions and are eligible to win the game.",
                "  [Stand] - Players are not able to take actions and are eligible to win the game.",
                "  [Bust] - Players are not able to take actions and are ineligible to win the game.");
        get.aWait();

        System.out.printf("%s%n%s%n%s%n%s%n%n%s%n%s%n%s%n%s%n%s%n%s%n",
                "Order of the Game",
                "  Each player is dealt a hidden card and a visible card to start.",
                "  If a player is dealt an Ace and a Face card (Jack, Queen, King), that player is declared the winner.",
                "  If not, normal play resumes; see below:",
                "  All [Active] players take turns performing actions.",
                "  The Actions are [Hit] and [Stand]",
                "  Once all players have either [Stand] or [Bust], the game is over.",
                "  If all but one player has [Bust], the game will end with that player the winner.",
                "  All players that have [Bust] will automatically lose",
                "  All players that have [Stand] will be ranked, the top ranked individual will win.");
        get.aWait();

        System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n",
                "Actions",
                "  [Hit] - The player is dealt a card that is visible to other players",
                "    If the new hand's value exceeds 21, the player will [Bust]",
                "    If the new hand's value matches 21, the player will [Stand]",
                "    If the new hand's value is less than 21, the player will continue to be [Active]",
                "  [Stand] - The player will be set to [Stand] and will not receive any more actions");
        get.aWait();

        System.out.printf("%s%n%s%n%s%n%n%s%n%s%n%s%n%s%n%s%n",
                "End Game:",
                "  All players that have [Bust] will be exempt from winning the game and will be displayed first",
                "  All players that have not [Bust] will be eligible to win the game and will be displayed second",
                "  Players will be ranked in the following manner:",
                "    - Hand Value (descending order)",
                "    - Amount of Cards in Hand (ascending order)",
                "    - Value of highest (then each subsequent) card in hand (descending order)",
                "    - Order of Registration for Game");
        get.aWait();

        System.out.printf("%s%n%s%n%s%n%s%n%s%n%n%s%n%s%n%s%n",
                "View:",
                "  Players will be given the following information preceding each action:",
                "    - Other players' name and current state",
                "    - Amount of cards held by other players",
                "    - Card name and cumulative value of other players' visible cards",
                "    - Card names and cumulative value of own hand (including hidden)",
                "      Note: hidden card will be marked as such",
                "    - Options to hit or stand.");
        get.aWait();
    }

    @Override
    protected void runGame() {
        boolean playAgain = false;

        do {
            if (Check.initialWin(players) != null) { // checks for initial win (ie. Black Jack)
                get.aClearScreen();
                System.out.println("Blackjack!");
                Player luckyWinner = Check.initialWin(players);
                System.out.println(luckyWinner.getName() + " was dealt a winning hand!");
                System.out.printf("%n%nSee below for the scores:%n");

            } else { // otherwise, no one was lucky, standard game resumes

                boolean gameEnd = false; // marks game as active
                do {
                    for (int i = 0; i < players.size(); i++) { // cycles through players
                        Player player = players.get(i);
                        if (player.getMiscString1().equals("[Active]")) { // if currentplayer is [Active], provide turn
                            playerTurn(player);
                            gameEnd = Check.forFinish(players); // check for game end at the end of each player's turn
                            if (!gameEnd) {
                                Player nextPlayer = Check.nextActive(player, players); // determines the next [active]
                                                                                       // player
                                get.aClearScreen();
                                Display.nextActive(nextPlayer); // prompts player to pass device to next player
                                get.aWait(); // clear screen and waits used to prevent players from seeing other
                                             // player's score
                            } else {
                                get.aClearScreen(); // otherwise, game has ended, informs players and breaks from for
                                                    // loop
                                System.out.println("All players have elected to [Stand] or have [Bust].");
                                System.out.println("See below for the scores:");
                                break;
                            }
                        }
                    }
                } while (!gameEnd);
            }
            endGame(); // game has ended, run endgame logic

            // asks users to play again
            System.out.println("Do you want to play again? [Yes]/[No]");
            System.out.print("> ");
            playAgain = get.aBoolean("Yes", "No");
            if (playAgain) { // if so, rerun setup to launch a new game
                get.aClearScreen();
                setupGame();
            }
        } while (playAgain); // loops while players want to play again
    }

    // flow of player's turn
    @Override
    protected void playerTurn(Player player) {
        get.aClearScreen();
        Display.table(player, players); // displays the table
        boolean choice = get.aBoolean("1", "2"); // asks user to hit or stand

        if (!choice) { // marks the user as stand, ends the turn
            player.setMiscString1("[Stand]");
            System.out.println("You have elected to [Stand]");

        } else { // otherwise player requests a new card (Hit), adds a new card to hand
            Card newCard = theDeck.dealCard();
            player.addCardToHand(newCard);
            System.out.println("New Card: " + newCard);

            // recalculates score and displays it
            int newScore = Scores.handValue(player, true);
            System.out.println("Your new score is: " + newScore);

            // bust logic; if a player's score exceeds 21, mark player as bust, display a
            // (hopefully fun) message
            if (newScore > 21) {
                player.setMiscString1("[Bust]");
                System.out.println("Like Icarus, you've flown too close to the sun. Sorry, but you've Bust!");
            }
        }
        get.aWait();
    }

    // end game logic
    @Override
    protected void endGame() {
        Sort.byHighCard(players); // first sorts players based on their highest card
        Sort.byHandSize(players); // overwrites this sort by hand size
        Sort.byHandValue(players);// overwrites this sort by hand value
        // this leaves a sort of: hand value, hand size, highest card value

        // separates players into two lists: bust and stand players
        ArrayList<Player> bustPlayer = new ArrayList<>();
        ArrayList<Player> standPlayer = new ArrayList<>();

        // add player to bust list if score exceeds 21, add player to stand list if
        // score is not bust
        for (Player player : players) {
            int score = Scores.handValue(player, true);
            if (score > 21) {
                bustPlayer.add(player);
            } else {
                standPlayer.add(player);
            }
        }

        // displays the winning hands in order if applicable
        if (standPlayer.size() > 0) {
            System.out.println("Winning Hands:");
            for (int i = 0; i < standPlayer.size(); i++) {
                Player player = standPlayer.get(i);
                if (i == 0) {
                    System.out.print("Winner: ");
                } else {
                    int k = i + 1;
                    System.out.print("#" + k + ": ");
                }
                System.out.println(Display.playerStats(player)); // adds the player's stats
            }

            // prints a separating line if there are winning hands and bust hands
            if (bustPlayer.size() > 0) {
                System.out.printf("%n%s%n%n",
                        "--------------------");
            }
        } else { // displays if there are no winning hands
            System.out.printf("Sorry, all players have Bust!%n%n");
        }

        // displays players that have bust
        if (bustPlayer.size() > 0) {
            System.out.println("Bust Hands:");
            for (Player player : bustPlayer) {
                System.out.println(Display.playerStats(player));
            }
        }
    }
}