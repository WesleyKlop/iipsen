package client.ui;

import game.GameStoreProvider;
import game.actions.AddPlayerAction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PreferencesController implements Initializable {

    public Pane rootPane;
    public TextField nameField;
    public Label createButton;
    public Label joinButton;
    public Label backButton;
    public Label nameLabel;
    public HBox buttons;
    @FXML
    private ColorPreferenceController colorPreferenceController;
    private MainMenuController mainMenuController = new MainMenuController();

    public void initialize(URL url, ResourceBundle bundle) {
        style(buttons);
    }

    private void style(Label label) {
        mainMenuController.style(label);
    }

    private void style(HBox hbox) {
        mainMenuController.style(hbox);
    }

    public void entered(MouseEvent mouseEvent) {
        mainMenuController.hoverEnter(mouseEvent);
    }

    public void exited(MouseEvent mouseEvent) {
        mainMenuController.hoverExit(mouseEvent);
    }

    public boolean submitPreferences() throws RemoteException {
        System.out.println("Got prefs");
        String name = nameField.getText();
        Color color = colorPreferenceController.getSelectedColor();

        if (checkName(name)) {
            var action = new AddPlayerAction(name, color);
            GameStoreProvider.sendAction(action);
            return true;
        } else {
            nameLabel.setText("Fill in a name");
            return false;
        }
    }

    public boolean checkName(String name) {
        return name.length() > 1;
    }

}
