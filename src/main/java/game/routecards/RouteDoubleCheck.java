package game.routecards;

import game.GameStore;
import game.player.Player;

/**
 * Here are the methods that get used before an build action on a double route
 *
 * @author ewout
 */
public class RouteDoubleCheck {

    /**
     * Here it checks if the couple of the route has an owner
     *
     * @param route
     * @param store
     * @return boolean
     * @author ewout
     */
    public boolean checkDouble(Route route, GameStore store) {
        return store.getRouteStore().getRouteById(route.getcoupleId()).hasOwner();
    }

    /**
     * Here it checks if couple of the double route is owned by the same player that is making the build request
     *
     * @param route
     * @param store
     * @param player
     * @return
     * @author ewout
     */

    public boolean checkSameOwner(Route route, GameStore store, Player player) {
        return store.getRouteStore().getRouteById(route.getcoupleId()).getOwner() == player.getId();
    }
}

