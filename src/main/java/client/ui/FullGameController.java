package client.ui;

import client.ui.views.LayoutGamePlayerbox;
import game.GameStore;
import game.GameStoreProvider;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.List;

public class FullGameController {

    GameStore gameStore;
    @FXML
    private Pane routesMap, rootPane;
    @FXML
    private LayoutGamePlayerbox playerBoxController;
    private List<Player> players;

    public void initialize() {
        players = GameStoreProvider.getStore().getPlayers();
        playerBoxController.setPlayers(players);
    }
}
