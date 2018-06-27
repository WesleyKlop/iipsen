package game.actions;

import game.GameStore;
import game.cards.CardStack;
import game.cards.CardType;
import game.player.Player;
import game.routecards.Route;
import game.routecards.RouteCard;
import game.routecards.RouteDoubleCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BuildRouteAction implements Action {

    private static final Logger Log = LogManager.getLogger(BuildRouteAction.class);

    private int playerId;
    private int routeId;
    private CardType cType;
    private CardStack costs;

    public BuildRouteAction(int playerId, Route route, int extraCards) {
        this.playerId = playerId;
        this.routeId = route.getId();
        cType = route.getCardType();
        costs = route.getCostsAsCardStack();
        for (int i = 0; i < extraCards; i++) {
            costs.addCard(cType);
        }
    }

    @Override
    public void executeAction(GameStore store) throws Exception {
        RouteDoubleCheck check = new RouteDoubleCheck();
        Player player = store.getPlayerById(playerId);
        Route route = store.getRouteStore().getRouteById(routeId);
        if (store.getPlayers().size() == 1) {
            if (route.getdoubleRoute() == 1) {
                boolean answer = check.checkDouble(route, store);
                System.out.println(answer);
                if (answer == true) {
                    if (player.getCardStack().containsCards(costs)) {
                        build(route, player);
                        updateRouteCards(player);
                    }
                }
            } else {
                Log.debug("Route type: {}", route.getRouteType());
                if (player.getCardStack().containsCards(costs)) {
                    build(route, player);
                    updateRouteCards(player);
                }
            }
        } else {
            Log.debug("Route type: {}", route.getRouteType());
            if (player.getCardStack().containsCards(costs)) {
                build(route, player);
                updateRouteCards(player);
            }
        }
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
