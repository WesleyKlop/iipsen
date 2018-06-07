package client.ui;

import client.MediaController;
import client.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

/**
 * View for managing settings
 */
public class MainMenuOptionController {
    private final UserPreferences prefs = new UserPreferences();

    @FXML
    private Slider optionVolumeMusicSlider;
    @FXML
    private Slider optionVolumeFXSlider;
    @FXML
    private CheckBox optionMute;
    @FXML
    private CheckBox optionColorblind;


    @FXML
    public void initialize() {
        // Set UI to correct values
        optionVolumeMusicSlider.setValue(prefs.getMusicVolume());
        optionVolumeFXSlider.setValue(prefs.getFxVolume());
        optionMute.setSelected(prefs.isSoundMuted());
        optionColorblind.setSelected(prefs.isColorBlind());
        optionVolumeMusicSlider.setDisable(prefs.isSoundMuted());

        optionMute.selectedProperty().addListener((observable, oldValue, newValue) -> {
            prefs.setSoundMuted(newValue);
            MediaController.getMusicPlayer().setMute(newValue);
            optionVolumeMusicSlider.setDisable(newValue);
        });

        optionColorblind.selectedProperty().addListener((observable, oldValue, newValue) -> {
            prefs.setColorblind(newValue);
        });

        optionVolumeFXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            prefs.setFxVolume(newValue.doubleValue());
        });

        optionVolumeMusicSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            prefs.setMusicVolume(newValue.doubleValue());
            MediaController.getMusicPlayer().setVolume(newValue.doubleValue());
        });
    }
}
