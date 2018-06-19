package client.ui.MainMenuControllers;

import client.MediaController;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import util.TempException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Thom
 * @version 2.0
 * @since 29-05-2018
 */
public class MainMenuController implements Initializable {

    public Pane rootPane;
    public Label playLabel, loadLabel, ruleLabel, optionLabel, quitLabel;
    public VBox VBoxMain, VBoxPlay, VBoxLoad, VBoxRule, VBoxOption;
    public Pane snowPane, snowPaneFront;
    public ImageView gear1, gear2, gear3;

    @FXML
    private MainMenuPlayController VBoxPlayController;
    @FXML
    private MainMenuLoadController VBoxLoadController;
    @FXML
    private MainMenuOptionController VBoxOptionController;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        rootPane.setStyle("-fx-background-color: linear-gradient(to bottom, #bfe8f9 0%,#0082ED 70%);");
        snow();
        rotateClock(gear1);
        rotateCounterClock(gear2);
        rotateClock(gear3);
        MediaController.playMusic();
    }

    private void rotateClock(ImageView image) {
        RotateTransition rotAni = new RotateTransition(Duration.seconds(3), image);
        rotAni.setCycleCount(Animation.INDEFINITE);
        rotAni.setInterpolator(Interpolator.LINEAR);
        rotAni.setToAngle(360);
        rotAni.play();
    }

    private void rotateCounterClock(ImageView image) {
        RotateTransition rotAni = new RotateTransition(Duration.seconds(3), image);
        rotAni.setCycleCount(Animation.INDEFINITE);
        rotAni.setInterpolator(Interpolator.LINEAR);
        rotAni.setToAngle(-360);
        rotAni.play();
    }

    /**
     * Manages menu opening and closing.
     *
     * @param mouseEvent Label source
     */
    public void openMenuSequence(MouseEvent mouseEvent) {
        MediaController.playClickSound();
        Label label = (Label) mouseEvent.getSource();
        VBox menu = getMenu(label);
        int disabledMenu = getDisabledInt();
        closeMenu();
        openMenu(label, menu);
        enableMain(disabledMenu);
    }

    /**
     * Opens menu, disables opened menu
     *
     * @param label Label source
     * @param menu Menu to be opened
     */
    private void openMenu(Label label, VBox menu) {
        label.setDisable(true);
        TranslateTransition ani = new TranslateTransition(Duration.millis(500), menu);
        ani.setToY(-1060);
        ani.play();
    }

    /**
     * Closes disabled menu
     */
    private void closeMenu() {
        VBox menu = getDisabledMenu();
        TranslateTransition ani = new TranslateTransition(Duration.millis(500), menu);
        ani.setToY(20);
        ani.play();
    }

    private void enableMain(int disabled) {
        VBoxMain.getChildren().get(disabled).setDisable(false);
    }

    /**
     * Searches trough the main labels
     *
     * @return disabled main label index number
     */
    private int getDisabledInt() {
        for (int i = 0; i < 5; i++) {
            if (VBoxMain.getChildren().get(i).isDisable()) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Matches disabled menu label with menu
     *
     * @return disabled menu
     */
    private VBox getDisabledMenu() {
        VBox menu = null;
        switch (getDisabledInt()) {
            case 1:
                menu = VBoxPlay;
                break;
            case 2:
                menu = VBoxLoad;
                break;
            case 3:
                menu = VBoxRule;
                break;
            case 4:
                menu = VBoxOption;
                break;
            default:
                break;
        }
        return menu;
    }

    /**
     * Matches label
     *
     * @param label main menu label
     * @return VBox corresponding the param label
     */
    private VBox getMenu(Label label) {
        VBox menu = null;
        switch (label.getId()) {
            case "playLabel":
                menu = VBoxPlay;
                break;
            case "loadLabel":
                menu = VBoxLoad;
                break;
            case "ruleLabel":
                menu = VBoxRule;
                break;
            case "optionLabel":
                menu = VBoxOption;
                break;
            default:
                break;
        }
        return menu;
    }

    public void quitGame() {
        try {
            throw new TempException();
        } catch (TempException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private void snow() {
        int imageXAmount = 10;
        int imageYAmount = 4;
        ImageView[][] snow = new ImageView[imageXAmount][imageYAmount];
        Image snowGif = new Image(getClass().getResourceAsStream("/images/snow.gif"));
        for (int i = 0; i < imageXAmount; i++) {
            for (int j = 0; j < imageYAmount; j++) {
                snow[i][j] = new ImageView(snowGif);
                snow[i][j].setLayoutX(i * 400);
                snow[i][j].setLayoutY((j % 2) * 300);
                if (j >= 2) {
                    snowPaneFront.getChildren().add(snow[i][j]);
                } else {
                    snowPane.getChildren().add(snow[i][j]);
                }
            }
        }
    }

    MainMenuPlayController getPlayController() {
        return VBoxPlayController;
    }

    MainMenuLoadController getLoadController() {
        return VBoxLoadController;
    }

}
