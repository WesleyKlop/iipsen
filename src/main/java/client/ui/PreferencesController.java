package client.ui;

import game.GameStoreProvider;
import game.actions.AddPlayerAction;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;

public class PreferencesController {
    private static final Logger Log = LogManager.getLogger(PreferencesController.class);

    @FXML
    private TextField nameField;
    @FXML
    private ColorPreferenceController colorPreferenceController;

    public void submitPreferences() throws RemoteException {
        Log.debug("Got prefs");
        String name = nameField.getText();
        Color color = colorPreferenceController.getSelectedColor();

        var action = new AddPlayerAction(name, color);
        GameStoreProvider.sendAction(action);
    }
}
