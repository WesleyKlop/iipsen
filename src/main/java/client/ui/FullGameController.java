package client.ui;

import client.ui.dialogs.DialogContainer;
import client.ui.views.LayoutGamePlayerbox;
import game.GameStore;
import game.GameStoreProvider;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.List;

public class FullGameController implements DialogContainer {

    GameStore gameStore;
    @FXML
    private Pane routesMap, rootPane, dialogHolder;
    @FXML
    private LayoutGamePlayerbox playerBoxController;
    private List<Player> players;
    @FXML
    private GameRoutesMapController routesMapController;

    public void initialize() {
        players = GameStoreProvider.getStore().getPlayers();
        playerBoxController.setPlayers(players);
        routesMapController.setDialogContainer(this);
    }

//    public void resetMessages() {
//        routeCostsController.closeAnimation();
//    }

    @Override
    public void showDialog(Node dialog) {
        clearDialog();
        dialogHolder.getChildren().add(dialog);
    }

    @Override
    public void clearDialog() {
        while (dialogHolder.getChildren().size() > 0) {
            dialogHolder.getChildren().remove(0);
        }
    }
}
