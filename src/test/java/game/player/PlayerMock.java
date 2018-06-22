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
    private final boolean richBoi;

    public PlayerMock(boolean isRichBoi) {
        super("Thierry Baudet", Color.LAVENDER);
        richBoi = isRichBoi;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public CardStack getCardStack() {
        return richBoi ? new InfiniteCardStack() : super.getCardStack();
    }


}
