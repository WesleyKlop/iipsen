package client.ui.components;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Wesley Klop
 */
public class LocationInformation extends StackPane {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;

    private Label locationInformation = new Label();

    public LocationInformation() {
        Rectangle locationInformationBackground = new Rectangle(WIDTH, HEIGHT, Color.WHITE);
        locationInformationBackground.setStroke(Color.BLACK);
        locationInformationBackground.setStrokeWidth(2);
        locationInformationBackground.setArcWidth(20);
        locationInformationBackground.setArcHeight(20);
        Polygon locationInformationDecoration = new Polygon();
        locationInformationDecoration.getPoints().addAll(-10.0, 0.0, 10.0, 0.0, 0.0, 10.0);
        locationInformationDecoration.setLayoutX(WIDTH / 2);
        locationInformationDecoration.setTranslateY(HEIGHT - 10);
        this.getChildren().addAll(locationInformationBackground, locationInformation, locationInformationDecoration);
        this.setOpacity(0);
        this.setMouseTransparent(true);

        startAnimation();
    }

    private void startAnimation() {
        TranslateTransition animation = new TranslateTransition(Duration.millis(1500), this);
        animation.setToY(-10);
        animation.setAutoReverse(true);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public void setLabel(String text) {
        locationInformation.setText(text);
    }

    public void show(String label, int[] coords) {
        this.setLabel(label);

        this.setLayoutX(Math.min(Math.max(coords[0] - (this.getWidth() / 2) + 3, 0), (1000 - this.getWidth() + 3)));
        this.setLayoutY(coords[1] - this.getHeight() - 10);
        this.setOpacity(1);
    }

    public void hide() {
        this.setOpacity(0);
        this.setLabel("");
    }
}
