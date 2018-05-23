package game.cards;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wesley
 */
class CardStackTest {

    @Test
    void generatedStackContainsAllTypes() {
        CardStack stack = CardStack.CreateTrainCardStack();
        for (Map.Entry<CardType, Integer> entry : stack.entrySet()) {
            var type = entry.getKey();
            if (type == CardType.LOCOMOTIVE && entry.getValue() != 14) {
                fail("Incorrect amount of locomotives");
            } else if (type != CardType.CART_ANY && type != CardType.LOCOMOTIVE && entry.getValue() != 12) {
                fail("Incorrect amount of carts for type " + type);
            }
        }
    }

    @Test
    void newCardStackIsEmpty() {
        if (!new CardStack().isEmpty()) {
            fail("new stack is not empty");
        }
    }

    @Test
    void stackReturnsCorrectType() throws Exception {
        var stack = new CardStack();
        var type = CardType.CART_BLACK;
        stack.addCard(type);

        assertEquals(type, stack.getCard(type).getCardType());
    }

    @Test
    void throwsWhenCardNotInStack() {
        assertThrows(Exception.class, () -> new CardStack().getCard(CardType.CART_ANY));
    }

    @Test
    void requestingRandomCardOnEmptyStackReturnsNull() {
        assertNull(new CardStack().getRandomCard());
    }

    @Test
    void returnsRandomCard() {
        assertNotNull(CardStack.CreateTrainCardStack().getRandomCard());
    }

    @Test
    void takingLastCardRemovesKeyFromStack() {
        var stack = new CardStack();
        var type = CardType.CART_BLACK;
        stack.addCard(type);
        assertFalse(stack.isEmpty());
        assertDoesNotThrow(() -> stack.getCard(type));
        assertTrue(stack.isEmpty());
        stack.addCard(type);
        assertFalse(stack.isEmpty());
        assertNotNull(stack.getRandomCard());
        assertTrue(stack.isEmpty());
    }

    @Test
    void returnsTrueWhenRequestingZeroOfAnyTypeOnEmptyMap() {
        assertTrue(new CardStack().containsCards(CardType.CART_ANY, 0));
    }

    @Test
    void returnsFalseWhenRequestingMoreThanExists() {
        assertFalse(CardStack.CreateTrainCardStack().containsCards(CardType.CART_BLUE, 1337));
    }

    @Test
    void addCardWithCardAddsOneCard() throws Exception {
        var type = CardType.CART_BLACK;
        var stack = new CardStack();
        stack.addCard(new Card(type));
        assertFalse(stack.isEmpty());
        assertEquals(stack.getCard(type).getCardType(), type);
    }

    @Test
    void canAddMultipleCardsOfSameType() {
        var stack = new CardStack();
        assertTrue(stack.isEmpty());
        stack.addCard(CardType.CART_BLACK);
        stack.addCard(CardType.CART_BLACK);
        assertTrue(stack.containsCards(CardType.CART_BLACK, 2));
    }

    @Test
    void returnsTrueWhenContainsCards() {
        var stack = CardStack.CreateTrainCardStack();
        assertTrue(stack.containsCards(new Card(CardType.CART_BLACK), new Card(CardType.CART_BLUE)));
    }

    @Test
    void returnsFalseWhenNotEnoughCards() {
        var stack = new CardStack();
        assertFalse(stack.containsCards(new Card(CardType.CART_BLACK), new Card(CardType.CART_BLACK)));
    }

    @Test
    void toStringDoesNotThrow() {
        assertDoesNotThrow(() -> CardStack.CreateTrainCardStack().toString());
    }
}