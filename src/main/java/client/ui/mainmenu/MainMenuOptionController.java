package client.ui.mainmenu;

import client.util.MediaController;
import client.util.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller for managing settings
 *
 * @author Wesley Klop
 */
public class MainMenuOptionController {
    private static final Logger Log = LogManager.getLogger(MainMenuOptionController.class);

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
        var muted = UserPreferences.isSoundMuted();
        optionVolumeMusicSlider.setValue(UserPreferences.getMusicVolume());
        optionVolumeFXSlider.setValue(UserPreferences.getFxVolume());
        optionMute.setSelected(muted);
        optionColorblind.setSelected(UserPreferences.isColorBlind());

        optionVolumeMusicSlider.setDisable(muted);
        optionVolumeFXSlider.setDisable(muted);

        optionMute.selectedProperty().addListener((observable, oldValue, newValue) -> {
            UserPreferences.setSoundMuted(newValue);
            optionVolumeMusicSlider.setDisable(newValue);
            optionVolumeFXSlider.setDisable(newValue);
            MediaController.getMusicPlayer().setMute(newValue);
        });

        optionVolumeMusicSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            UserPreferences.setMusicVolume(newValue.doubleValue());
//            Log.debug("Setting volume from {} to {}", MediaController.getMusicPlayer().getVolume(), newValue.doubleValue());
            MediaController.getMusicPlayer().setVolume(newValue.doubleValue());
        });

        optionColorblind.selectedProperty().addListener((observable, oldValue, newValue) -> {
            UserPreferences.setColorblind(newValue);
        });

        optionVolumeFXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            UserPreferences.setFxVolume(newValue.doubleValue());
        });
    }
}
