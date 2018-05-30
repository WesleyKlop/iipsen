package client.ui;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StartupController implements Initializable {

    public Pane MainMenuPane;
    public Pane rootPane;
    public Pane snowPane;

    @FXML
    private MainMenuController MainMenuPaneController;

    public void initialize(URL url, ResourceBundle bundle) {
        var screenInfo = Screen.getPrimary().getVisualBounds();
        double width = screenInfo.getWidth();
        double height = screenInfo.getHeight();
        double scaleWidth = (width / 1920);
        double scaleHeight = (height / 1080);

        MainMenuPane.setScaleX(scaleWidth);
        MainMenuPane.setScaleY(scaleHeight);

        MainMenuPaneController.VBoxPlayController.createLobby.setOnMouseClicked(e -> switchMenuCreate());
        MainMenuPaneController.VBoxPlayController.joinLobby.setOnMouseClicked(e -> switchMenuJoin());
        MainMenuPaneController.VBoxLoadController.loadLevelLabel.setOnMouseClicked(e -> openLoadMenu());
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
        switchMenu();
    }

    private void switchMenuJoin() {
        switchMenu();
    }

    private void switchMenu() {
        if (MainMenuPane.isDisable()) {
            moveMenuRight();
        } else {
            moveMenuLeft();
        }

    }

    private void moveMenuLeft() {
        TranslateTransition menuAni = new TranslateTransition(Duration.seconds(1), MainMenuPane);
        menuAni.setToX(-1920);
        menuAni.play();
        menuAni.setOnFinished(e -> MainMenuPane.setDisable(true));
    }

    private void moveMenuRight() {
        TranslateTransition menuAni = new TranslateTransition(Duration.seconds(1), MainMenuPane);
        menuAni.setToX(0);
        menuAni.play();
        menuAni.setOnFinished(e -> MainMenuPane.setDisable(false));
    }

    private void openLoadMenu() {

    }


}
