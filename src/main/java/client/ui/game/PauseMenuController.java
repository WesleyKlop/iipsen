package client.ui.game;


import client.util.GameSaver;
import game.GameStoreProvider;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PauseMenuController {
    private static final Logger Log = LogManager.getLogger(PauseMenuController.class);

    @FXML
    public Label resumeLabel;
    @FXML
    private Label saveLabel;
    @FXML
    private VBox labels, optionsMenu, rulesMenu, saveMenu;
    @FXML
    private StackPane menus;

    @FXML

    public void initialize() {
        for (int i = 0; i < menus.getChildren().size(); i++) {
            menus.getChildren().get(i).setDisable(true);
        }

        saveLabel.setOnMouseClicked(this::onSaveClicked);
    }

    private void onSaveClicked(MouseEvent mouseEvent) {
        Log.info("Saving game...");
        GameSaver saver = new GameSaver(GameStoreProvider.getStore());
        saver.saveGame();
        Log.info("Game saved!");
        quitGame();
    }

    @FXML
    private void menuOpeningSequence(MouseEvent mE) {
        //Get selected menu from label
        //Get opened menu
        //Close opened menu
        //Open menu from step 1
        VBox menuToOpen = getMenuFromLabel(mE);
        VBox menuToClose = getOpenedMenu();
        if (menuToClose != null) {
            closeMenu(menuToClose);
        }
        if (menuToOpen != null) {
            openMenu(menuToOpen);
        }
    }

    private VBox getMenuFromLabel(MouseEvent mE) {
        Label source = (Label) mE.getSource();
        String id = source.getId();
        switch (id) {
            case "optionsLabel":
                return optionsMenu;
            case "rulesLabel":
                return rulesMenu;
            case "saveLabel":
                return saveMenu;
            default:
                return null;
        }
    }

    private VBox getOpenedMenu() {
        for (int i = 0; i < menus.getChildren().size(); i++) {
            if (!menus.getChildren().get(i).isDisable()) {
                return (VBox) menus.getChildren().get(i);
            }
        }
        return null;
    }

    private void openMenu(VBox menu) {
        menu.setDisable(false);
        TranslateTransition transAni = new TranslateTransition(Duration.millis(300), menu);
        transAni.setToY(-1080);
        transAni.play();
    }

    private void closeMenu(VBox menu) {
        menu.setDisable(true);
        TranslateTransition transAni = new TranslateTransition(Duration.millis(300), menu);
        transAni.setToY(0);
        transAni.play();
    }


    public void quitGame() {
        System.exit(0);
    }

}
