package client.ui;


import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    public Pane rootPane;
    public VBox VBoxMain;
    public Label playLabel;
    public Label loadLabel;
    public Label ruleLabel;
    public Label optionLabel;
    public Label quitLabel;

    public VBox VBoxPlay;
    public Label playCreateLobby;
    public Label playJoinLobby;

    public VBox VBoxLoad;
    public Label loadLevelLabel;

    public VBox VBoxRule;
    public HBox HBoxRule1;
    public Label ruleObjective;
    public Label ruleTurn;
    public Label ruleTrainCards;
    public HBox HBoxRule2;
    public Label ruleRoutes;
    public Label ruleTunnels;
    public Label ruleFerries;
    public HBox HBoxRule3;
    public Label ruleDestinationCards;
    public Label ruleEnd;
    public Label ruleScore;
    public Text ruleObjectiveRules;

    public VBox VBoxOption;
    public Slider optionVolumeSlider;
    public CheckBox optionColorblind;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        playMusic("/Sound/background.mp3");
        style(VBoxMain);
        style(VBoxPlay);
        style(VBoxLoad);
        style(HBoxRule1);
        style(HBoxRule2);
        style(HBoxRule3);
        ruleObjectiveRules.setFont(Font.font("Californian FB", 20));

        rootPane.setStyle("-fx-background-color: linear-gradient(to bottom, #bfe8f9 0%,#0082ED 70%);");
    }

    public void playMusic(String name) {
        Media backgroundMusic = new Media(getClass().getResource(name).toString());
        MediaPlayer player = new MediaPlayer(backgroundMusic);
        player.volumeProperty().bind(optionVolumeSlider.valueProperty());
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

    public void style(Label label) {
        label.setFont(Font.font("Californian FB", FontWeight.BOLD, 25));
        label.setStyle("-fx-background-color: white;" + "-fx-border-color: black;");
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(12, 12, 12, 12));
        label.setPrefHeight(50);
        label.setPrefWidth(250);
    }

    public void style(VBox menu) {
        for (int i = 1; i < menu.getChildren().size(); i++) {
            style((Label) menu.getChildren().get(i));
        }
    }

    public void style(HBox menu) {
        for (int i = 0; i < menu.getChildren().size(); i++) {
            style((Label) menu.getChildren().get(i));
        }
    }

    public void hoverEnter(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.setTextFill(Color.RED);
    }

    public void hoverExit(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.setTextFill(Color.BLACK);
    }

    public void openMenuSequence(MouseEvent mouseEvent) {
        VBox menu = getMenu(mouseEvent);
        int disabledMenu = getDisabledInt();
        closeMenu();
        openMenu(mouseEvent, menu);
        enableMain(disabledMenu);
    }

    public void openMenu(MouseEvent mouseEvent, VBox menu) {
        Label label = (Label) mouseEvent.getSource();
        label.setDisable(true);
        TranslateTransition ani = new TranslateTransition(Duration.millis(500), menu);
        ani.setToY(-1480);
        ani.play();
    }

    public void closeMenu() {
        VBox menu = getDisabledMenu();
        TranslateTransition ani = new TranslateTransition(Duration.millis(500), menu);
        ani.setToY(20);
        ani.play();
    }

    public void enableMain(int disabled) {
        VBoxMain.getChildren().get(disabled).setDisable(false);
    }

    public int getDisabledInt() {
        for (int i = 0; i < 5; i++) {
            if (VBoxMain.getChildren().get(i).isDisable()) {
                return i;
            }
        }
        return 0;
    }

    public VBox getDisabledMenu() {
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

    public VBox getMenu(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
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
        System.exit(0);
    }


}
