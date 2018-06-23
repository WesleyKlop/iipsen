package game.actions;

import client.ui.MessagesControllerProvider;
import game.GameStore;
import game.cards.CardStack;
import game.cards.CardType;
import game.player.Player;
import game.routecards.Route;
import game.routecards.RouteCard;
import game.routecards.RouteType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BuildRouteAction implements Action {

    private static final Logger Log = LogManager.getLogger(BuildRouteAction.class);

    private int playerId;
    private int routeId;
    private CardType cType;
    private CardStack costs;

    public BuildRouteAction(int playerId, Route route) {
        this.playerId = playerId;
        this.routeId = route.getId();
        cType = route.getCardType();
        costs = route.getCostsAsCardStack();
    }

    @Override
    public void executeAction(GameStore store) throws Exception {
        Player player = store.getPlayerById(playerId);
        Route route = store.getRouteStore().getRouteById(routeId);
        int extraCosts = 0;
        Log.debug("Route type: {}", route.getRouteType());
        if (route.getRouteType() == RouteType.TUNNEL) {
            for (int i = 0; i < 3; i++) {
                if (store.getCardStackController().getRandomCard().getCardType() == cType) {
                    costs.addCard(cType);
                    extraCosts++;
                }
            }
            Log.info("Extra costs for tunnel are {}", extraCosts);
            // FIXME this does not work over rmi
            try {
                MessagesControllerProvider.getMessageController().setBuildRouteWarning("Extra costs for tunnel: " + extraCosts);
            } catch (NullPointerException ex) {
                Log.error("NullPointerError showing BuildRouteWarning... (BuildRouteAction.java:43)");
            }
        }

        build(route, player);
        updateRouteCards(player);

        store.cyclePlayerTurn();
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
