package client.ui;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuPlayController implements Initializable {

    public Label createLobby;
    public Label joinLobby;

    private MainMenuController mainMenuController = new MainMenuController();

    public void initialize(URL url, ResourceBundle bundle) {

    }

    public void hoverEnter(MouseEvent mouseEvent) {
        mainMenuController.hoverEnter(mouseEvent);
    }

    public void hoverExit(MouseEvent mouseEvent) {
        mainMenuController.hoverExit(mouseEvent);
    }


}
