package game.cards;

import java.io.Serializable;
import java.util.EnumMap;

/**
 * CardStack contains a list of card types and how many there are left of it.
 *
 * @author Wesley Klop
 */
public class CardStack extends EnumMap<CardType, Integer> implements Serializable {

    /**
     * Creates an empty CardStack
     */
    public CardStack() {
        super(CardType.class);
    }

    /**
     * Get a Card of specific type from the stack
     *
     * @param type the type of card you need
     * @return the Card with type
     * @throws Exception when the CardType is not present in the stack
     */
    public Card getCard(CardType type) throws Exception {
        if (!this.containsKey(type)) {
            throw new Exception("CardType is not in the stack");
        }

        // Decrease amount of type by one
        this.put(type, this.get(type) - 1);

        if (this.get(type) == 0) {
            this.remove(type);
        }

        return new Card(type);
    }

    /**
     * Adds a card to the stack
     *
     * @param card the card to add
     */
    public void addCard(Card card) {
        addCard(card.getCardType());
    }

    /**
     * Adds a card to the stack
     *
     * @param cardType the card type to add
     */
    public void addCard(CardType cardType) {
        if (!this.containsKey(cardType))
            this.put(cardType, 1);
        else
            this.put(cardType, this.get(cardType) + 1);
    }

    /**
     * Checks if the stack contains the specified card type with the correct count
     *
     * @param type the CardType to look for
     * @param count the amount of cards the map should contain
     * @return true if the map contains enough cards of the specified type or false
     */
    public boolean containsCards(CardType type, int count) {
        if (count == 0) return true;
        Integer cardCount = get(type);
        return cardCount != null && cardCount >= count;
    }

    /**
     * Checks if the stack contains these cards
     *
     * @param cards an array of cards to look for
     * @return true if the map contains enough cards of the specified type or false
     */
    public boolean containsCards(Card... cards) {
        CardStack tempStack = new CardStack();

        for (Card card : cards) {
            tempStack.addCard(card);
        }

        return containsCards(tempStack);
    }

    /**
     * Check if the card stack contains all cards in the given cardstack
     *
     * @param cards the cardstack to compare against
     * @return true if the map contains enough cards of the specified type or false
     */
    public boolean containsCards(CardStack cards) {
        for (CardStack.Entry<CardType, Integer> entry : cards.entrySet()) {
            if (!this.containsKey(entry.getKey()) || this.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove cards specified in cardstack from the cardstack
     *
     * @param cards the cards to be removed
     * @throws Exception when a card does not exist in the cardstack
     */
    public void takeCards(CardStack cards) throws Exception {
        // TODO: This throws when it notices a card is not available, but would still remove the game.cards it checked before that so we should keep track of the game.cards removed and place them back in a try-catch
        for (CardStack.Entry<CardType, Integer> entry : cards.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                this.getCard(entry.getKey());
                System.out.println("Taking card from the stack");
            }
        }
    }

    /**
     * Remove x cards of specified type from the map
     *
     * @param type the type of card to remove
     * @param count the amount of cards to remove
     * @throws Exception when there are not enough cards in the stack
     */
    public void takeCards(CardType type, int count) throws Exception {
        if (count == 0) {
            return;
        }
        if (this.get(type) == null || this.get(type) < count) {
            throw new Exception("Not enough cards!");
        }
        this.put(type, this.get(type) - count);
    }

    public CardType getBiggestType() {
        CardType biggestType = null;
        for (Entry<CardType, Integer> entry : this.entrySet()) {
            if (entry.getKey() != CardType.LOCOMOTIVE && (biggestType == null || this.get(biggestType) < entry.getValue())) {
                biggestType = entry.getKey();
            }
        }
        return biggestType;
    }

    @Override
    public String toString() {
        return "CardStack" + super.toString();
    }
}
