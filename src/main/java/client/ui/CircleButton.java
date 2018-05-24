package client.ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleButton extends Circle {

    private Color color;
    private Color hoverColor;
    private static final int RADIUS = 10;
    private boolean isSelected = false;

    public CircleButton() {
        super();
        setRadius(RADIUS);
        setStroke(Color.BLACK);

        setOnMouseEntered(e -> {
            setFill(hoverColor);
            if (!isSelected)
                setStroke(Color.YELLOW);
        });

        setOnMouseExited(e -> {
            setFill(color);
            if (!isSelected)
                setStroke(Color.BLACK);
        });
    }

    public void setSelected() {
        isSelected = true;
        setStroke(color);
    }

    public void setDeselected() {
        isSelected = false;
        setStroke(Color.BLACK);
    }

    public String getColor() {
        return color.toString();
    }

    public void setColor(String color) {
        this.color = Color.web(color);
        setFill(this.color);
    }

    public Color getColorObj() {
        return color;
    }

    public String getHoverColor() {
        return hoverColor.toString();
    }

    public void setHoverColor(String hoverColor) {
        this.hoverColor = Color.web(hoverColor);
    }
}
