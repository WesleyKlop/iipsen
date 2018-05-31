package client.ui;

import game.GameState;
import game.GameStoreProvider;
import game.actions.ChangeStateAction;
import game.routecards.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.rmi.RemoteException;

import static javafx.geometry.Pos.CENTER;

/**
 * @author wesley
 */
public class LobbyController {
    @FXML
    public VBox container;
    public Label startButtonLabel;
    public Label quitButtonLabel;

    private MainMenuController mainMenuController = new MainMenuController();
    @FXML
    public void initialize() {
        style(startButtonLabel);
        style(quitButtonLabel);
        GameStoreProvider.getInstance().subscribe(gameState -> {
            System.out.println("List changed");
            // TODO: This could be optimized
            // Maybe compare array size and add/remove based on that
            Platform.runLater(() -> {
                var children = this.container.getChildren();
                children.remove(0, children.size());
                for (Player player : gameState.getPlayers()) {
                    Label playerLabel = new Label(player.getPlayerName());
                    playerLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25));
                    playerLabel.setTextFill(player.getColorAsColor());
                    playerLabel.setStyle("-fx-background-color:" + contrastCalculator(player.getColorAsColor()) + ";");
                    playerLabel.setPrefWidth(500);
                    playerLabel.setAlignment(CENTER);
                    playerLabel.setPadding(new Insets(5, 10, 5, 10));
                    container.getChildren().add(playerLabel);
                }
            });
        });
    }

    public void onStartButtonClicked(ActionEvent actionEvent) throws RemoteException {
        var action = new ChangeStateAction(GameState.GAME);
        System.out.println("Changing to GameState.GAME");
        GameStoreProvider.sendAction(action);
    }

    public void entered(MouseEvent mouseEvent) {
        mainMenuController.hoverEnter(mouseEvent);
    }

    public void exited(MouseEvent mouseEvent) {
        mainMenuController.hoverExit(mouseEvent);
    }

    public void style(Label label) {
        mainMenuController.style(label);
    }

    public String contrastCalculator(Color color) {
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
}
