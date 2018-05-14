package cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

public class CardStack extends EnumMap<CardType, Integer> implements Serializable {
    private static final int DEFAULT_LOCOMOTIVE_COUNT = 14;
    private static final int DEFAULT_CART_COUNT = 12;

    private CardStack() {
        super(CardType.class);
    }

    static CardStack CreateCardStack() {
        CardStack stack = new CardStack();

        for (CardType type : CardType.values()) {
            if (type == CardType.LOCOMOTIVE) {
                stack.put(type, DEFAULT_LOCOMOTIVE_COUNT);
            } else {
                stack.put(type, DEFAULT_CART_COUNT);
            }
        }

        return stack;
    }

    Card getRandomCard() {
        // I think we should generate a new stack when we are out of cards...
        if (this.size() == 0) {
            for (CardType type : CardType.values()) {
                if (type == CardType.LOCOMOTIVE) {
                    this.put(type, DEFAULT_LOCOMOTIVE_COUNT);
                } else {
                    this.put(type, DEFAULT_CART_COUNT);
                }
            }
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
