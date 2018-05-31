package client.ui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author Thom
 * @Version 2.0
 * @Since 30-5-2018
 */
public class StartupController implements Initializable {

    public Pane rootPane;
    public Pane MainMenuPane;
    public Pane allPanes;

    @FXML
    private MainMenuController MainMenuPaneController;
    @FXML
    private PreferencesController preferencesPaneController;

    public void initialize(URL url, ResourceBundle bundle) {
        MainMenuPaneController.VBoxPlayController.createLobby.setOnMouseClicked(e -> switchMenuCreate());
        MainMenuPaneController.VBoxPlayController.joinLobby.setOnMouseClicked(e -> switchMenuJoin());
        MainMenuPaneController.VBoxLoadController.loadLevelLabel.setOnMouseClicked(e -> openLoadMenu());
        preferencesPaneController.backButton.setOnMouseClicked(e -> moveMenuRight());

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
            System.out.println("Exception found: " + e.toString());
        }
        preferencesPaneController.buttons.getChildren().remove(preferencesPaneController.joinButton);
        moveMenuLeft();
    }

    private void switchMenuJoin() {
        try {
            preferencesPaneController.buttons.getChildren().add(preferencesPaneController.joinButton);
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
        preferencesPaneController.rootPane.getChildren().removeAll(preferencesPaneController.createButton, preferencesPaneController.joinButton);
        TranslateTransition menuAni = new TranslateTransition(Duration.seconds(1), allPanes);
        menuAni.setToX(0);
        menuAni.play();
        menuAni.setOnFinished(e -> MainMenuPane.setDisable(false));
    }

    public void openLoadMenu() {
        System.out.println("Trying to load game");
    }

    public PreferencesController getPreferenceController() {
        return preferencesPaneController;
    }

}
