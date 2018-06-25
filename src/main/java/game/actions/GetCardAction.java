package game.actions;

import game.GameStore;
import game.cards.Card;
import game.player.PlayerController;

/**
 * @author Thom
 */
public class GetCardAction implements Action {

    private int playerId;
    private int[] index;

    /**
     * This action gives the player 1 card at random, or from the open card stack.
     *
     * @param playerId The player id that is executing the action
     * @param index    MAX:5 (0 = random, 1 - 5 = open)
     */
    public GetCardAction(int playerId, int[] index) {
        this.playerId = playerId;
        this.index = index;
    }

    @Override
    public void executeAction(GameStore store) {
        Card card;
        PlayerController controller = store.getPlayerController();
        for (int i = 0; i < 2; i++) {
            if (index[i] == 0) {
                card = store.getCardStackController().getRandomCard();
            } else {
                card = store.getCardStackController().getOpenCard(index[i] - 1);
            }
            controller.getPlayerById(playerId).getCardStack().addCard(card);
        }
        controller.cyclePlayerTurn();
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }
}
