package game.cards;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CardsViewApp extends Application implements OnCardSelectListener {
    private CardStack stack;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cards app!!");
        this.stack = CardStack.CreateTrainCardStack();

        CardsPane root = new CardsPane(stack, this);
        primaryStage.setScene(new Scene(root, 500, 1000));
        primaryStage.show();
    }

    /**
     * Handles button clicks
     *
     * @param caller the button that called it
     */
    @Override
    public void onCardSelected(CardButton caller) {
        System.out.println(caller.getCard());
        if (!(caller instanceof RandomCardButton)) {
            Card newCard = stack.getRandomCard();
            caller.setCard(newCard);
        }
    }
}
