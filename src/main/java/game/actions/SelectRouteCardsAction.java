package game.actions;

import game.GameStore;
import game.routecards.RouteCard;

/**
 * @author Wesley Klop
 */
public class SelectRouteCardsAction implements Action {
    private final int playerId;
    private final RouteCard[] cards;

    public SelectRouteCardsAction(int playerId, RouteCard[] cards) {
        this.playerId = playerId;
        this.cards = cards;
    }

    @Override
    public void executeAction(GameStore store) throws Exception {
        if (cards.length < 1) {
            throw new Exception("Didn't pick enough cards, should be 1 to 3 (or 5)");
        }
        var player = store.getPlayerById(playerId);

        for (RouteCard card : cards) {
            player.addRouteCard(card);
        }
        //store.getRouteCardStack().populatePickableCards();
    }
}
