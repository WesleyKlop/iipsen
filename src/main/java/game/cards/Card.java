package game.cards;

import client.UserPreferences;

/**
 * Card class
 * Contains CardType
 * TODO: Path for graphic depending on #cardType
 * TODO: Custom card functionality
 */
public class Card {
    private final CardType cardType;
    private String path;

    /**
     * Create a Card of a specific type
     *
     * @param cardType the CardType
     */
    Card(CardType cardType) {
        this.cardType = cardType;
        this.path = "/cards/" + UserPreferences.isColorBlind() + "/" + cardType + ".png";
    }

    /**
     * Get the CardType
     *
     * @return the CardType
     */
    CardType getCardType() {
        return cardType;
    }

    /**
     * Get the filepath for the card
     * TODO: I think this should go in a controller / factory or in the view layer
     *
     * @return the filepath for a card
     */
    public String getPath() {
        return path;
    }
}
