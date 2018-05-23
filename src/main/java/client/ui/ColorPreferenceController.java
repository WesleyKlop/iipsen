package client.ui;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * @author wesley
 */
public class ColorPreferenceController {
    public void onColorSelected(MouseEvent mouseEvent) {
        Color selectedColor = ((CircleButton) mouseEvent.getSource()).getColor();
        System.out.println("Color selected: " + selectedColor.toString());
    }
}
