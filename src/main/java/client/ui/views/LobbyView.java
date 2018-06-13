package client.ui.views;

import client.ui.controllers.LobbyController;
import game.player.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.rmi.RemoteException;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

/**
 * Shows the
 */
public class LobbyView {
    @FXML
    public VBox container;
    public Label startButtonLabel, quitButtonLabel;

    private LobbyController controller = new LobbyController(this);

    @FXML
    public void initialize() {
    }

    public void updateView(List<Player> players) {
        Platform.runLater(() -> {
            var children = this.container.getChildren();
            children.remove(0, children.size());
            for (Player player : players) {
                Label playerLabel = new Label(player.getPlayerName());
                playerLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25));
                playerLabel.setTextFill(player.getColorAsColor());
                playerLabel.setStyle("-fx-background-color:" + controller.contrastCalculator(player.getColorAsColor()) + ";");
                playerLabel.setPrefWidth(500);
                playerLabel.setAlignment(CENTER);
                playerLabel.setPadding(new Insets(5, 10, 5, 10));
                container.getChildren().add(playerLabel);
            }
        });
    }

    public void onStartButtonClicked(MouseEvent mouseEvent) throws RemoteException {
        controller.onStartButtonClicked();
    }



}
