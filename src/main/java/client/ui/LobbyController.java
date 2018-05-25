package client.ui;

import game.GameStoreProvider;
import game.routecards.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
            var children = container.getChildren();
            children.remove(0, children.size());
            for (Player player : gameState.getPlayers()) {
                container.getChildren().add(new Label(player.getPlayerName()));
            }
        });
    }
}
