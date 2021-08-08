package com.salat.CardGame.Durak;

import com.salat.CardGame.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardCycleInfo {
    private HashMap<Card, Card> attackDefendCards;
    private boolean isDefended;
    private boolean isEnded;


    public CardCycleInfo() {
        attackDefendCards = new HashMap<>();
        isDefended = false;
        isEnded = false;

    }

    public void update() {
        if (attackDefendCards.containsValue(null)) {
            isDefended = false;
        }
    }

    public boolean addAttack(Card card) {
        if (attackDefendCards.size() == 0) {
            attackDefendCards.put(card, null);
            update();
            return true;
        }
        else if (canAttack(card)) {
            update();
            attackDefendCards.put(card, null);
            return true;
        }
        update();
        return false;
    }

    public boolean addAttack(List<Card> cards) {
        if (attackDefendCards.size() == 0 && canAttackList(cards)) {
            for (Card card : cards) {
                attackDefendCards.put(card, null);
            }
            update();
            return true;
        }
        else if (canAttackList(cards)) {
            for (Card card : cards) {
                attackDefendCards.put(card, null);
            }
            update();
            return true;
        }
        update();
        return false;
    }

    public boolean addDefend(Card card, Card cardToBeat) {
        CardWrapper wrapper = canDefend(card);
        if (wrapper.canDefend) {
            attackDefendCards.put(wrapper.card, card);
            update();
            return true;
        }
        update();
        return false;
    }

    public void end() {

    }

    private boolean canBeEnded() {
        return !isEnded;
    }

    public boolean canAttackList(List<Card> cards) {
        Card.CardType type = cards.get(0).getType();
        for (Card card : cards) {
            if (!card.getType().equals(type)) return false;
        }
        return true;
    }

    public boolean canAttack(Card card) {
        for (Map.Entry<Card, Card> entry : attackDefendCards.entrySet()) {
            if (entry.getKey().getType().equals(card.getType()) || entry.getValue().getType().equals(card.getType()))
                return true;
        }
        return false;
    }

    public CardWrapper canDefend(Card card) {
        for (Map.Entry<Card, Card> entry : attackDefendCards.entrySet()) {
            if (entry.getKey().getSuit().equals(card.getSuit()) && entry.getKey().getWeight() < card.getWeight())
                return new CardWrapper(entry.getKey(), true);
        }
        return new CardWrapper(null, false);
    }


}
