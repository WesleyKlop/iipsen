package client.ui;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 */
public class ColorPreferenceController {

    public ColorPicker colorPreferencePicker;
    public Label colorPreferenceLabel;
    public Rectangle colorPreferenceRectangle;

    public void initialize() {
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

    public void exited() {
        changeColor();
    }

    Color getSelectedColor() {
        return colorPreferencePicker.getValue();
    }

    public void reset() {
        colorPreferencePicker.setValue(Color.WHITE);
        changeColor();
    }
}
