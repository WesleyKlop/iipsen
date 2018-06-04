package client.ui;

import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Wesley Klop <wesley19097@gmail.com>
 */
public class ColorPreferenceController implements Initializable {

    public ColorPicker colorPreferencePicker;
    public Label colorPreferenceLabel;
    public Rectangle colorPreferenceRectangle;
    private MainMenuController mainMenuController = new MainMenuController();

    public void initialize(URL url, ResourceBundle bundle) {
        colorPreferencePicker.getStyleClass().add("button");
        colorPreferenceLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25));
        colorPreferenceRectangle.setArcHeight(20);
        colorPreferenceRectangle.setArcWidth(20);
        changeColor();
    }

    public void changeColor() {
        colorPreferenceRectangle.setFill(colorPreferencePicker.getValue());
    }

    public void entered() {
        Rectangle source = colorPreferenceRectangle;
        double red = Math.max(0, colorPreferencePicker.getValue().getRed() - 0.05);
        double green = Math.max(0, colorPreferencePicker.getValue().getGreen() - 0.05);
        double blue = Math.max(0, colorPreferencePicker.getValue().getBlue() - 0.05);
        Color newFill = new Color(red, green, blue, 1);
        source.setFill(newFill);
    }

    public void exited(MouseEvent mouseEvent) {
        changeColor();
    }

    public Color getSelectedColor() {
        return colorPreferencePicker.getValue();
    }

}
