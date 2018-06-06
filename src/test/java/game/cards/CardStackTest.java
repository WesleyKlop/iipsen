package game.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wesley
 */
class CardStackTest {


    @Test
    void newCardStackIsEmpty() {
        assertTrue(new CardStack().isEmpty());
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
    void takingLastCardRemovesKeyFromStack() {
        var stack = new CardStack();
        var type = CardType.CART_BLACK;
        stack.addCard(type);
        assertFalse(stack.isEmpty());
        assertDoesNotThrow(() -> stack.getCard(type));
        assertTrue(stack.isEmpty());
    }

    @Test
    void returnsTrueWhenRequestingZeroOfAnyTypeOnEmptyMap() {
        assertTrue(new CardStack().containsCards(CardType.CART_ANY, 0));
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
        var stack = new CardStack();
        stack.addCard(CardType.CART_BLACK);
        stack.addCard(CardType.CART_BLUE);
        assertTrue(stack.containsCards(new Card(CardType.CART_BLACK), new Card(CardType.CART_BLUE)));
    }

    @Test
    void returnsFalseWhenNotEnoughCards() {
        assertFalse(new CardStack().containsCards(new Card(CardType.CART_BLACK), new Card(CardType.CART_BLACK)));
    }

    @Test
    void toStringDoesNotThrow() {
        assertDoesNotThrow(() -> new CardStack().toString());
    }
}