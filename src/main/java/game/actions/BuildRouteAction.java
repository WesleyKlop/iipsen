package game.actions;

import client.ui.MessagesControllerProvider;
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
        cType = route.getCardType();
        costs = route.getCostsAsCardStack();
    }

    @Override
    public void executeAction(GameStore store) throws Exception {
        Player player = store.getPlayerById(playerId);
        RouteType type = route.getRouteType();
        int extraCosts = 0;
        if (type.toString().equalsIgnoreCase("tunnel")) {
            for (int i = 0; i < 3; i++) {
                if (store.getCardStackController().getRandomCard().getCardType() == cType) {
                    costs.addCard(cType);
                    extraCosts++;
                }
            }
            MessagesControllerProvider.getMessageController().setBuildRouteWarning("Extra costs for tunnel: " + extraCosts);
        }

        if (player.getCardStack().containsCards(costs)) {
            build(route, player);
        }

        store.cyclePlayerTurn();
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }

    private void build(Route route, Player player) throws Exception {
        player.getCardStack().takeCards(costs);
        route.setOwner(player.getId());
        player.givePoints(route.getPoints());
        player.takeTrains(route.getLength());
    }
}
