package game.cards;


/**
 * Card class
 * Contains CardType
 * TODO: Path for graphic depending on #cardType
 * TODO: Custom card functionality
 */
public class Card {
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

    public String getPath() {
        return path;
    }
}
