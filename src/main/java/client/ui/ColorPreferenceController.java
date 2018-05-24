package client.ui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

interface ColorPreferenceListener {
    void onColorSelected(Color color);
}

/**
 * @author wesley
 */
public class ColorPreferenceController {
    @FXML
    public HBox container;

    private ColorPreferenceListener listener;

    public void onColorSelected(MouseEvent mouseEvent) {
        Color selectedColor = ((CircleButton) mouseEvent.getSource()).getColorObj();
        System.out.println("Color selected: " + selectedColor);
        if (listener != null) {
            listener.onColorSelected(selectedColor);
        }
        setSelectedColor(selectedColor);
    }

    public void setListener(ColorPreferenceListener listener) {
        this.listener = listener;
    }

    public void setSelectedColor(Color color) {
        container.getChildren().forEach(e -> {
            CircleButton button = (CircleButton) e;
            if (color == button.getColorObj()) {
                button.setSelected();
            } else {
                button.setDeselected();
            }
        });
    }
}
