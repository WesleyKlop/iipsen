package client.ui.mainmenu;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 */
public class MainMenuLoadController {

    private EventHandler<MouseEvent> eventHandler;

    public void setOnLoadClicked(EventHandler<MouseEvent> handler) {
        eventHandler = handler;
    }

    public void onLoadButtonClicked(MouseEvent mouseEvent) {
        eventHandler.handle(mouseEvent);
    }
}
