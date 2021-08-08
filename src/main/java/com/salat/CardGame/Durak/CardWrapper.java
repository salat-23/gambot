package com.salat.CardGame.Durak;

import com.salat.CardGame.Card;

public class CardWrapper {
    public Card card;
    public boolean canDefend;

    public CardWrapper(Card card, boolean bool) {
        this.card = card;
        canDefend = bool;

    }
}
