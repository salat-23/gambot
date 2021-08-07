package CardGame;

import java.util.List;

public class DurakGame {

    private DurakDeck deck;

    public DurakGame() {
        deck = new DurakDeck().fillDeck();
    }

    public String getDeckInfo() {
        StringBuilder info = new StringBuilder("This is a deck with " + DurakDeck.CARD_AMOUNT + " and the first 5 cards are: ");
        for (int i = 0; i < 5; i++) {
            info.append("\n");
            info.append(deck.getCardByNumber(i).getName());
        }
        return info.toString();
    }


}

