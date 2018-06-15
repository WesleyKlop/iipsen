package game.actions;

import game.GameStore;
import game.cards.Card;
import game.player.Player;

/**
 * @author Thom
 */
public class GetCardAction implements Action {

    private Player player;
    private Card card;
    private int index;

    /**
     * This action gives the player 1 card at random, or from the open card stack.
     *
     * @param player The player that is executing the action
     * @param index  MAX:5 (0 = random, 1 - 5 = open)
     */
    public GetCardAction(Player player, int index) {
        this.player = player;
        this.index = index;
    }

    @Override
    public void executeAction(GameStore store) {
        if (index == 0) {
            card = store.getCardStackController().getRandomCard();
        } else {
            card = store.getCardStackController().getOpenCard(index - 1);
        }
        player.getCardStack().addCard(card);
    }
}
