package com.salat.CardGame.Durak;

import com.salat.CardGame.Card;
import com.salat.Game;

import java.util.ArrayList;
import java.util.List;

public class DurakGame implements Game {

    private DurakDeck deck;
    private List<Player> players;
    private boolean isStarted;
    private Card trumpCard;

    public DurakDeck getDeck() {
        return deck;
    }

    public DurakGame() {
        isStarted = false;
        players = new ArrayList<>();
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

    public void addPlayer(Player player) {
        if (isStarted) return;
        players.add(player);
    }

    public void startGame() {
        deck.shuffleDeck();
        trumpCard = deck.takeBottomCard();
        isStarted = true;
    }

    public void setDeck(DurakDeck deck) {
        this.deck = deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public Card getTrumpCard() {
        return trumpCard;
    }

    public void setTrumpCard(Card trumpCard) {
        this.trumpCard = trumpCard;
    }

    public void initialCardGiveaway() {
        for (int i = 0; i < 6; i++) {
            for (Player player : players) {
                player.getCards().add(deck.takeTopCard());
            }
        }
    }

    public void takeCards(Player player) {
        while (player.getCards().size() < 6 && deck.getDeckSize() > 0) player.getCards().add(deck.takeTopCard());
    }

    public void play(Player attack, Player defend, CardCycleInfo info) {

    }



}

