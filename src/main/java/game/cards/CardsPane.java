package game.cards;

import javafx.scene.layout.VBox;

/**
 * UI pane for holding (Random)CardButtons
 *
 * @author Wesley Klop <wesley19097@gmail.com>
 */
public class CardsPane extends VBox {

    public CardsPane(CardStack stack, OnCardSelectListener listener) {
        RandomCardButton randomCardButton = new RandomCardButton(stack);
        randomCardButton.setOnAction(listener);

        CardButton[] cardButtons = new CardButton[5];
        for (int i = 0, cardsLength = cardButtons.length; i < cardsLength; i++) {
            cardButtons[i] = new CardButton(stack.getRandomCard());
            cardButtons[i].setOnAction(listener);
        }

        this.getChildren().add(randomCardButton);
        this.getChildren().addAll(cardButtons);
    }
}
