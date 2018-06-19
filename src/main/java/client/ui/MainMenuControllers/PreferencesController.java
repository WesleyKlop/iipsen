package client.ui.MainMenuControllers;

import client.ui.views.ColorPreferenceView;
import game.GameStoreProvider;
import game.actions.AddPlayerAction;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;

public class PreferencesController {
    private static final Logger Log = LogManager.getLogger(PreferencesController.class);

    public Pane rootPane;
    public TextField nameField, ipInput;
    public Label createButton, joinButton, backButton, nameLabel, ipLabel;
    public HBox buttons;
    public VBox ipBox;
    @FXML
    private ColorPreferenceView colorPreferenceController;

    @FXML
    public void initialize() {
        // Remove both join and create buttons to prevent an exception
        buttons.getChildren().removeAll(createButton, joinButton);
        ipBox.getChildren().removeAll(ipInput, ipLabel);
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
        for (Player player : GameStoreProvider.getStore().getPlayers()) { // <--- FIXME dit werkt niet
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
