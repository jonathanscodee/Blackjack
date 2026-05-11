package com.yearup.models;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;
    private int bet;

    public Hand() {
        this.cards = new ArrayList<>();
        this.bet = 0;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int getValue() {
        int value = 0;
        int aces = 0;

        for (Card card : cards) {
            value += card.getValue();
            if (card.getRank().getDisplay().equals("A")) {
                aces++;
            }
        }

        // Adjust for Aces (11 to 1 if bust)
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    public boolean isBusted() {
        return getValue() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    public int getCardCount() {
        return cards.size();
    }


    public void clear() {
        cards.clear();
        bet = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(" ");
        }
        sb.append("(Value: ").append(getValue()).append(")");
        return sb.toString();
    }


    public String toStringHideFirst() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Hidden] ");
        for (int i = 1; i < cards.size(); i++) {
            sb.append(cards.get(i).toString()).append(" ");
        }
        return sb.toString();
    }

}
