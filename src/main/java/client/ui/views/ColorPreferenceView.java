package client.ui.views;

import client.ui.controllers.ColorPreferenceController;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * ColorPreferenceView displays the color preference and passes action to ColorPreferenceController
 *
 * @see ColorPreferenceController
 */
public class ColorPreferenceView {
    public ColorPicker colorPreferencePicker;
    public Label colorPreferenceLabel;
    public Rectangle colorPreferenceRectangle;
    private ColorPreferenceController controller;

    public void initialize() {
        controller = new ColorPreferenceController(this);
        colorPreferencePicker.getStyleClass().add("button");
        colorPreferenceLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25));
        colorPreferenceRectangle.setArcHeight(20);
        colorPreferenceRectangle.setArcWidth(20);
        onColorPickerHide();
    }

    public void onColorPickerHide() {
        controller.setColor(colorPreferencePicker.getValue());
    }

    public void onMouseEnter() {
        controller.darkenButton();
    }

    public void onMouseExit() {
        controller.updateColor();
    }

    public Color getSelectedColor() {
        return controller.getColor();
    }

    public void reset() {
        controller.resetColor();
    }

    public void setColor(Color color) {
        colorPreferenceRectangle.setFill(color);
    }
}
