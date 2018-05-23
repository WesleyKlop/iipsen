package client.ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleButton extends Circle {

    private Color color;
    private Color hoverColor;

    public CircleButton() {
        super();
        this.setColor(getColor());
        setRadius(5);
        setStroke(Color.BLACK);


        setOnMouseEntered(e -> {
            setFill(hoverColor);
            setStroke(Color.YELLOW);
        });

        setOnMouseExited(e -> {
            setFill(Color.web(getColor()));
            setStroke(Color.BLACK);
        });
    }

    public String getColor() {
        return color.toString();
    }

    public void setColor(String color) {
        this.setColor(Color.web(color));
        setFill(this.color);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getHoverColor() {
        return hoverColor.toString();
    }

    public void setHoverColor(String hoverColor) {
        this.hoverColor = Color.web(hoverColor);
    }
}
