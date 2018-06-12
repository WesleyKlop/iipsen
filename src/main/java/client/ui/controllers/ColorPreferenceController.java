package client.ui.controllers;

import client.ui.views.ColorPreferenceView;
import javafx.scene.paint.Color;

/**
 * @author Wesley Klop
 */
public class ColorPreferenceController {
    private ColorPreferenceView view;
    private Color color = Color.WHITE;
    private Color darkerColor = color.darker();

    public ColorPreferenceController(ColorPreferenceView view) {
        this.view = view;

        view.setColor(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color value) {
        color = value;
        darkerColor = value.darker();
        updateColor();
    }

    public void darkenButton() {
        view.setColor(darkerColor);
    }

    public void updateColor() {
        view.setColor(color);
    }

    public void resetColor() {
        color = Color.WHITE;
        darkerColor = color.darker();
        updateColor();
    }
}
