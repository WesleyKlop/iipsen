package client.ui.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;


public class LayoutCardController implements Initializable {
    @FXML
    HBox HBox;
    @FXML
    ImageView black;
    @FXML
    ImageView blue;
    @FXML
    ImageView green;
    @FXML
    ImageView orange;
    @FXML
    ImageView purple;
    @FXML
    ImageView red;
    @FXML
    ImageView white;
    @FXML
    ImageView yellow;
    @FXML
    ImageView locomotive;

    public void initialize(URL url, ResourceBundle bundle) {
        black.setVisible(false);
        blue.setVisible(false);
        green.setVisible(false);
        orange.setVisible(false);
        purple.setVisible(false);
        red.setVisible(false);
        white.setVisible(false);
        yellow.setVisible(false);
        locomotive.setVisible(false);
    }


}
