package game.cards;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Wesley Klop
 */
class CardStackControllerTest {

    @Test
    void generatedStackContainsAllTypes() {
        CardStackController stack = new CardStackController();
        for (Map.Entry<CardType, Integer> entry : stack.getStack().entrySet()) {
            var type = entry.getKey();
            if (type == CardType.LOCOMOTIVE && entry.getValue() != 14) {
                fail("Incorrect amount of locomotives");
            } else if (type != CardType.CART_ANY && type != CardType.LOCOMOTIVE && entry.getValue() != 12) {
                fail("Incorrect amount of carts for type " + type);
            }
        }
    }

    @Test
    void returnsRandomCard() {
        assertNotNull(new CardStackController().getRandomCard());
    }

    @Test
    void returnsFalseWhenRequestingMoreThanExists() {
        assertFalse(new CardStackController().getStack().containsCards(CardType.CART_BLUE, 1337));
    }
}