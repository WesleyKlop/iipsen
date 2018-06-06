package client.ui;

import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 */
public class MainMenuOptionController implements Initializable {

    private static final Logger Log = LogManager.getLogger(MainMenuOptionController.class);
    public Slider optionVolumeMusicSlider, optionVolumeFXSlider;
    public CheckBox optionMute;
    private MediaPlayer player;
    private boolean isMute = false;


    public void initialize(URL url, ResourceBundle bundle) {
        playMusic();
    }

    private void playMusic() {
        Media backgroundMusic = new Media(getClass().getResource("/sound/background.mp3").toString());
        player = new MediaPlayer(backgroundMusic);
        player.setVolume(optionVolumeMusicSlider.getValue());
        player.volumeProperty().bind(optionVolumeMusicSlider.valueProperty());
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setOnReady(() -> {
            Log.debug("Volume: " + player.getVolume());
            Log.debug("Mute set to: " + player.isMute());
        });
        player.play();
    }

    public void mute() {
        isMute = !isMute;
        player.setMute(isMute);
        optionVolumeMusicSlider.setDisable(isMute);
        optionVolumeFXSlider.setDisable(isMute);

    }

    public void playFX() {
        Media click = new Media(getClass().getResource("/sound/click.mp3").toString());
        MediaPlayer playerFX = new MediaPlayer(click);
        playerFX.setMute(isMute);
        playerFX.setVolume(optionVolumeFXSlider.getValue());
        playerFX.play();
    }

    public void checkBoxCursorChanger(MouseEvent mouseEvent){

        CheckBox checkbox = (CheckBox) mouseEvent.getSource();
        checkbox.setCursor(Cursor.HAND);
    }

    public void checkBoxCursorChanger(MouseEvent mouseEvent){

        CheckBox checkbox = (CheckBox) mouseEvent.getSource();
        checkbox.setCursor(Cursor.HAND);
    }

}
