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


public class RouteCardStackBank implements Serializable {
    private final Random generator = new Random();
    private List<RouteCard> routeCards = new ArrayList<>();

    public RouteCardStackBank() {
        //fillRouteBank(); TODO: Way to fill the routeCardStackBank
    }

    public RouteCard getRandomRouteCard() {
        int index = generator.nextInt(routeCards.size());
        RouteCard card = routeCards.get(index);
        routeCards.remove(index);
        return card;
    }
}