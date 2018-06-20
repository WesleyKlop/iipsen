package client.ui.components;

import game.cards.Card;
import game.cards.CardType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author Wesley Klop
 */
public class PlayerCard extends VBox {
    private static final Logger Log = LogManager.getLogger(PlayerCard.class);
    private final Card card;
    private int count;

    @FXML
    private Label amount;
    @FXML
    private ImageView image;

    public PlayerCard(Card card, int count) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/component_player_card.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        this.card = card;
        this.count = count;

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
        var path = card.getPath();
        var stream = getClass().getResourceAsStream(path);
        Log.debug("Image path: {}", path);
        image.setImage(new Image(stream));
        amount.setText(String.valueOf(count));
    }

    public void update(int count) {
        this.count = count;
        amount.setText(String.valueOf(count));
    }

    public CardType getCardType() {
        return card.getCardType();
    }
}
