package com.yearup.game;

import com.yearup.enums.PlayerAction;
import com.yearup.models.Card;
import com.yearup.models.Deck;
import com.yearup.models.Hand;
import com.yearup.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//handsles rules of the game
public class BlackjackGame {

    private Deck deck;
    private Player player;
    private Hand dealerHand;
    private boolean gameInProgress;

    public BlackjackGame(String playerName, int startingChips) {
        this.deck = new Deck();
        this.player = new Player(playerName);
        this.dealerHand = new Hand();
        this.gameInProgress = false;
        deck.shuffle();
    }

    public Player getPlayer() {
        return player;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public boolean placeBet(int amount) {
        if (!gameInProgress) {
            startNewRound();
            return true;
        }
        return false;
    }

    private void startNewRound() {
        player.clearHand();
        dealerHand.clear();

        // Deal initial cards
        player.getHand().addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        gameInProgress = true;
    }

    public boolean performAction(PlayerAction action) {
        if (!gameInProgress) {
            return false;
        }

        switch (action) {
            case HIT:
                return hit();
            case STAND:
                return stand();
            default:
                return false;
        }
    }

    private boolean hit() {
        player.getHand().addCard(deck.drawCard());

        if (player.getHand().isBusted()) {
            endRound();
            return false;
        }
        return true;
    }

    private boolean stand() {
        playDealerHand();
        endRound();
        return true;
    }

    private void playDealerHand() {
        // Dealer must hit on 16 or less, stand on 17 or more
        while (dealerHand.getValue() < 17) {
            dealerHand.addCard(deck.drawCard());
        }
    }

    private void endRound() {
        gameInProgress = false;
    }

    public String determineWinner() {
        Hand playerHand = player.getHand();
        int playerValue = playerHand.getValue();
        int dealerValue = dealerHand.getValue();

        // Player blackjack
        if (playerHand.isBlackjack() && !dealerHand.isBlackjack()) {
            // 3:2 payout
            return "BLACKJACK!";
        }

        // Both blackjack
        if (playerHand.isBlackjack() && dealerHand.isBlackjack()) {
            return "Push - Both have Blackjack!";
        }

        // Player busted
        if (playerHand.isBusted()) {
            return "You busted! Dealer wins.";
        }

        // Dealer busted
        if (dealerHand.isBusted()) {

            return "Dealer busted!";
        }

        // Compare values
        if (playerValue > dealerValue) {
            return "You win";
        } else if (playerValue < dealerValue) {
            return "Dealer wins.";
        } else {
            return "Push - It's a tie!";
        }
    }

    public void resetGame() {
        deck.reset();
        player.clearHand();
        dealerHand.clear();
        gameInProgress = false;
    }


}
