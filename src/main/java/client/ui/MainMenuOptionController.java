package client.ui;

import client.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * View for managing settings
 */
public class MainMenuOptionController {
    private final UserPreferences prefs = new UserPreferences();

    public MediaPlayer player;
    @FXML
    private Slider optionVolumeMusicSlider;
    @FXML
    private Slider optionVolumeFXSlider;
    @FXML
    private CheckBox optionMute;
    @FXML
    private CheckBox optionColorblind;

    public MainMenuOptionController() {
        Media backgroundMusic = new Media(getClass().getResource("/sound/background.mp3").toString());
        player = new MediaPlayer(backgroundMusic);
        player.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @FXML
    public void initialize() {
        // Set UI to correct values
        optionVolumeMusicSlider.setValue(prefs.getMusicVolume());
        optionVolumeFXSlider.setValue(prefs.getFxVolume());
        optionMute.setSelected(prefs.isSoundMuted());
        optionColorblind.setSelected(prefs.isColorBlind());
        optionVolumeMusicSlider.setDisable(prefs.isSoundMuted());

        // Mute properties on music player
        player.setMute(prefs.isSoundMuted());
        player.setVolume(prefs.getMusicVolume());

        optionMute.selectedProperty().addListener((observable, oldValue, newValue) -> {
            prefs.setSoundMuted(newValue);
            player.setMute(newValue);
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
            player.setVolume(newValue.doubleValue());
        });

        player.play();
    }
}
