package client.ui;

import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author Thom
 * @Version 2.0
 * @Since 30-5-2018
 */
public class MainMenuOptionController implements Initializable {

    public MediaPlayer player;
    public Slider optionVolumeMusicSlider;
    public Slider optionVolumeFXSlider;

    public void initialize(URL url, ResourceBundle bundle) {
        playMusic();
    }

    private void playMusic() {
        Media backgroundMusic = new Media(getClass().getResource("/sound/background.mp3").toString());
        player = new MediaPlayer(backgroundMusic);
        player.volumeProperty().bind(optionVolumeMusicSlider.valueProperty());
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

    public void mute() {
        player.setMute(!player.isMute());
        optionVolumeMusicSlider.setDisable(player.isMute());
        optionVolumeFXSlider.setDisable(player.isMute());

    }
}
