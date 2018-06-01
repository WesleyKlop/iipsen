package client.ui;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class PlayscreenController implements Initializable {



    @FXML
    private BackgroundImage myBI = new BackgroundImage(new Image("/Routes/Background.png", 996, 555, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    @FXML
    private BorderPane bPane;
    @FXML
    private ImageView test;
    @FXML
    private ImageView orange_cave;
    @FXML
    private ImageView yellow_cave;
    @FXML
    private ImageView black_cave;
    @FXML
    private ImageView green_route;
    @FXML
    private ImageView red_route;
    @FXML
    private ImageView green_routes;
    @FXML
    private ImageView orange_route;
    @FXML
    private ImageView orange2_route;
    @FXML
    private ImageView blue_route;
    @FXML
    private ImageView orange4_route;
    @FXML
    private ImageView yellow3_route;
    @FXML
    private ImageView red_place;
    @FXML
    private ImageView red_place2;
    @FXML
    private ImageView white_route;
    @FXML
    private ImageView green2_route;
    @FXML
    private ImageView red_route2;
    @FXML
    private ImageView yellow_route2;
    @FXML
    private ImageView purple_route;
    @FXML
    private ImageView white2_route;
    @FXML
    private ImageView red_route3;
    @FXML
    private ImageView Purple_route2;
    @FXML
    private ImageView black_route2;
    @FXML
    private ImageView yellow_route4;
    @FXML
    private ImageView green_route3;
    @FXML
    private ImageView white_route3;
    @FXML
    private ImageView yellow_route5;
    @FXML
    private ImageView black_route3;
    @FXML
    private ImageView orange_route5;
    @FXML
    private ImageView black_route4;
    @FXML
    private ImageView yellow_route6;

    public void yellow_route6Entered(MouseEvent event) {
        yellow_route6.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void yellow_route6Exited(MouseEvent event) {
        yellow_route6.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void black_route4Entered(MouseEvent event) {
        black_route4.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void black_route4Exited(MouseEvent event) {
        black_route4.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void orange_route5Entered(MouseEvent event) {
        orange_route5.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void orange_route5Exited(MouseEvent event) {
        orange_route5.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void black_route3Entered(MouseEvent event) {
        black_route3.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void black_route3Exited(MouseEvent event) {
        black_route3.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void white_route3Entered(MouseEvent event) {
        white_route3.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void white_route3Exited(MouseEvent event) {
        white_route3.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void yellow_route5Entered(MouseEvent event) {
        yellow_route5.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void yellow_route5Exited(MouseEvent event) {
        yellow_route5.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void green_route3Entered(MouseEvent event) {
        green_route3.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void green_route3Exited(MouseEvent event) {
        green_route3.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void purple_routeEntered(MouseEvent event) {
        purple_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void purple_routeExited(MouseEvent event) {
        purple_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void yellow_route4Entered(MouseEvent event) {
        yellow_route4.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void yellow_route4Exited(MouseEvent event) {
        yellow_route4.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void black_route2Entered(MouseEvent event) {
        black_route2.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void black_route2Exited(MouseEvent event) {
        black_route2.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void Purple_route2Entered(MouseEvent event) {
        Purple_route2.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void Purple_route2Exited(MouseEvent event) {
        Purple_route2.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void red_route3Entered(MouseEvent event) {
        red_route3.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void red_route3Exited(MouseEvent event) {
        red_route3.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void yellow_route2Entered(MouseEvent event) {
        yellow_route2.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void yellow_route2Exited(MouseEvent event) {
        yellow_route2.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void white2_routeEntered(MouseEvent event) {
        white2_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void white2_routeExited(MouseEvent event) {
        white2_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }


    public void green2_routeEntered(MouseEvent event) {
        green2_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void green2_routeExited(MouseEvent event) {
        green2_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void red_route2Entered(MouseEvent event) {
        red_route2.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void red_route2Exited(MouseEvent event) {
        red_route2.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void testEntered(MouseEvent event) {
        test.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void testExited(MouseEvent event) {
        test.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void white_routeEntered(MouseEvent event) {
        white_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void white_routeExited(MouseEvent event) {
        white_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void red_place2Entered(MouseEvent event) {
        red_place2.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void red_place2Exited(MouseEvent event) {
        red_place2.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void red_placeEntered(MouseEvent event) {
        red_place.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void red_placeExited(MouseEvent event) {
        red_place.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void yellow3_routeEntered(MouseEvent event) {
        yellow3_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void yellow3_routeExited(MouseEvent event) {
        yellow3_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void blue_routeEntered(MouseEvent event) {
        blue_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void blue_routeExited(MouseEvent event) {
        blue_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void orange4_routeEntered(MouseEvent event) {
        orange4_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void orange4_routeExited(MouseEvent event) {
        orange4_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }


    public void orange_caveEntered(MouseEvent event) {
        orange_cave.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void orange_caveExited(MouseEvent event) {
        orange_cave.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void yellow_caveEntered(MouseEvent event) {
        yellow_cave.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void yellow_caveExited(MouseEvent event) {
        yellow_cave.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void black_caveEntered(MouseEvent event) {
        black_cave.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void black_caveExited(MouseEvent event) {
        black_cave.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void red_routeEntered(MouseEvent event) {
        red_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void red_routeExited(MouseEvent event) {
        red_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void green_routeEntered(MouseEvent event) {
        green_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void green_routeExited(MouseEvent event) {
        green_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void green_routesEntered(MouseEvent event) {
        green_routes.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void green_routesExited(MouseEvent event) {
        green_routes.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void orange_routeEntered(MouseEvent event) {
        orange_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void orange_routeExited(MouseEvent event) {
        orange_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }

    public void orange2_routeEntered(MouseEvent event) {
        orange2_route.setStyle("-fx-effect:dropshadow(gaussian, white, 1, 4, 0, 0);");
    }

    public void orange2_routeExited(MouseEvent event) {
        orange2_route.setStyle("-fx-effect:dropshadow(gaussian, black, 1, 4, 0, 0);");
    }


    public void initialize(URL url, ResourceBundle rb) {
        DropShadow ds = new DropShadow(20, Color.AQUA);
        bPane.setBackground(new Background(myBI));
    }
}
