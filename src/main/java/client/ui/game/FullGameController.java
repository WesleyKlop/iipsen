package client.ui.game;

import client.ui.controllers.LayoutCardController;
import client.ui.dialogs.MessagesController;
import client.ui.dialogs.MessagesControllerProvider;
import client.util.UserPreferences;
import client.util.UserPreferences.PreferencesContainer;
import game.GameStore;
import game.GameStoreProvider;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;
import util.Observer;

public class FullGameController implements Observer<GameStore>, UserPreferences.PreferencesListener {

    private Image image = new Image("/images/points.png");
    private ImageView iv1 = new ImageView();
    @FXML
    private Pane routesMap, rootPane, pauseMenu;
    @FXML
    private PauseMenuController pauseMenuController;
    @FXML
    private MessagesController messagesController;
    @FXML
    private LayoutBankController bankController;
    @FXML
    private LayoutCardController handController;
    @FXML
    private GameRoutesMapController routesMapController;
    @FXML
    private Pane initRouteCards;
    private boolean colorBlind = UserPreferences.isColorBlind();


    public void initialize() {
        UserPreferences.addObserver(this);
        GameStoreProvider.getInstance().addObserver(this);
        MessagesControllerProvider.setMessageController(messagesController);
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
        rootPane.getChildren().addAll(iv1);
    }


    public void ScoreExited() {
        rootPane.getChildren().removeAll(iv1);
    }

    @Override
    public void onUpdate(PreferencesContainer value) {
        if (colorBlind != value.isColorBlind()) {
            colorBlind = value.isColorBlind();
            handController.switchColorBlind(GameStoreProvider.getPlayer());
            bankController.updateCardImages(GameStoreProvider.getStore());
            routesMapController.switchColorBlind(colorBlind);
        }
    }

    @Override
    public void onUpdate(GameStore value) {

    }
}
