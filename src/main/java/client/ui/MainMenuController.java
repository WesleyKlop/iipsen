package client.ui;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// TODO THIS REALLY SHITTY CODE
public class MainMenuController {

    @FXML
    public Label titleLabel;
    @FXML
    public Label playLabel;
    @FXML
    public Label loadLabel;
    @FXML
    public Label ruleLabel;
    @FXML
    public Label optionLabel;
    @FXML
    public Label quitLabel;
    @FXML
    public Pane rootPane;

    @FXML
    public void initialize() {
        style(playLabel);
        style(loadLabel);
        style(ruleLabel);
        style(optionLabel);
        style(quitLabel);
        rootPane.setStyle("-fx-background-color: linear-gradient(to bottom, #bfe8f9 0%,#0082ED 70%);");
    }

    private void style(Label label) {
        label.setFont(Font.font("Californian FB", FontWeight.BOLD, 20));
        label.setStyle("-fx-background-color: white;" + "-fx-border-color: black;");
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setPrefHeight(50);
        label.setPrefWidth(200);
    }

    public void hoverEnterPlay() {
        hoverEnter(playLabel);
    }

    public void hoverEnterLoad() {
        hoverEnter(loadLabel);
    }

    public void hoverEnterRule() {
        hoverEnter(ruleLabel);
    }

    public void hoverEnterOption() {
        hoverEnter(optionLabel);
    }

    public void hoverEnterQuit() {
        hoverEnter(quitLabel);
    }

    public void hoverExitPlay() {
        hoverExit(playLabel);
    }

    public void hoverExitLoad() {
        hoverExit(loadLabel);
    }

    public void hoverExitRule() {
        hoverExit(ruleLabel);
    }

    public void hoverExitOption() {
        hoverExit(optionLabel);
    }

    public void hoverExitQuit() {
        hoverExit(quitLabel);
    }

    public void hoverEnter(Label label) {
        label.setTextFill(Color.RED);
    }

    public void hoverExit(Label label) {
        label.setTextFill(Color.BLACK);
    }

    public void quitGame() {
        System.exit(0);
    }


}
