package client.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PreferencesController {

    // Declare Circles
    @FXML
    private Button submitPreferencesButton;
    @FXML
    private TextField nameField;
    @FXML
    private ColorPreferenceController colorPreferenceController;

    public void submitPreferences() {
        String name = nameField.getText();
        Color color = colorPreferenceController.getSelectedColor();

        System.out.printf("Name: %s%nColor: %s%n", name, color);
    }
}
