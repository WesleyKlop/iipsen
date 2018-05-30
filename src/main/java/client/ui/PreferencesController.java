package client.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PreferencesController {

    @FXML
    private TextField nameField;
    @FXML
    private ColorPreferenceController colorPreferenceController;
    public Button submitButton;

    public void submitPreferences() {
        System.out.println("Got prefs");
        String name = nameField.getText();
        Color color = colorPreferenceController.getSelectedColor();

        System.out.println("Name: " + name + "\rColor: " + color);

        //var action = new AddPlayerAction(name, color);
        //GameStoreProvider.sendAction(action);
    }
}
