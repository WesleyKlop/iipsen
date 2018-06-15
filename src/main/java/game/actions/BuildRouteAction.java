package game.actions;

import game.GameStore;
import game.cards.CardStack;
import game.cards.CardType;
import game.player.Player;
import game.routecards.Route;
import game.routecards.RouteType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BuildRouteAction implements Action {

    private static final Logger Log = LogManager.getLogger(BuildRouteAction.class);

    private int playerId;
    private Route route;
    private CardType cType;
    private CardStack costs;

    public BuildRouteAction(int playerId, Route route) {
        this.playerId = playerId;
        this.route = route;
        cType = route.getType();
        costs = route.getCostsAsCardStack();
    }

    @Override
    public void executeAction(GameStore store) {
        Player player = store.getPlayerById(playerId);
        RouteType type = route.getRouteType();
        if (type.toString().equalsIgnoreCase("tunnel")) {
            for (int i = 0; i < 3; i++) {
                if (store.getCardStackController().getRandomCard().getCardType() == cType) {
                    costs.addCard(cType);
                }
            }
            if (player.getCardStack().containsCards(costs)) {
                //TODO Message player system
                //MESSAGE PLAYER EXTRA COSTS
                build(route, player);
            } else {
                //MESSAGE PLAYER EXTRA COSTS
            }
        } else {
            build(route, player);
        }
    }

    private void build(Route route, Player player) {
        try {
            player.getCardStack().takeCards(costs);
            route.setOwner(player.getId());
        } catch (Exception e) {
            Log.error(e.toString());
        }
    }
}
