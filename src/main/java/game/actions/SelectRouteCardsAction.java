package game.actions;

import game.GameStore;
import game.player.PlayerController;
import game.routecards.RouteCard;

import java.util.List;

/**
 * @author Wesley Klop
 */
public class SelectRouteCardsAction implements Action {
    private final int playerId;
    private final List<RouteCard> cards;

    public SelectRouteCardsAction(int playerId, List<RouteCard> cards) {
        this.playerId = playerId;
        this.cards = cards;
    }

    @Override
    public void executeAction(GameStore store) {
        PlayerController controller = store.getPlayerController();
        var player = controller.getPlayerById(playerId);
        for (RouteCard card : cards) {
            player.addRouteCard(card);
        }
        store.getSelectableRouteCards().populatePickableCards();
        controller.cyclePlayerTurn();
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }
}
