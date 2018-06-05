package client.ui;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class PlayscreenController implements Initializable {



    @FXML
    private BackgroundImage myBI = new BackgroundImage(new Image("/images/Background.png", 996, 555, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    @FXML
    private BorderPane bPane;
    @FXML
    private HBox title1;
    @FXML
    private HBox title2;
    @FXML
    private HBox title3;
    @FXML
    private HBox title4;

    public void orangeroadEntered(MouseEvent event) {
        title1.setStyle("-fx-background-color: white; -fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid;");
        title2.setStyle("-fx-background-color: white; -fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid;");
        title3.setStyle("-fx-background-color: white; -fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid;");
        title4.setStyle("-fx-background-color: white; -fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid;");
    }

    public void orangeroadExited(MouseEvent event) {
        title1.setStyle("-fx-background-color: black; -fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid;");
        title2.setStyle("-fx-background-color: black; -fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid;");
        title3.setStyle("-fx-background-color: black; -fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid;");
        title4.setStyle("-fx-background-color: black; -fx-border-color:black; -fx-border-width: 1; -fx-border-style: solid;");
    }


    public void initialize(URL url, ResourceBundle rb) {
        bPane.setBackground(new Background(myBI));
    }
}
