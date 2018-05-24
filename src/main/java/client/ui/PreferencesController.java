package client.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PreferencesController implements ColorPreferenceListener {

    // Declare Circles
    @FXML
    public Button submitPreferencesButton;
    @FXML
    public TextField nameField;
    @FXML
    public HBox colorPreference;
    @FXML
    public ColorPreferenceController colorPreferenceController;

    public void submitName() {
        submitPreferencesButton.setText(nameField.getText());
    }

    @FXML
    public void initialize() {
        System.out.println("init");
        colorPreferenceController.setListener(this);
    }

    @Override
    public void onColorSelected(Color color) {
        System.out.println("Controller received color: " + color);
    }
}
