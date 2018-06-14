package client.ui;

import game.cards.CardType;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class LayoutBankController {

    @FXML
    private ImageView stack, card0, card1, card2, card3, card4;
    @FXML
    private Pane rootPane;

    public void initialize() {

    }

    public void setCardImages(CardType type, int index) {
        ImageView target = (ImageView) rootPane.getChildren().get(index + 1);

    }
}
