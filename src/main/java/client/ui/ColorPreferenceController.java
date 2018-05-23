package client.ui;

import javafx.scene.input.MouseEvent;

/**
 * @author wesley
 */
public class ColorPreferenceController {
    public void onColorSelected(MouseEvent mouseEvent) {
        String selectedColor = ((CircleButton) mouseEvent.getSource()).getColor();
        System.out.println("Color selected: " + selectedColor);
    }
}
