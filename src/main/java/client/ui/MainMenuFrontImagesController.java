package client.ui;

import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 */
public class MainMenuFrontImagesController implements Initializable {

    public ImageView train1;
    public ImageView train2;
    public ImageView train3;

    public void initialize(URL url, ResourceBundle bundle) {
        ImageView[] trains = {train1, train2, train3};
        trainAnimation(trains);
    }

    /**
     * Animates a single train image
     *
     * @param image train image
     */
    private void trainAnimation(ImageView image) {
        image.setImage(new Image(getClass().getResource(generateTrainColorImage()).toString()));
        TranslateTransition trainAni = new TranslateTransition(Duration.seconds(28), image);
        trainAni.setByX(8000);
        trainAni.play();
        trainAni.setOnFinished(e -> {
            image.setLayoutX(image.getLayoutX() - 8000);
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
}
