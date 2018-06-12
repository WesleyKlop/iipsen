package client.ui.MainMenuControllers;

import client.ui.views.ColorPreferenceView;
import game.GameStoreProvider;
import game.actions.AddPlayerAction;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PreferencesController implements Initializable {
    private static final Logger Log = LogManager.getLogger(PreferencesController.class);

    public Pane rootPane;
    public TextField nameField, ipInput;
    public Label createButton, joinButton, backButton, nameLabel, ipLabel;
    public HBox buttons;
    public VBox ipBox;
    @FXML
    private ColorPreferenceView colorPreferenceController;
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

    public void submitPreferences() throws RemoteException {
        Log.debug("Got prefs");
        String name = nameField.getText();
        Color color = colorPreferenceController.getSelectedColor();
        var action = new AddPlayerAction(name, color);
        GameStoreProvider.sendAction(action);
    }

    public boolean checkName() {
        String name = nameField.getText().toLowerCase();
        String permitted = "abcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < name.length(); i++) {
            for (int j = 0; j < permitted.length(); j++) {
                if (name.charAt(i) == permitted.charAt(j)) {
                    return true;
                }
            }
        }

        nameLabel.setText("Your name must consist of at least 1 letter or number");
        return false;
    }


    public boolean checkNameDouble() {
        String name = nameField.getText();
        for (Player player : GameStoreProvider.getStore().getPlayers()) { // <--- dit werkt niet
            if (name.equalsIgnoreCase(player.getPlayerName())) {
                nameLabel.setText("Name is already taken, please choose another name");
                return false;
            }
        }
        return true;
    }

    public void resetPrefs() {
        nameLabel.setText("Name:");
        nameField.setText("");
        colorPreferenceController.reset();
    }

}
