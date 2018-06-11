package game.routecards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Contains methods for managing the route cards that can be picked by players.
 *
 * @author Wesley Klop
 */
public class RouteCardStack implements Serializable {
    private static final int PICKABLE_CARD_COUNT = 3;
    private final Random generator = new Random();
    private List<RouteCard> routeCards = new ArrayList<>();
    private RouteCard[] pickableCards = new RouteCard[PICKABLE_CARD_COUNT];

    public void populatePickableCards() {
        for (int i = 0; i < pickableCards.length; i++) {
            pickableCards[i] = getRandomRouteCard();
        }
    }

    private RouteCard getRandomRouteCard() {
        int index = generator.nextInt(routeCards.size());
        RouteCard card = routeCards.get(index);
        routeCards.remove(index);
        return card;
    }

    public RouteCard[] getPickableCards() {
        return pickableCards;
    }
}
