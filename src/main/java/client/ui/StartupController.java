package client.ui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 */
public class StartupController implements Initializable {

    private static final Logger logger = LogManager.getLogger(StartupController.class);

    public Pane rootPane, MainMenuPane, allPanes;

    @FXML
    private MainMenuController MainMenuPaneController;
    @FXML
    private PreferencesController preferencesPaneController;
    @FXML
    private LobbyController lobbyPaneController;

    public void initialize(URL url, ResourceBundle bundle) {
        MainMenuPaneController.getPlayController().createLobby.setOnMouseClicked(e -> switchMenuCreate());
        MainMenuPaneController.getPlayController().joinLobby.setOnMouseClicked(e -> switchMenuJoin());
        MainMenuPaneController.getLoadController().loadLevelLabel.setOnMouseClicked(e -> openLoadMenu());
        preferencesPaneController.backButton.setOnMouseClicked(e -> moveMenuRight());
        lobbyPaneController.quitButtonLabel.setOnMouseClicked(e -> moveMenuUp());
        moveMenuLeft();
        moveMenuDown();
    }

    public void widthUpImageView(MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getSource();
        source.setFitWidth(source.getFitWidth() + 14);
        source.setLayoutX(source.getLayoutX() - 7);
        source.setLayoutY(source.getLayoutY() - 7);
    }
    public void widthDownImageView(MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getSource();
        source.setFitWidth(source.getFitWidth() - 14);
        source.setLayoutX(source.getLayoutX() + 7);
        source.setLayoutY(source.getLayoutY() + 7);
    }

    public void quitGame() {
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
        closeMenuVertical(MainMenuPaneController.VBoxOption);
        closeMenuVertical(MainMenuPaneController.VBoxRule);
    }

    private void openLoadMenu() {
        System.out.println("Trying to load game");
    }

    public PreferencesController getPreferenceController() {
        return preferencesPaneController;
    }


    public void openRulesVertical() {
        closeMenuVertical(MainMenuPaneController.VBoxOption);
        openMenuVertical(MainMenuPaneController.VBoxRule);
    }

    public void openOptionsVertical() {
        closeMenuVertical(MainMenuPaneController.VBoxRule);
        openMenuVertical(MainMenuPaneController.VBoxOption);

    }

    private void closeMenuVertical(VBox menu) {
        TranslateTransition verticalAni = new TranslateTransition(Duration.seconds(1), menu);
        verticalAni.setToX(0);
        verticalAni.play();
        menu.setDisable(false);
    }

    private void openMenuVertical(VBox menu) {
        TranslateTransition verticalAni = new TranslateTransition(Duration.seconds(1), menu);
        verticalAni.setToX(1280);
        verticalAni.play();
        menu.setDisable(false);
    }

}
