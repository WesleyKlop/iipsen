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
     * @param route the route to check the neighbour of
     * @param store store to find the other route in
     * @return boolean true if the other route has an owner
     */
    public boolean checkDouble(Route route, GameStore store) {
        return store.getRouteStore().getRouteById(route.getcoupleId()).hasOwner();
    }

    /**
     * Here it checks if couple of the double route is owned by the same player that is making the build request
     *
     * @param route the route to check the neighbour of
     * @param store store to find the other route in
     * @param player the player who may not already own the other route
     * @return true if the player does own the other route as well, or false
     */

    public boolean checkSameOwner(Route route, GameStore store, Player player) {
        return store.getRouteStore().getRouteById(route.getcoupleId()).getOwner() == player.getId();
    }
}

