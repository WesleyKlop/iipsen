package game.cards;

/**
 * @author Wesley Klop
 */
public class InfiniteCardStack extends CardStack {

    @Override
    public Card getCard(CardType type) {
        return new Card(type);
    }

    @Override
    public boolean containsCards(CardType type, int count) {
        return true;
    }

    @Override
    public boolean containsCards(Card... cards) {
        return true;
    }

    @Override
    public boolean containsCards(CardStack cards) {
        return true;
    }

    @Override
    public void takeCards(CardStack cards) {
        // Always works :)
    }

    @Override
    public void takeCards(CardType type, int count) {
        // Always works :)
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
