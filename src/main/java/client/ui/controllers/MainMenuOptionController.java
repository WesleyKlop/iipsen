package client.ui.controllers;

import client.MediaController;
import client.UserPreferences;
import client.ui.views.MainMenuOptionView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller for managing settings
 *
 * @author Wesley Klop
 */
public class MainMenuOptionController {
    private static final Logger Log = LogManager.getLogger(MainMenuOptionController.class);
    private final UserPreferences prefs = new UserPreferences();
    private final MainMenuOptionView view;

    public MainMenuOptionController(MainMenuOptionView view) {
        this.view = view;
        view.setOnMuteChange((observable, oldValue, newValue) -> {
            prefs.setSoundMuted(newValue);
            MediaController.getMusicPlayer().setMute(newValue);
            updateView();
        });

        view.setOnMusicVolumeChange((observable, oldValue, newValue) -> {
            prefs.setMusicVolume(newValue.doubleValue());
            Log.debug("Setting volume from {} to {}", MediaController.getMusicPlayer().getVolume(), newValue.doubleValue());
            MediaController.getMusicPlayer().setVolume(newValue.doubleValue());
            updateView();
        });

        view.setOnColorBlindChange((observable, oldValue, newValue) -> {
            prefs.setColorblind(newValue);
            updateView();
        });

        view.setOnFXVolumeChange((observable, oldValue, newValue) -> {
            prefs.setFxVolume(newValue.doubleValue());
            updateView();
        });

        updateView();
    }

    private void updateView() {
        view.updateState(prefs.isSoundMuted(), prefs.isColorBlind(), prefs.getMusicVolume(), prefs.getFxVolume());
    }
}
