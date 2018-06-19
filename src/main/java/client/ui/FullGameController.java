package client.ui;

import client.ui.dialogs.DialogContainer;
import client.ui.views.LayoutGamePlayerbox;
import game.GameStore;
import game.GameStoreProvider;
import game.player.Player;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.util.List;

public class FullGameController implements DialogContainer {

    GameStore gameStore;
    private Image image = new Image("/images/points.png");
    @FXML
    private Pane routesMap, rootPane, dialogHolder, pauseMenu;
    @FXML
    private LayoutGamePlayerbox playerBoxController;
    private List<Player> players;
    private ImageView iv1 = new ImageView();
    @FXML
    private GameRoutesMapController routesMapController;
    @FXML
    private pauseMenuController pauseMenuController;

    public void initialize() {
        players = GameStoreProvider.getStore().getPlayers();
        playerBoxController.setPlayers(players);
        pauseMenuController.resumeLabel.setOnMouseClicked(e -> closePauseMenu());
        var screenInfo = Screen.getPrimary().getVisualBounds();
        iv1.setImage(image);
        iv1.setLayoutX((screenInfo.getWidth() / 2) - (image.getWidth() / 2));
        iv1.setLayoutY((screenInfo.getHeight() / 2) - (image.getHeight() / 2));
    }

    @FXML
    private void openPauseMenu() {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(500), pauseMenu);
        transAni.setToX(-1920);
        transAni.play();
    }

    @FXML
    private void closePauseMenu() {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(500), pauseMenu);
        transAni.setToX(0);
        transAni.play();
    }


    public void ScoreEntered() {
        iv1.setFitHeight(170);
        iv1.setFitWidth(300);
        rootPane.getChildren().addAll(iv1);
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

    public void ScoreExited() {
        rootPane.getChildren().removeAll(iv1);
    }
}
