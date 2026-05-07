package entity;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void deal(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    public int getValue() {
        int value = 0;

        for (Card card : cards) {
            card.flip(); //turn card over
            value += card.getPointValue(); //tallys score
            card.flip(); //hide again
        }

        return value;
    }
}
