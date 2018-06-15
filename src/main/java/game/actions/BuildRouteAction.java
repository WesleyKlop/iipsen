package game.actions;

import game.GameStore;
import game.cards.CardStack;
import game.location.ELocation;
import game.player.Player;
import game.routecards.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BuildRouteAction implements Action {

    private static final Logger Log = LogManager.getLogger(BuildRouteAction.class);

    private Player player;
    private int id;

    public BuildRouteAction(Player player, int id) {
        this.player = player;
        this.id = id;
    }

    @Override
    public void executeAction(GameStore store) {
        Route route = store.getRouteStore().getRouteById(id);
        ELocation[] locs = route.getLocations();
        CardStack costs = route.getCostsAsCardStack();

        if (!player.getCardStack().containsCards(costs)) {
            Log.debug("Player doesn't have enough cards");
        }

        Log.debug("Player has enough cards! player id: {}", player.getId());
        try {
            Log.debug("Route doesn't have an owner, time to pay up!");
            player.getCardStack().takeCards(costs);
            player.removeTrainCarts(route.getCartCost());
            Log.debug("Payment is accepted, granting ownership to " + player.getPlayerName());
            route.setOwner(player.getId());
            Log.debug("Player: " + player.getPlayerName() + " is now the proud owner of this route!");
            player.getConnectionKeeper().addLocations(locs[0], locs[1]);
        } catch (Exception e) {
            Log.debug("Whoops, looks like something went wrong!");
            Log.error("Exception found: ", e);
        }
    }
}
