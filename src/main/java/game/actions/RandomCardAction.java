package game.actions;

import game.GameStore;
import game.cards.Card;
import game.player.Player;

public class RandomCardAction implements Action {

    private Player player;
    private Card randomCard;

    public RandomCardAction(Player player) {
        this.player = player;
    }

    @Override
    public void executeAction(GameStore store) {
        //randomCard = store.getCardStack().getRandomCard(); TODO: add cardStack and getter to GameStore
        player.getCardStack().addCard(randomCard);
    }
}
