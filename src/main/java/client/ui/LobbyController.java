package client.ui;

import game.GameState;
import game.GameStoreProvider;
import game.actions.ChangeStateAction;
import game.routecards.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;

/**
 * @author wesley
 */
public class LobbyController {
    @FXML
    public VBox container;

    @FXML
    public void initialize() {
        GameStoreProvider.getInstance().subscribe(gameState -> {
            System.out.println("List changed");
            // TODO: This could be optimized
            // Maybe compare array size and add/remove based on that
            Platform.runLater(() -> {
                var children = this.container.getChildren();
                children.remove(0, children.size());
                for (Player player : gameState.getPlayers()) {
                    container.getChildren().add(new Label(player.getPlayerName()));
                }
            });
        });
    }

    public void onStartButtonClicked(ActionEvent actionEvent) throws RemoteException {
        var action = new ChangeStateAction(GameState.GAME);
        System.out.println("Changing to GameState.GAME");
        GameStoreProvider.sendAction(action);
    }
}
