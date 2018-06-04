package client.ui;

import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
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
    public MediaPlayer playerFX;
    public Slider optionVolumeMusicSlider;
    public Slider optionVolumeFXSlider;
    public CheckBox optionMute;

    public void initialize(URL url, ResourceBundle bundle) {
        playMusic();
    }

    public boolean isMute = false;

    private void playMusic() {
        Media backgroundMusic = new Media(getClass().getResource("/sound/background.mp3").toString());
        player = new MediaPlayer(backgroundMusic);
        player.volumeProperty().bind(optionVolumeMusicSlider.valueProperty());
        optionVolumeMusicSlider.setCursor(Cursor.HAND);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

    public void mute() {
        isMute = !isMute;
        player.setMute(isMute);
        optionVolumeMusicSlider.setDisable(isMute);
        optionVolumeFXSlider.setDisable(isMute);

    }

    public void playFX(boolean isMute) {

        Media click = new Media(getClass().getResource("/sound/click.mp3").toString());
        playerFX = new MediaPlayer(click);
        playerFX.setMute(isMute);
        playerFX.play();
        playerFX.setVolume(0.3);
        playerFX.volumeProperty().bind(optionVolumeFXSlider.valueProperty());

    }

}
