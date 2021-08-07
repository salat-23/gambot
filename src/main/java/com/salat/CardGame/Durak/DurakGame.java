package com.salat.CardGame.Durak;

import com.salat.CardGame.Card;
import com.salat.Game;

public class DurakGame implements Game {

    private DurakDeck deck;

    public DurakDeck getDeck() {
        return deck;
    }

    public DurakGame() {
        deck = new DurakDeck().fillDeck();
        deck.shuffleDeck();
    }

    public String getDeckInfo() {
        StringBuilder info = new StringBuilder("This is a deck with " + DurakDeck.CARD_AMOUNT + " and the first 5 cards are: ");
        for (int i = 0; i < 5; i++) {
            Card card = deck.getCardByNumber(i);
            info.append("\n");
            info.append(card.getName());
            info.append(" - ");
            info.append(card.toString());
        }
        return info.toString();
    }





}

