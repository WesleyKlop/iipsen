package client.ui.components;

import game.routecards.RouteCard;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * @author Wesley Klop
 */
public class PlayerRouteCard extends StackPane {
    public PlayerRouteCard(RouteCard card) {
        getChildren().add(new ImageView(card.getImagePath()));
        if (card.isCompleted()) {
            setCompleted();
        }
    }

    public void setCompleted() {
        getChildren().add(new ImageView("/images/checkmark.png"));
    }
}
