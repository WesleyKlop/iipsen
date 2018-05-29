package client.ui;


import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
    public Text ruleRules;

    public VBox VBoxOption;
    public Slider optionVolumeSlider;
    public CheckBox optionColorblind;

    public ImageView train1;
    public ImageView train2;
    public ImageView train3;

    public MediaPlayer player;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        playMusic();
        style(VBoxMain);
        style(VBoxPlay);
        style(VBoxLoad);
        style(HBoxRule1);
        style(HBoxRule2);
        style(HBoxRule3);
        ImageView[] trains = {train1, train2, train3};
        trainAnimation(trains);
        ruleRules.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Regular.ttf"), 25));
        rootPane.setStyle("-fx-background-color: linear-gradient(to bottom, #bfe8f9 0%,#0082ED 70%);");
    }


    private void trainAnimation(ImageView image) {
        image.setImage(new Image(getClass().getResource(generateTrainColorImage()).toString()));
        TranslateTransition trainAni = new TranslateTransition(Duration.seconds(20), image);
        trainAni.setByX(5000);
        trainAni.play();
        trainAni.setOnFinished(e -> {
            image.setLayoutX(image.getLayoutX() - 5000);
            trainAnimation(image);
        });
    }
    private void trainAnimation(ImageView[] image) {
        for (ImageView train : image) {
            trainAnimation(train);
        }
    }
    private String generateTrainColorImage() {
        int imageNumber = (int) (Math.random() * 5);
        String path = "/images/train_";
        switch (imageNumber) {
            case 0:
                path += "blue.png";
                break;
            case 1:
                path += "green.png";
                break;
            case 2:
                path += "orange.png";
                break;
            case 3:
                path += "pink.png";
                break;
            case 4:
                path += "red.png";
                break;
            default:
                path += "blue.png";
                break;
        }
        return path;
    }

    private void playMusic() {
        Media backgroundMusic = new Media(getClass().getResource("/sound/background.mp3").toString());
        player = new MediaPlayer(backgroundMusic);
        player.volumeProperty().bind(optionVolumeSlider.valueProperty());
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

    public void mute() {
        player.setMute(!player.isMute());
        optionVolumeSlider.setDisable(player.isMute());
    }

    private void style(Label label) {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25);
        label.setFont(font);
        label.setStyle("-fx-background-color: white;" + "-fx-background-radius: 10;");
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(10));
        label.setPrefHeight(50);
        label.setPrefWidth(250);
    }

    private void style(VBox menu) {
        for (int i = 1; i < menu.getChildren().size(); i++) {
            style((Label) menu.getChildren().get(i));
        }
    }

    private void style(HBox menu) {
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

    public void manageRules(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        String rules = getRule(label);
        ruleRules.setText(rules);
    }

    private String getRule(Label label) {
        String labelId = label.getId();
        String rules = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document rulesDoc = builder.parse(getClass().getResourceAsStream("/string/rules.xml"));
            rulesDoc.getDocumentElement().normalize();

            String[] arr = rulesDoc.getElementsByTagName(labelId).item(0).getTextContent().split("\n");
            StringBuilder buffer = new StringBuilder();
            for (String line : arr) {
                buffer.append(line.trim());
                buffer.append("\n");
            }
            rules = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rules;
    }

    public void openMenuSequence(MouseEvent mouseEvent) {
        VBox menu = getMenu(mouseEvent);
        int disabledMenu = getDisabledInt();
        closeMenu();
        openMenu(mouseEvent, menu);
        enableMain(disabledMenu);
    }

    private void openMenu(MouseEvent mouseEvent, VBox menu) {
        Label label = (Label) mouseEvent.getSource();
        label.setDisable(true);
        TranslateTransition ani = new TranslateTransition(Duration.millis(500), menu);
        ani.setToY(-1480);
        ani.play();
    }

    private void closeMenu() {
        VBox menu = getDisabledMenu();
        TranslateTransition ani = new TranslateTransition(Duration.millis(500), menu);
        ani.setToY(20);
        ani.play();
    }

    private void enableMain(int disabled) {
        VBoxMain.getChildren().get(disabled).setDisable(false);
    }

    private int getDisabledInt() {
        for (int i = 0; i < 5; i++) {
            if (VBoxMain.getChildren().get(i).isDisable()) {
                return i;
            }
        }
        return 0;
    }

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

    private VBox getMenu(MouseEvent mouseEvent) {
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

    public void widthUpImageView(MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getSource();
        source.setFitWidth(source.getFitWidth() + 14);
        source.setLayoutX(source.getLayoutX() - 7);
        source.setLayoutY(source.getLayoutY() - 7);
    }

    public void widthDownImageView(MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getSource();
        source.setFitWidth(source.getFitWidth() - 14);
        source.setLayoutX(source.getLayoutX() + 7);
        source.setLayoutY(source.getLayoutY() + 7);
    }
}
