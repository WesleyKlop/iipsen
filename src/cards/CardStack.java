package cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

/**
 * @author Wesley
 * CardStack contains a list of card types and how many there are left of it.
 */
public class CardStack extends EnumMap<CardType, Integer> implements Serializable {
    private static final int DEFAULT_LOCOMOTIVE_COUNT = 14;
    private static final int DEFAULT_CART_COUNT = 12;

    private CardStack() {
        super(CardType.class);
    }

    /**
     * Creates a stack of train cards
     *
     * @return a stack containing
     */
    static CardStack CreateTrainCardStack() {
        CardStack stack = new CardStack();
        stack.generateTrainCards();

        return stack;
    }

    private void generateTrainCards() {
        for (CardType type : CardType.values()) {
            if (type == CardType.LOCOMOTIVE) {
                this.put(type, DEFAULT_LOCOMOTIVE_COUNT);
            } else {
                this.put(type, DEFAULT_CART_COUNT);
            }
        }
    }

    /**
     * Get a Card of specific type from the stack
     *
     * @param type the type of card you need
     * @return the Card with type
     * @throws Exception when the CardType is not present in the stack
     */
    Card getCard(CardType type) throws Exception {
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
     * Get a random card from the stack
     * Side effects: Generates new stack when there are no cards left
     *
     * @return a random Card
     */
    Card getRandomCard() {
        // I think we should generate a new stack when we are out of cards...
        if (this.size() == 0) {
            System.out.println("Out of cards, generating new stack");
            this.generateTrainCards();
        }

        // Get a random cards.CardType from the stack
        Random random = new Random();
        List<CardType> keys = new ArrayList<>(this.keySet());

        CardType randomCardType = keys.get(random.nextInt(keys.size()));

        // Decrement the amount of that type in the map
        this.put(randomCardType, this.get(randomCardType) - 1);

        // Remove from map when cards are out
        if (this.get(randomCardType) == 0) {
            this.remove(randomCardType);
        }

        return new Card(randomCardType);
    }

    @Override
    public String toString() {
        return "cards.CardStack" + super.toString();
    }
}
