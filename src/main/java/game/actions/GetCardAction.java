package game.actions;

import game.GameStore;
import game.cards.Card;
import game.player.Player;

public class GetCardAction implements Action {

    private Player player;
    private Card card;
    private int index;

    public GetCardAction(Player player, int index) {
        this.player = player;
        this.index = index;
    }

    @Override
    public void executeAction(GameStore store) {
        if (index == 0) {
            //card = store.getCardStackController().getRandomCard();
        } else {
            //card = store.getCardStackController().getCardAtIndex(index-1);
        }
        player.getCardStack().addCard(card);
    }
}
