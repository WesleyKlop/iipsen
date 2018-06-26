package client.ui.views;

import game.GameState;
import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.AttachPlayerAction;
import game.actions.ChangeStateAction;
import game.player.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Observer;

import java.rmi.RemoteException;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

/**
 * Shows the
 */
public class LobbyController implements Observer<GameStore> {

    private static final Logger Log = LogManager.getLogger(LobbyController.class);
    @FXML
    public Label startButtonLabel, quitButtonLabel;
    @FXML
    private VBox container;
    @FXML
    private Label ipAddress;

    @FXML
    public void initialize() {
        GameStoreProvider.getInstance().addObserver(this);
    }

    private void updateView(List<Player> players) {
        var children = this.container.getChildren();
        children.remove(0, children.size());
        for (final Player player : players) {
            Label playerLabel = new Label(player.getPlayerName());
            playerLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25));
            playerLabel.setTextFill(player.getColorAsColor());
            playerLabel.setStyle("-fx-background-color:" + contrastCalculator(player.getColorAsColor()) + ";");
            playerLabel.setPrefWidth(500);
            playerLabel.setAlignment(CENTER);
            playerLabel.setPadding(new Insets(5, 10, 5, 10));
            playerLabel.setOnMouseClicked(e -> this.onPlayerLabelClicked(player));
            container.getChildren().add(playerLabel);
        }
    }

    private void onPlayerLabelClicked(final Player player) {
        Action action = new AttachPlayerAction(GameStoreProvider.getClient(), player.getId());
        try {
            GameStoreProvider.sendAction(action);
        } catch (RemoteException e) {
            Log.error("Failed to attach player to client :(", e);
        }
    }

    public void onStartButtonClicked(MouseEvent mouseEvent) throws RemoteException {
        GameStoreProvider.getInstance().removeObserver(this);
        Log.debug("Starting game");
        var action = new ChangeStateAction(GameState.GAME);
        Log.debug("Changing to GameState.GAME");
        GameStoreProvider.sendAction(action);
    }

    private String contrastCalculator(Color color) {
        double brightness = color.getBrightness();
        double hue = color.getHue();
        double saturation = color.getSaturation();

        brightness = (brightness < 0.5) ? brightness + 0.5 : brightness - 0.5;

        color = Color.hsb(hue, saturation, brightness);

        return String.format("#%02X%02X%02X",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255));
    }

    private void setIpAddress(String address) {
        ipAddress.setText(address);
    }

    @Override
    public void onUpdate(final GameStore value) {
        Platform.runLater(() -> {
            updateView(value.getPlayerController().getPlayers());
            setIpAddress(value.getServerIp());
        });
    }
}
