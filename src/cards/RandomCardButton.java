package cards;

/**
 * RandomCardButton overrides the set/get card methods with a custom implementation
 * that's used for getting random cards.
 *
 * @author Wesley
 * @see CardButton
 */
public class RandomCardButton extends CardButton {
    private CardStack stack;

    /**
     * Creates a RandomCardButton
     *
     * @param stack the stack to pull cards from
     */
    public RandomCardButton(CardStack stack) {
        this.setText("Random card");
        this.stack = stack;
        super.setOnAction((OnCardSelectListener) e -> listener.onCardSelected(this));
    }

    /**
     * Pulls a random card from the stack
     *
     * @return a random Card
     */
    public Card getCard() {
        return stack.getRandomCard();
    }

    /**
     * Overrides the super method to block setting the card.
     * This method should never be called!
     *
     * @param card the card to use
     * @throws RuntimeException this method should never be called!
     * @see CardButton#setCard(Card)
     */
    public void setCard(Card card) throws RuntimeException {
        throw new RuntimeException("Should not set card on a RandomCardButton");
    }

}
