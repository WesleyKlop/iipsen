package client.ui;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 */
public class MainMenuLoadController {

    public Label loadLevelLabel;

    private MainMenuController mainMenuController = new MainMenuController();

    public void hoverEnter(MouseEvent mouseEvent) {
        mainMenuController.hoverEnter(mouseEvent);
    }

    public void hoverExit(MouseEvent mouseEvent) {
        mainMenuController.hoverExit(mouseEvent);
    }


}
