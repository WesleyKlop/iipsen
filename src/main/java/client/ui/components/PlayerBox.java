package client.ui.components;

import game.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Wesley Klop
 */
public class PlayerBox extends VBox {

    @FXML
    private Text playerId;
    @FXML
    private Text playerName;
    @FXML
    private Text playerScore;
    @FXML
    private Text playerTrainCarts;

    private Player player;

    public PlayerBox(Player player) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/component_player_box.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        this.player = player;

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
        setBackground(new Background(new BackgroundFill(player.getColorAsColor(), CornerRadii.EMPTY, Insets.EMPTY)));

        playerId.setText(String.format("player %d", player.getId()));
        playerName.setText(player.getPlayerName());
        playerScore.setText(String.format("score: %d", player.getScore()));
        playerTrainCarts.setText(String.format("%dx", player.getTraincarts()));

    }

    public void update(Player player) {
        this.player = player;
        playerScore.setText(String.format("score: %d", player.getScore()));
        playerTrainCarts.setText(String.format("%dx", player.getTraincarts()));
    }
}
