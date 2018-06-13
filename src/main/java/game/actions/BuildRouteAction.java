package game.actions;

import game.GameStore;
import game.cards.CardType;
import game.location.Location;
import game.player.Player;
import game.routecards.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BuildRouteAction implements Action {

    private static final Logger Log = LogManager.getLogger(BuildRouteAction.class);

    private Route route;
    private Player player;
    private Location location1;
    private Location location2;

    public BuildRouteAction(Player player, Location location1, Location location2) {
        this.player = player;
        this.route = location1.getRouteToLocation(location2.getLocation());
        this.location1 = location1;
        this.location2 = location2;
    }

    @Override
    public void executeAction(GameStore store) {
        if (player.getCardStack().containsCards(route.getType(), route.getCartCost()) &&
            player.getCardStack().containsCards(CardType.LOCOMOTIVE, route.getLocomotiveCost())) {
            Log.debug("Player has enough cards! player id: {}", player.getId());
            try {
                if (!route.hasOwner()) {
                    Log.debug("Route doesn't have an owner, time to pay up!");
                    player.getCardStack().takeCards(route.getType(), route.getCartCost());
                    player.getCardStack().takeCards(CardType.LOCOMOTIVE, route.getLocomotiveCost());
                    player.removeTrainCarts(route.getCartCost());
                    Log.debug("Payment is accepted, granting ownership to " + player.getPlayerName());
                    route.setOwner(player.getId());
                    Log.debug("Player: " + player.getPlayerName() + " is now the proud owner of this route!");
                    player.getConnectionKeeper().addLocations(location1.getLocation(), location2.getLocation());
                }
            } catch (Exception e) {
                Log.debug("Whoops, looks like something went wrong!");
                Log.error("Exception found: " + e.toString());
            }
        } else {
            Log.debug("Player doesn't have enough cards");
        }
    }
}
