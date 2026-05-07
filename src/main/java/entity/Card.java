package entity;

/*
    todo:
        - get point value
 */
public class Card {
    private String suit;
    private String value;
    private boolean isFaceUp;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        if (isFaceUp) {
            return suit;
        }
        else {
            return "#"; //hidden
        }
    }

    public String getValue() {
        if (isFaceUp) {
            return value;
        }
        else {
            return "#";
        }
    }

    public int getPointValue() {
        if (isFaceUp) {
            //return point value
            switch(this.getValue()) {
                case "A" -> {
                    return 11;
                }
                case "K", "Q", "J", "10" -> {
                    return 10;
                }

                case "2", "3", "4", "5", "6", "7", "8", "9" -> {
                    return Integer.parseInt(getValue());
                }

                default -> {
                    System.out.println("Unexpected error");
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void flip() {
        isFaceUp = !isFaceUp;
    }
}
