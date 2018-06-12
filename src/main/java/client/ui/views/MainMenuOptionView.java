package client.ui.views;

import client.ui.controllers.MainMenuOptionController;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

/**
 * View for managing settings
 */
public class MainMenuOptionView {

    @FXML
    private Slider optionVolumeMusicSlider;
    @FXML
    private Slider optionVolumeFXSlider;
    @FXML
    private CheckBox optionMute;
    @FXML
    private CheckBox optionColorblind;

    public void updateState(boolean muted, boolean colorblind, double musicVolume, double fxVolume) {
        optionVolumeMusicSlider.setValue(musicVolume);
        optionVolumeFXSlider.setValue(fxVolume);
        optionMute.setSelected(muted);
        optionColorblind.setSelected(colorblind);

        optionVolumeMusicSlider.setDisable(muted);
        optionVolumeFXSlider.setDisable(muted);
    }

    public void setOnMuteChange(ChangeListener<Boolean> listener) {
        optionMute.selectedProperty().addListener(listener);
    }

    public void setOnColorBlindChange(ChangeListener<Boolean> listener) {
        optionColorblind.selectedProperty().addListener(listener);
    }

    public void setOnFXVolumeChange(ChangeListener<Number> listener) {
        optionVolumeFXSlider.valueProperty().addListener(listener);
    }

    public void setOnMusicVolumeChange(ChangeListener<Number> listener) {
        optionVolumeMusicSlider.valueProperty().addListener(listener);
    }

    @FXML
    public void initialize() {
        new MainMenuOptionController(this);
    }
}
