package game.actions;

import game.GameStore;
import game.cards.CardStack;
import game.cards.CardType;
import game.player.Player;
import game.player.PlayerController;
import game.routecards.Route;
import game.routecards.RouteCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BuildRouteAction implements Action {

    private static final Logger Log = LogManager.getLogger(BuildRouteAction.class);

    private int playerId;
    private int routeId;
    private CardStack costs;

    public BuildRouteAction(int playerId, Route route, int extraCards) {
        this.playerId = playerId;
        this.routeId = route.getId();
        CardType cType = route.getCardType();
        costs = route.getCostsAsCardStack();
        for (int i = 0; i < extraCards; i++) {
            costs.addCard(cType);
        }
    }

    /**
     * Build a route for the set Player, and check if the player has than completed a routecard
     *
     * @param store the GameStore to execute the action on
     * @throws Exception
     */
    @Override
    public void executeAction(GameStore store) throws Exception {
        PlayerController controller = store.getPlayerController();
        Player player = controller.getPlayerById(playerId);
        Route route = store.getRouteStore().getRouteById(routeId);
        if (player.getCardStack().containsCards(costs)) {
            build(route, player);
            updateRouteCards(player);
        }
        controller.cyclePlayerTurn();
    }

    private void updateRouteCards(Player player) {
        for (RouteCard routeCard : player.getRouteCards()) {
            Log.debug("Checking routecard {}", routeCard);
            if (routeCard.isCompleted()) {
                continue;
            }

            if (player.getConnectionKeeper().isRouteCardCompleted(routeCard)) {
                Log.debug("Awarding points for routecard");
                player.givePoints(routeCard.getValue());
                routeCard.setCompleted();
            }
        }
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }

    private void build(Route route, Player player) throws Exception {
        player.getCardStack().takeCards(costs);
        player.getConnectionKeeper().addRoute(route);
        route.setOwner(player.getId());
        player.givePoints(route.getPoints());
        player.takeTrains(route.getLength());
    }
}
