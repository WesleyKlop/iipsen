package client.ui.views;

import client.ui.controllers.ColorPreferenceController;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * ColorPreferenceView displays the color preference and passes action to ColorPreferenceController
 *
 * @see ColorPreferenceController
 */
public class ColorPreferenceView {
    public ColorPicker colorPreferencePicker;
    public Label colorPreferenceLabel;
    private ColorPreferenceController controller;

    public void initialize() {
        controller = new ColorPreferenceController(this);
        colorPreferenceLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25));
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
        colorPreferenceLabel.setStyle(String.format("-fx-background-color: #%02X%02X%02X",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255)));
    }
}
