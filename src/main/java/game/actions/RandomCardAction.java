package game.actions;

import game.GameStore;
import game.cards.Card;
import game.player.Player;

public class RandomCardAction implements Action {

    private Player player;

    public RandomCardAction(Player player) {
        this.player = player;
    }

    @Override
    public void executeAction(GameStore store) {
        Card randomCard = store.getCardStackController().getRandomCard();
        player.getCardStack().addCard(randomCard);
    }
}
