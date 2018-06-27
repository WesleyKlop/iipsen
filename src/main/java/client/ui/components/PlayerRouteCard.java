package client.ui.components;

import game.routecards.RouteCard;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * @author Wesley Klop
 */
public class PlayerRouteCard extends StackPane {
    private final RouteCard routeCard;

    public PlayerRouteCard(RouteCard card, EventHandler<MouseEvent> enterHandler, EventHandler<MouseEvent> exitHandler) {
        this.setOnMouseEntered(enterHandler);
        this.setOnMouseExited(exitHandler);
        this.routeCard = card;
        getChildren().add(new ImageView(card.getImagePath()));
        if (card.isCompleted()) {
            setCompleted();
        }
    }

    public void setCompleted() {
        getChildren().add(new ImageView("/images/checkmark.png"));
    }

    public RouteCard getRouteCard() {
        return routeCard;
    }
}
