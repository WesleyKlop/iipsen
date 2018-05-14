package cards;

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
