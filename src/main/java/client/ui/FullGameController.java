package client.ui;

import client.ui.dialogs.DialogContainer;
import game.GameStore;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.util.List;

public class FullGameController {

    GameStore gameStore;
    private Image image = new Image("/images/points.png");
    @FXML
    private LayoutGamePlayerbox playerBoxController;
    private List<Player> players;
    private ImageView iv1 = new ImageView();
    @FXML
    private Pane routesMap, rootPane, pauseMenu;
    @FXML
    private pauseMenuController pauseMenuController;
    @FXML
    private MessagesController messagesController;

    public void initialize() {
        MessagesControllerProvider.setMessageController(messagesController);
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
    }


    public void ScoreExited() {
        rootPane.getChildren().removeAll(iv1);
    }
}
