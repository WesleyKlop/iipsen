package client.ui.MainMenuControllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 */
public class MainMenuFrontImagesController implements Initializable {

    public ImageView train1, train2, train3;
    public HBox trainsHBox;

    public void initialize(URL url, ResourceBundle bundle) {
        trainAnimation(trainsHBox);
    }

    /**
     * Animates a single train image
     *
     * @param trains HBox with the train images
     */
    private void trainAnimation(HBox trains) {
        newImage(trains);
        int seconds = (int) ((Math.random() * 20) + 10);
        TranslateTransition trainAni = new TranslateTransition(Duration.seconds(seconds), trains);
        trainAni.setByX(8000);
        trainAni.play();
        trainAni.setOnFinished(e -> {
            trains.setLayoutX(trains.getLayoutX() - 8000);
            trainAnimation(trains);
        });
    }

    private void newImage(HBox trainBox) {
        for (int i = 0; i < trainBox.getChildren().size(); i++) {
            ImageView train = (ImageView) trainBox.getChildren().get(i);
            train.setImage(new Image(getClass().getResource(generateTrainColorImage()).toString()));
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
}
