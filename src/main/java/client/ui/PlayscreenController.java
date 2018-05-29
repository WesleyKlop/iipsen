package client.ui;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class PlayscreenController implements Initializable {



    @FXML
    private BackgroundImage myBI= new BackgroundImage(new Image("/Routes/Background.png",1280,650,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    @FXML
    private BorderPane bPane;
    @FXML
    private HBox steen;
    @FXML
    private HBox white;
    @FXML
    private HBox white2;
    @FXML
    private HBox yellow2;
    @FXML
    private HBox red;
    @FXML
    private HBox orange;
    @FXML
    private HBox green;
    @FXML
    private HBox green2;
    @FXML
    private HBox green3;
    @FXML
    private HBox red2;
    @FXML
    private HBox white3;
    @FXML
    private HBox black;
    @FXML
    private HBox orange2;
    @FXML
    private HBox yellow3;
    @FXML
    private HBox green4;
    @FXML
    private HBox black2;
    @FXML
    private HBox purple2;
    @FXML
    private HBox white4;
    @FXML
    private HBox yellow4;
    @FXML
    private HBox red3;
    @FXML
    private HBox orange3;
    @FXML
    public void greenEntered(MouseEvent event) { green.setStyle("-fx-border-color: white;"); green2.setStyle("-fx-border-color: white;");}
    @FXML
    public void greenExited(MouseEvent event) { green.setStyle("-fx-border-color: black;"); green2.setStyle("-fx-border-color: black;");}
    @FXML
    public void orange3Entered(MouseEvent event) { orange3.setStyle("-fx-border-color: white;"); }
    @FXML
    public void orange3Exited(MouseEvent event) { orange3.setStyle("-fx-border-color: black;"); }
    @FXML
    public void white4Entered(MouseEvent event) { white4.setStyle("-fx-border-color: white;"); }
    @FXML
    public void white4Exited(MouseEvent event) { white4.setStyle("-fx-border-color: black;"); }
    @FXML
    public void red3Entered(MouseEvent event) { red3.setStyle("-fx-border-color: white;"); }
    @FXML
    public void red3Exited(MouseEvent event) { red3.setStyle("-fx-border-color: black;"); }
    @FXML
    public void yellow4Entered(MouseEvent event) { yellow4.setStyle("-fx-border-color: white;"); }
    @FXML
    public void yellow4Exited(MouseEvent event) { yellow4.setStyle("-fx-border-color: black;"); }
    @FXML
    public void whiteEntered(MouseEvent event) { white.setStyle("-fx-border-color: white;"); }
    @FXML
    public void whiteExited(MouseEvent event) { white.setStyle("-fx-border-color: black;"); }
    @FXML
    public void purple2Entered(MouseEvent event) { purple2.setStyle("-fx-border-color: white;"); }
    @FXML
    public void purple2Exited(MouseEvent event) { purple2.setStyle("-fx-border-color: black;"); }
    @FXML
    public void black2Entered(MouseEvent event) { black2.setStyle("-fx-border-color: white;"); }
    @FXML
    public void black2Exited(MouseEvent event) { black2.setStyle("-fx-border-color: black;"); }
    @FXML
    public void green4Entered(MouseEvent event) { green4.setStyle("-fx-border-color: white;"); }
    @FXML
    public void green4Exited(MouseEvent event) { green4.setStyle("-fx-border-color: black;"); }
    @FXML
    public void yellow3Entered(MouseEvent event) { yellow3.setStyle("-fx-border-color: white;"); }
    @FXML
    public void yellow3Exited(MouseEvent event) { yellow3.setStyle("-fx-border-color: black;"); }
    @FXML
    public void yellow2Entered(MouseEvent event) { yellow2.setStyle("-fx-border-color: white;"); }
    @FXML
    public void yellow2Exited(MouseEvent event) { yellow2.setStyle("-fx-border-color: black;"); }
    @FXML
    public void orange2Entered(MouseEvent event) { orange2.setStyle("-fx-border-color: white;"); }
    @FXML
    public void orange2Exited(MouseEvent event) { orange2.setStyle("-fx-border-color: black;"); }
    @FXML
    public void white3Entered(MouseEvent event) { white3.setStyle("-fx-border-color: white;"); }
    @FXML
    public void blackExited(MouseEvent event) { black.setStyle("-fx-border-color: black;"); }
    @FXML
    public void blackEntered(MouseEvent event) { black.setStyle("-fx-border-color: white;"); }
    @FXML
    public void white3Exited(MouseEvent event) { white3.setStyle("-fx-border-color: black;"); }
    @FXML
    public void green3Entered(MouseEvent event) { green3.setStyle("-fx-border-color: white;"); }
    @FXML
    public void green3Exited(MouseEvent event) { green3.setStyle("-fx-border-color: black;"); }
    @FXML
    public void red2Entered(MouseEvent event) { red2.setStyle("-fx-border-color: white;"); }
    @FXML
    public void red2Exited(MouseEvent event) { red2.setStyle("-fx-border-color: black;"); }
    @FXML
    public void redEntered(MouseEvent event) { red.setStyle("-fx-border-color: white;"); }
    @FXML
    public void redExited(MouseEvent event) { red.setStyle("-fx-border-color: black;"); }
    @FXML
    public void white2Entered(MouseEvent event) { white2.setStyle("-fx-border-color: white;"); }
    @FXML
    public void white2Exited(MouseEvent event) { white2.setStyle("-fx-border-color: black;");
    }
    @FXML
    public void orangeEntered(MouseEvent event) {
        orange.setStyle("-fx-border-color: white;");

    }

    @FXML
    public void orangeExited(MouseEvent event) {
        orange.setStyle("-fx-border-color: black;");
    }

    @FXML
    public void purpleEntered(MouseEvent event) {
        steen.setStyle("-fx-border-color: white;");
    }

    @FXML
    public void purpleExited(MouseEvent event) {
        steen.setStyle("-fx-border-color: black;");
    }

    public void initialize(URL url, ResourceBundle rb) {
        bPane.setBackground(new Background(myBI));
    }
}
