package game.actions;

import game.GameStore;
import game.player.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Wesley Klop
 */
class RandomCardActionTest {

    @Test
    void executingActionAddsCardToPlayer() {
        var store = new GameStore();
        var player = new Player("Thierry Baudet", Color.LAVENDER);
        var action = new RandomCardAction(player);
        store.addPlayer(player);
        assertEquals(0, player.getCardStack().size());
        action.executeAction(store);
        assertEquals(1, player.getCardStack().size());
    }
}
