package game.routecards;

import game.location.ELocation;
import game.location.Location;
import game.location.LocationStore;
import game.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wesley Klop
 */
public class RouteCardPathFinder {
    private static final Logger Log = LogManager.getLogger(RouteCardPathFinder.class);
    private LocationStore store;
    private Set<Route> walkedRoutes = new HashSet<>(); // This is used so we don't walk over the same route twice.

    public RouteCardPathFinder(LocationStore store) {
        this.store = store;
    }

    /**
     * Takes a player and routecard, and walks all connections starting from the starting position of the card.
     *
     * @param player the player to check routes for
     * @param card the routecard to be completed
     * @return
     */
    public boolean solveForRouteCard(Player player, RouteCard card) {
        var startLocation = store.getLocation(card.getStart());

        // Clear the set before starting
        walkedRoutes.clear();
        Log.info("Starting pathFinder, need to go from {} to {}", card.getStart(), card.getEnd());
        return checkConnections(player, startLocation, card.getEnd());
    }

    /**
     * Walks all connections on a given location
     * It will then check if the player is the owner of a connection, and if it's not yet walked.
     * If it succeeds these checks it will get the Location thats on the other side of the route and
     * call this function again.
     *
     * @param player the player to check routes for
     * @param location the location to pull connections off
     * @param finish the location we are looking for
     * @return true if we found the finish, recursively
     */
    private boolean checkConnections(Player player, Location location, ELocation finish) {
        for (var entry : location.getConnections().entrySet()) {
            ELocation connectingLocation = entry.getKey();
            Route route = entry.getValue();
            Log.debug(route);

            if (route.getOwner() != player.getId() || walkedRoutes.contains(route)) {
                // Go the the next connection if this route is not owned by the player or has already been walked.
                Log.warn("This route has already been walked.");
                continue;
            }

            walkedRoutes.add(route);

            // Get the connecting location
            Log.debug("Walking route owned by player, connects {} to {}", location.getLocation(), connectingLocation);
            // If this is the final location we can return true! :D
            if (connectingLocation == finish) {
                Log.debug("Found end location!!!");
                return true;
            }

            return checkConnections(player, store.getLocation(connectingLocation), finish);
        }
        return false;
    }
}

/* __/\\\________/\\\_______________________________________________________________/\\\\\\\\\________________________________________________________________________________________________________/\\\__
 *  _\/\\\_______\/\\\__________________________________/\\\_______________________/\\\\\\\\\\\\\_____________________________________________________________________________________________________\/\\\__
 *   _\/\\\_______\/\\\_________________________________\/\\\______________________/\\\/////////\\\____/\\\\\\\\\_____/\\\\\\\\\_______________________________________________________________________\/\\\__
 *    _\/\\\\\\\\\\\\\\\______/\\\\\\\\____/\\/\\\\\\____\/\\\\\\\\________________\/\\\_______\/\\\___/\\\/////\\\___/\\\/////\\\___/\\/\\\\\\\_______/\\\\\______/\\\____/\\\______/\\\\\\\\__________\/\\\__
 *     _\/\\\/////////\\\____/\\\/////\\\__\/\\\////\\\___\/\\\////\\\______________\/\\\\\\\\\\\\\\\__\/\\\\\\\\\\___\/\\\\\\\\\\___\/\\\/////\\\____/\\\///\\\___\//\\\__/\\\_____/\\\/////\\\____/\\\\\\\\\__
 *      _\/\\\_______\/\\\___/\\\\\\\\\\\___\/\\\__\//\\\__\/\\\\\\\\/_______________\/\\\/////////\\\__\/\\\//////____\/\\\//////____\/\\\___\///____/\\\__\//\\\___\//\\\/\\\_____/\\\\\\\\\\\____/\\\////\\\__
 *       _\/\\\_______\/\\\__\//\\///////____\/\\\___\/\\\__\/\\\///\\\_______________\/\\\_______\/\\\__\/\\\__________\/\\\__________\/\\\__________\//\\\__/\\\_____\//\\\\\_____\//\\///////____\/\\\__\/\\\__
 *        _\/\\\_______\/\\\___\//\\\\\\\\\\__\/\\\___\/\\\__\/\\\_\///\\\_____________\/\\\_______\/\\\__\/\\\__________\/\\\__________\/\\\___________\///\\\\\/_______\//\\\_______\//\\\\\\\\\\__\//\\\\\\\/\\_
 *         _\///________\///_____\//////////___\///____\///___\///____\///______________\///________\///___\///___________\///___________\///______________\/////__________\///_________\//////////____\///////\//__
 */
