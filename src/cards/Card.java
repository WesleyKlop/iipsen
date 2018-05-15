package cards;

/**
 * Card class
 * Contains CardType
 * TODO: Path for graphic depending on #cardType
 * TODO: Custom card functionality
 */
public class Card {
    private final CardType cardType;

    Card(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return "Card{cardType=" + cardType + '}';
    }
}
