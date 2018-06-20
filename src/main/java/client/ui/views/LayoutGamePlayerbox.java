package client.ui.views;

import client.ui.components.PlayerBox;
import game.GameStore;
import game.GameStoreProvider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import util.Observer;

public class LayoutGamePlayerbox implements Observer<GameStore> {

    //    private static final Logger Log = LogManager.getLogger(LayoutGamePlayerbox.class);
    @FXML
    VBox container;
    private PlayerBox[] boxes;

    @FXML
    private void initialize() {
        GameStoreProvider.getInstance().addObserver(this);
        var players = GameStoreProvider.getStore().getPlayers();
        boxes = new PlayerBox[players.size()];
        for (int i = 0; i < players.size(); i++) {
            var player = players.get(i);
            boxes[i] = new PlayerBox(player);
        }
        container.getChildren().addAll(boxes);
    }

    @Override
    public void onUpdate(GameStore value) {
        Platform.runLater(() -> {
            for (int i = 0; i < boxes.length; i++) {
                boxes[i].update(value.getPlayers().get(i));
            }
        });
    }
}
