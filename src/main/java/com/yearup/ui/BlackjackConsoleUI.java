package com.yearup.ui;

import com.yearup.enums.PlayerAction;
import com.yearup.game.BlackjackGame;
import com.yearup.models.Player;
import java.util.Scanner;


public class BlackjackConsoleUI {
    private BlackjackGame game;
    private Scanner scanner;

    public BlackjackConsoleUI() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        displayWelcome();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        game = new BlackjackGame(name, 1000);

        boolean playing = true;
        while (playing) {
            playRound();

            System.out.print("\nPlay another round? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playing = response.equals("y") || response.equals("yes");
        }

        displayGameOver();
    }

    private void playRound() {
        clearScreen();
        displayPlayerInfo();


        displayHands(true);

        // Check for player blackjack
        if (game.getPlayer().getHand().isBlackjack()) {
            System.out.println("\n🎉 BLACKJACK! 🎉");
            displayHands(false);
            System.out.println("\n" + game.determineWinner());
            return;
        }

        // Player's turn
        boolean playerTurn = true;
        while (playerTurn && game.isGameInProgress()) {
            PlayerAction action = getPlayerAction();

            if (action == PlayerAction.STAND) {
                playerTurn = false;
            } else {
                game.performAction(action);
                displayHands(true);

                if (game.getPlayer().getHand().isBusted()) {
                    System.out.println("\n💥 BUST! You went over 21!");
                    playerTurn = false;
                }
            }
        }

        // Reveal dealer's hand and determine winner
        if (!game.getPlayer().getHand().isBusted()) {
            System.out.println("\nDealer's turn...");
            game.performAction(PlayerAction.STAND);
        }

        displayHands(false);
        System.out.println("\n" + game.determineWinner());
    }



    private PlayerAction getPlayerAction() {
        System.out.println("\n=== Your Turn ===");
        System.out.println("1. Hit");
        System.out.println("2. Stand");
        System.out.print("Choose action: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1: return PlayerAction.HIT;
                case 2: return PlayerAction.STAND;
                default:
                    System.out.println("Invalid option! Hitting by default.");
                    return PlayerAction.HIT;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Hitting by default.");
            return PlayerAction.HIT;
        }
    }

    private void displayHands(boolean hideDealer) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DEALER'S HAND:");
        if (hideDealer) {
            System.out.println(game.getDealerHand().toStringHideFirst());
        } else {
            System.out.println(game.getDealerHand().toString());
        }

        System.out.println("\nYOUR HAND:");
        System.out.println(game.getPlayer().getHand().toString());
        System.out.println("=".repeat(50));
    }

    private void displayPlayerInfo() {
        Player player = game.getPlayer();
        System.out.println("\n" + player.toString());
    }

    private void displayWelcome() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║        ♠ ♥ BLACKJACK ♦ ♣              ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\nWelcome to Blackjack!");
        System.out.println();
    }

    private void displayGameOver() {
        clearScreen();
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            GAME OVER                   ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Thanks for playing, " + game.getPlayer().getName() + "!");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
