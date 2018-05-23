package client.ui;

import client.ui.PreferencesController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleButton extends Circle {

    public CircleButton(Color color, EventHandler eventHandler){
        super();
        setFill(color);
        setOnMouseClicked(e -> {

        });
    }

}
