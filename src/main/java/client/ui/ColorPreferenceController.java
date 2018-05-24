package client.ui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * @author wesley
 */
public class ColorPreferenceController {
    @FXML
    public HBox container;

    private Color selectedColor;

    public void onColorSelected(MouseEvent mouseEvent) {
        selectedColor = ((CircleButton) mouseEvent.getSource()).getColorObj();
        setSelectedColor();
    }

    public void setSelectedColor() {
        // Loop through all buttons and select the right one
        container.getChildren().forEach(e -> {
            CircleButton button = (CircleButton) e;
            if (selectedColor == button.getColorObj()) {
                button.setSelected();
            } else {
                button.setDeselected();
            }
        });
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

}
