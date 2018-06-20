package game.cards;

import static client.UserPreferences.isColorBlind;

/**
 * Card class
 * Contains CardType
 * TODO: Path for graphic depending on #cardType
 * TODO: Custom card functionality
 */
public class Card {
    private final CardType cardType;

    /**
     * Create a Card of a specific type
     *
     * @param cardType the CardType
     */
    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * Get the CardType
     *
     * @return the CardType
     */
    public CardType getCardType() {
        return cardType;
    }

    /**
     * Get the filepath for the card
     * TODO: I think this should go in a controller / factory or in the view layer
     *
     * @return the filepath for a card
     */
    public String getPath() {
        return String.format("/cards/%s/%s.png", String.valueOf(isColorBlind()).toUpperCase(), cardType);
    }
}
