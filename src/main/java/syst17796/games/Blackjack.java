package syst17796.games;

import java.util.ArrayList;

import syst17796.abstract_classes.GameRequirements;
import syst17796.games.libraries.blackjack.Check;
import syst17796.games.libraries.blackjack.Display;
import syst17796.games.libraries.blackjack.Scores;
import syst17796.games.libraries.blackjack.Sort;
import syst17796.object_classes.standard.Card;
import syst17796.object_classes.standard.Deck;
import syst17796.object_classes.standard.Player;
import syst17796.utility.UserInput;

public class Blackjack extends GameRequirements<Player, Card, Deck> {
    Deck theDeck;
    ArrayList<Player> players;
    boolean blackjackWin;
    UserInput get = new UserInput();

    @Override
    public void newGame(ArrayList<String> playerNames) {
        players = new ArrayList<>();
        get.aClearScreen();
        for (String name : playerNames) {
            Player player = new Player(name);
            players.add(player);
            System.out.println("Added " + player.getName() + " to the game.");
        }
        System.out.println("");
        setupGame();
    }

    protected void setupGame() {
        System.out.println("Would you like to view the instructions? [Yes]/[No]");
        boolean choice = get.aBoolean("Yes", "No");

        if (choice) {
            instructions();
        }
        get.aClearScreen();

        if (theDeck == null) {
            System.out.println("Compiling a new deck for the game.");
        } else {
            System.out.println("Resetting the deck for a new game.");
        }
        for (Player player : players) {
            player.clearHand();
            player.setMiscString1("[Active]");
        }
        theDeck = new Deck();
        System.out.println("Shuffling the deck.");
        theDeck.shuffle();
        System.out.printf("%nDealing first card.%n");
        for (Player player : players) {
            player.addCardToHand(theDeck.dealCard());
        }
        System.out.println("Dealing second card.");
        for (Player player : players) {
            player.addCardToHand(theDeck.dealCard());
        }

        System.out.printf("%nGood Luck!%n");
        get.aWait();
        runGame();
    }

    @Override
    protected void instructions() {
        get.aClearScreen();
        System.out.printf("%s%n%n%s%n%s%n",
                "Welcome to the game of Blackjack!",
                "Goal:",
                "To have the combined value of cards as close to, or matching, without exceeding 21.");
        get.aWait();

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
            if (Check.initialWin(players) != null) {
                get.aClearScreen();
                System.out.println("Blackjack!");
                Player luckyWinner = Check.initialWin(players);
                System.out.println(luckyWinner.getName() + " was dealt a winning hand!");
                System.out.printf("%n%nSee below for the scores:%n");
            } else {

                boolean gameEnd = false;
                do {
                    for (int i = 0; i < players.size(); i++) {
                        Player player = players.get(i);
                        if (player.getMiscString1().equals("[Active]")) {
                            playerTurn(player);
                            gameEnd = Check.forFinish(players);
                            if (!gameEnd) {
                                Player nextPlayer = Check.nextActive(player, players);
                                get.aClearScreen();
                                Display.nextActive(nextPlayer);
                                get.aWait();
                            } else {
                                get.aClearScreen();
                                System.out.println("All players have elected to [Stand] or have [Bust].");
                                System.out.println("See below for the scores:");
                                break;
                            }
                        }
                    }
                } while (!gameEnd);
            }
            endGame();
            System.out.println("Do you want to play again? [Yes]/[No]");
            System.out.print("> ");
            playAgain = get.aBoolean("Yes", "No");
            if (playAgain) {
                get.aClearScreen();
                setupGame();
            }
        } while (playAgain);

    }

    @Override
    protected void playerTurn(Player player) {
        get.aClearScreen();
        Display.table(player, players);
        boolean choice = get.aBoolean("1", "2");

        if (!choice) {
            player.setMiscString1("[Stand]");
            System.out.println("You have elected to [Stand]");
        } else {
            Card newCard = theDeck.dealCard();
            player.addCardToHand(newCard);
            System.out.println("New Card: " + newCard);

            int newScore = Scores.handValue(player, true);
            System.out.println("Your new score is: " + newScore);

            if (newScore > 21) {
                player.setMiscString1("[Bust]");
                System.out.println("Like Icarus, you've flown too close to the sun. Sorry, but you've Bust!");
            }
        }
        get.aWait();
    }

    @Override
    protected void endGame() {
        Sort.byHighCard(players);
        Sort.byHandSize(players);
        Sort.byHandValue(players);

        ArrayList<Player> bustPlayer = new ArrayList<>();
        ArrayList<Player> standPlayer = new ArrayList<>();
        for (Player player : players) {
            int score = Scores.handValue(player, true);
            if (score > 21) {
                bustPlayer.add(player);
            } else {
                standPlayer.add(player);
            }
        }

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
                System.out.println(Display.playerStats(player));
            }
            System.out.printf("%n%s%n%n",
                    "--------------------");
        } else {
            System.out.printf("Sorry, all players have Bust!%n%n");
        }

        if (bustPlayer.size() > 0) {
            System.out.println("Bust Hands:");
            for (Player player : bustPlayer) {
                System.out.println(Display.playerStats(player));
            }
        }
    }
}