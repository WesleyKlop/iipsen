package game.cards;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * RandomCardButton overrides the set/get card methods with a custom implementation
 * that's used for getting random game.cards.
 *
 * @author Wesley
 * @see CardButton
 */
public class RandomCardButton extends CardButton {
    private CardStack stack;

    /**
     * Creates a RandomCardButton
     *
     * @param stack the stack to pull game.cards from
     */
    RandomCardButton(CardStack stack) {
        this.stack = stack;
        super.setOnAction((EventHandler<ActionEvent>) e -> listener.onCardSelected(this));

        Image image = new Image(RandomCardButton.class.getResourceAsStream("/cards/BACK.png"));
        this.setGraphic(new ImageView(image));
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
