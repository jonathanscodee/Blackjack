package com.yearup.models;

import com.yearup.enums.Rank;
import com.yearup.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;


    public Deck() {
        cards = new ArrayList<>();

        initDeck();
    }

    public void initDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            initDeck();
            shuffle();
        }
        return cards.remove(cards.size() - 1);
    }

    public int remaningCards() {
        return cards.size();
    }

    public void reset() {
        cards.clear();
        initDeck();
        shuffle();
    }


}
