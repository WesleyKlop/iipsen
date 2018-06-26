package client.ui.mainmenu;

import client.ui.views.LobbyController;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 */
public class StartupController {

    private static final Logger Log = LogManager.getLogger(StartupController.class);

    public Pane rootPane, MainMenuPane, allPanes;
    public VBox optionsLobby, rulesLobby;

    @FXML
    private MainMenuController MainMenuPaneController;
    @FXML
    private PreferencesController preferencesPaneController;
    @FXML
    private LobbyController lobbyPaneController;

    @FXML
    public void initialize() {
        MainMenuPaneController.getPlayController().createLobby.setOnMouseClicked(e -> switchMenuCreate());
        MainMenuPaneController.getPlayController().joinLobby.setOnMouseClicked(e -> switchMenuJoin());
        preferencesPaneController.backButton.setOnMouseClicked(e -> moveMenuRight());
        lobbyPaneController.quitButtonLabel.setOnMouseClicked(e -> moveMenuUp());
    }

    public void quitGame() {
        System.exit(0);
    }

    private void switchMenuCreate() {
        try {
            if (!preferencesPaneController.buttons.getChildren().contains(preferencesPaneController.createButton))
                preferencesPaneController.buttons.getChildren().add(preferencesPaneController.createButton);
        } catch (Exception e) {
            Log.error("Exception found: ", e);
        }
        preferencesPaneController.ipBox.getChildren().removeAll(preferencesPaneController.ipLabel, preferencesPaneController.ipInput);
        preferencesPaneController.buttons.getChildren().remove(preferencesPaneController.joinButton);
        moveMenuLeft();
    }

    private void switchMenuJoin() {
        try {
            if (!preferencesPaneController.buttons.getChildren().contains(preferencesPaneController.joinButton))
                preferencesPaneController.buttons.getChildren().add(preferencesPaneController.joinButton);

            var ipBoxChildren = preferencesPaneController.ipBox.getChildren();
            if (!ipBoxChildren.contains(preferencesPaneController.ipLabel)
                || !ipBoxChildren.contains(preferencesPaneController.ipInput))
                ipBoxChildren.addAll(preferencesPaneController.ipLabel, preferencesPaneController.ipInput);
        } catch (Exception e) {
            Log.error("Exception found: ", e);
        }
        preferencesPaneController.buttons.getChildren().remove(preferencesPaneController.createButton);
        moveMenuLeft();
    }

    private void moveMenuLeft() {
        TranslateTransition menuAni = new TranslateTransition(Duration.seconds(1), allPanes);
        menuAni.setToX(-1920);
        menuAni.play();
        menuAni.setOnFinished(e -> MainMenuPane.setDisable(true));
    }

    private void moveMenuRight() {
        preferencesPaneController.resetPrefs();
        preferencesPaneController.ipBox.getChildren().removeAll(preferencesPaneController.createButton, preferencesPaneController.joinButton);
        TranslateTransition menuAni = new TranslateTransition(Duration.seconds(1), allPanes);
        menuAni.setToX(0);
        menuAni.play();
        menuAni.setOnFinished(e -> MainMenuPane.setDisable(false));
    }

    public void moveMenuDown() {
        TranslateTransition menuAni = new TranslateTransition(Duration.seconds(1), rootPane);
        menuAni.setToY(-1080);
        menuAni.play();
    }

    private void moveMenuUp() {
        TranslateTransition menuAni = new TranslateTransition(Duration.seconds(1), rootPane);
        menuAni.setToY(0);
        menuAni.play();
        closeMenuVertical(optionsLobby);
        closeMenuVertical(rulesLobby);
    }

    public PreferencesController getPreferenceController() {
        return preferencesPaneController;
    }

    public MainMenuLoadController getLoadMenuController() {
        return MainMenuPaneController.getLoadController();
    }

    public void openRulesVertical() {
        if (rulesLobby.isDisable()) {
            closeMenuVertical(optionsLobby);
            openMenuVertical(rulesLobby);
        } else {
            closeMenuVertical(rulesLobby);
        }
    }

    public void openOptionsVertical() {
        if (optionsLobby.isDisable()) {
            closeMenuVertical(rulesLobby);
            openMenuVertical(optionsLobby);
        } else {
            closeMenuVertical(optionsLobby);
        }
    }

    private void closeMenuVertical(VBox menu) {
        menu.setDisable(true);
        TranslateTransition verticalAni = new TranslateTransition(Duration.millis(450), menu);
        verticalAni.setToX(0);
        verticalAni.play();
    }

    private void openMenuVertical(VBox menu) {
        menu.setDisable(false);
        TranslateTransition verticalAni = new TranslateTransition(Duration.millis(450), menu);
        verticalAni.setToX(-900);
        verticalAni.play();
    }

}
