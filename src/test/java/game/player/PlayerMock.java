package game.player;

import game.cards.CardStack;
import game.cards.InfiniteCardStack;
import javafx.scene.paint.Color;

/**
 * Shoutout naar FvD.
 *
 * @author Wesley Klop
 */
public class PlayerMock extends Player {
    public PlayerMock() {
        super("Thierry Baudet", Color.LAVENDER);
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public CardStack getCardStack() {
        return new InfiniteCardStack();
    }


}
