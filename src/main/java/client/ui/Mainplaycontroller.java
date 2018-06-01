package client.ui;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class Mainplaycontroller implements Initializable {


    @FXML
    private BackgroundImage myBI = new BackgroundImage(new Image("/Routes/Background2.png", 1280, 720, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    @FXML
    private BorderPane bPane;

    public void initialize(URL url, ResourceBundle rb) {
        bPane.setBackground(new Background(myBI));
    }
}