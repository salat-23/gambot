package com.salat.CardGame;

public class Card {

    public enum CardType {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    private String name;

    private CardType type;

    private Suit suit;

    private int weight;



    public Card(CardType type, Suit suit) {
        this.type = type;
        this.suit = suit;
        this.name = generateName();
        this.weight = generateWeight();
    }

    private int generateWeight() {
        switch (this.type) {
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case JACK: return 11;
            case QUEEN: return 12;
            case KING: return 13;
            case ACE: return 14;
            default:
                throw new IllegalStateException("Unexpected value: " + this.type);
        }
    }

    private String generateName() {

        if (this.suit == null || this.type == null) return "Empty";

        String suitStr;
        String typeStr;

        switch (this.type) {
            case ACE: typeStr = "A";break;
            case TWO: typeStr = "2";break;
            case THREE: typeStr = "3";break;
            case FOUR: typeStr = "4";break;
            case FIVE: typeStr = "5";break;
            case SIX: typeStr = "6";break;
            case SEVEN: typeStr = "7";break;
            case EIGHT: typeStr = "8";break;
            case NINE: typeStr = "9";break;
            case TEN: typeStr = "10";break;
            case JACK: typeStr = "J";break;
            case QUEEN: typeStr = "Q";break;
            case KING: typeStr = "K";break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.type);
        }
        switch (this.suit) {
            case CLUBS: suitStr = "C";break;
            case DIAMONDS: suitStr = "D";break;
            case HEARTS: suitStr = "H";break;
            case SPADES: suitStr = "S";break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.type);
        }

        return typeStr + suitStr;

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight >= 2) this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String cardName = type.name() + " of " + suit.name();
        return cardName;
    }

    public void setName(String name) {
        if (name != null) this.name = name;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        if (type != null) this.type = type;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        if (suit != null) this.suit = suit;
    }
}
