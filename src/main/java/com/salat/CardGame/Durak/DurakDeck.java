package com.salat.CardGame.Durak;

import com.salat.CardGame.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DurakDeck {

    public static final int CARD_AMOUNT = 36;

    private static final List<Card.CardType> ALLOWED_CARD_TYPES = new ArrayList<>(Arrays.asList(
            Card.CardType.SIX,
            Card.CardType.SEVEN,
            Card.CardType.EIGHT,
            Card.CardType.NINE,
            Card.CardType.TEN,
            Card.CardType.JACK,
            Card.CardType.QUEEN,
            Card.CardType.KING,
            Card.CardType.ACE
    ));
    private static final List<Card.Suit> ALLOWED_SUIT_TYPES = new ArrayList<>(Arrays.asList(
            Card.Suit.CLUBS,
            Card.Suit.DIAMONDS,
            Card.Suit.HEARTS,
            Card.Suit.SPADES
    ));

    private final List<Card> deck;

    public DurakDeck() {
        deck = new ArrayList<>();
        fillDeck();
    }

    public DurakDeck fillDeck() {
        for (Card.Suit suit : ALLOWED_SUIT_TYPES) {
            for (Card.CardType type : ALLOWED_CARD_TYPES) {
                deck.add(new Card(type, suit));
            }
        }
        return this;
    }

    public Card getCardByNumber(int number) {
        if (number <= deck.size()) return deck.get(number);
        else throw new IllegalStateException("The value is not in the range of the card deck: " + number + " in " + deck.size());
    }

    public void shuffleDeck() {
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < CARD_AMOUNT; i++) {
            int changeIndex = random.nextInt(CARD_AMOUNT);
            Card buffer = deck.get(i);
            deck.set(i, deck.get(changeIndex));
            deck.set(changeIndex, buffer);
        }
    }

    public Card takeTopCard() {

        if (deck.size() <= 0) return null;

        Card card = deck.get(deck.size() - 1);
        deck.remove(deck.size() - 1);
        return card;

    }

    public int getDeckSize() {
        return deck.size();
    }

    public Card takeBottomCard() {
        if (deck.size() <= 0) return null;

        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }






}
