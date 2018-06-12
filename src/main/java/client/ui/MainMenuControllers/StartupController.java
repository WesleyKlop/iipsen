package client.ui.MainMenuControllers;

import client.ui.views.LobbyView;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.TempException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 */
public class StartupController implements Initializable {

    private static final Logger logger = LogManager.getLogger(StartupController.class);

    public Pane rootPane, MainMenuPane, allPanes;
    public VBox optionsLobby, rulesLobby;

    @FXML
    private MainMenuController MainMenuPaneController;
    @FXML
    private PreferencesController preferencesPaneController;
    @FXML
    private LobbyView lobbyPaneController;

    public void initialize(URL url, ResourceBundle bundle) {
        MainMenuPaneController.getPlayController().createLobby.setOnMouseClicked(e -> switchMenuCreate());
        MainMenuPaneController.getPlayController().joinLobby.setOnMouseClicked(e -> switchMenuJoin());
        MainMenuPaneController.getLoadController().loadLevelLabel.setOnMouseClicked(e -> openLoadMenu());
        preferencesPaneController.backButton.setOnMouseClicked(e -> moveMenuRight());
        lobbyPaneController.quitButtonLabel.setOnMouseClicked(e -> moveMenuUp());
    }

    public void quitGame() {
        try {
            throw new TempException();
        } catch (TempException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private void switchMenuCreate() {
        try {
            preferencesPaneController.buttons.getChildren().add(preferencesPaneController.createButton);
        } catch (Exception e) {
            logger.error("Exception found: " + e.toString());
        }
        preferencesPaneController.ipBox.getChildren().removeAll(preferencesPaneController.ipLabel, preferencesPaneController.IPinput);
        preferencesPaneController.buttons.getChildren().remove(preferencesPaneController.joinButton);
        moveMenuLeft();
    }

    private void switchMenuJoin() {
        try {
            preferencesPaneController.buttons.getChildren().add(preferencesPaneController.joinButton);
            preferencesPaneController.ipBox.getChildren().addAll(preferencesPaneController.ipLabel, preferencesPaneController.IPinput);
        } catch (Exception e) {
            System.out.println("Exception found: " + e.toString());
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
        preferencesPaneController.resetPrefs();
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

    private void openLoadMenu() {
        System.out.println("Trying to load game");
    }

    public PreferencesController getPreferenceController() {
        return preferencesPaneController;
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
