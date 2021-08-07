package com.salat.CardGame.Durak;

import com.salat.CardGame.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private boolean isReady;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
        isReady = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
