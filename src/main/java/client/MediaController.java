package client;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Wesley Klop
 */
public class MediaController {
    private static MediaController controller;
    private final UserPreferences prefs = new UserPreferences();

    private MediaPlayer player;
    private AudioClip clickClip;

    private MediaController() {
        Media backgroundMusic = new Media(getClass().getResource("/sound/background.mp3").toString());
        player = new MediaPlayer(backgroundMusic);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        clickClip = new AudioClip(getClass().getResource("/sound/click.mp3").toString());
    }

    public static void playMusic() {
        var player = getController().player;
        var prefs = getController().prefs;

        if (player.getStatus() == MediaPlayer.Status.PLAYING) {
            return;
        }

        player.setVolume(prefs.getMusicVolume());
        player.setMute(prefs.isSoundMuted());
        player.play();
    }

    public static void playClickSound() {
        var prefs = getController().prefs;
        if (prefs.isSoundMuted()) {
            return;
        }
        getController().clickClip.stop();
        getController().clickClip.play(prefs.getFxVolume());
    }

    private static MediaController getController() {
        if (controller == null) {
            controller = new MediaController();
        }

        return controller;
    }

    public static MediaPlayer getMusicPlayer() {
        return getController().player;
    }
}
