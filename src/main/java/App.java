import entity.Card;
import entity.Deck;
import entity.Hand;

public class App {
    public static void main(String [] args) {

        Deck deck = new Deck();
        Hand hand1 = new Hand();

        for (int i = 0; i < 5; i++) {
            Card card = deck.deal();
            hand1.deal(card);
        }

        int handValue = hand1.getValue();
        System.out.println("Hand value: " + handValue);

    }
}
