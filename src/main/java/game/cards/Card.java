package game.cards;


/**
 * Card class
 * Contains CardType
 * TODO: Custom card functionality
 */
class Card {
    private final CardType cardType;
    private String path;

    Card(CardType cardType) {
        this.cardType = cardType;
        this.path = "/cards/" + cardType + ".png";
    }

    CardType getCardType() {
        return cardType;
    }

//    @Override
//    public String toString() {
//        return "Card{cardType=" + cardType + '}';
//    }

    String getPath() {
        return path;
    }
}
