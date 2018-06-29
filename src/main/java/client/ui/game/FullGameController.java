package client.ui.game;

import client.ui.components.PlayerRouteCard;
import client.ui.controllers.LayoutCardController;
import client.ui.dialogs.MessagesController;
import client.ui.dialogs.MessagesControllerProvider;
import client.util.UserPreferences;
import client.util.UserPreferences.PreferencesContainer;
import game.GameStoreProvider;
import game.location.ELocation;
import game.routecards.RouteCard;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Thom
 */
public class FullGameController implements UserPreferences.PreferencesListener {

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
    private static final Logger Log = LogManager.getLogger(FullGameController.class);
    @FXML
    private LayoutGameHandRouteCardsController routeHandController;
    private boolean colorBlind = UserPreferences.isColorBlind();
    @FXML
    private Pane initRouteCards;

    public void initialize() {
        UserPreferences.addObserver(this);
        MessagesControllerProvider.setMessageController(messagesController);
        pauseMenuController.resumeLabel.setOnMouseClicked(e -> closePauseMenu());
        var screenInfo = Screen.getPrimary().getVisualBounds();
        iv1.setImage(image);
        iv1.setLayoutX((screenInfo.getWidth() / 2) - (image.getWidth() / 2));
        iv1.setLayoutY((screenInfo.getHeight() / 2) - (image.getHeight() / 2));
        routeHandController.setOnRouteCardHoverEnter(this::onRouteCardHover);
        routeHandController.setOnRouteCardHoverExit(this::onRouteCardExit);
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

    /**
     * Opens the score tabel for route lengths
     */
    public void ScoreEntered() {
        rootPane.getChildren().addAll(iv1);
    }

    /**
     * Closes the score tabel for route lengths
     */
    public void ScoreExited() {
        rootPane.getChildren().removeAll(iv1);
    }

    /**
     * Updates the gamescreen when someone changed his colorblind settings.
     *
     * @param preferences The new user Preferences.
     */
    @Override
    public void onUpdate(PreferencesContainer preferences) {
        if (colorBlind != preferences.isColorBlind()) {
            colorBlind = preferences.isColorBlind();
            handController.switchColorBlind(GameStoreProvider.getPlayer());
            bankController.updateCardImages(GameStoreProvider.getStore());
            routesMapController.switchColorBlind(colorBlind);
        }
    }

    /**
     * Gives a glow to the hovered route
     *
     * @param mE The mouse event from the hover
     */
    public void onRouteCardHover(MouseEvent mE) {
        PlayerRouteCard source = (PlayerRouteCard) mE.getSource();
        RouteCard card = source.getRouteCard();
        ELocation loc1 = card.getStart();
        ELocation loc2 = card.getEnd();
        routesMapController.showLocations(loc1, loc2);
    }

    /**
     * Takes the glow from the unhovered route
     *
     * @param mE The mouse event from the unhover
     */
    public void onRouteCardExit(MouseEvent mE) {
        PlayerRouteCard source = (PlayerRouteCard) mE.getSource();
        RouteCard card = source.getRouteCard();
        ELocation loc1 = card.getStart();
        ELocation loc2 = card.getEnd();
        routesMapController.unShowLocations(loc1, loc2);
    }
}
