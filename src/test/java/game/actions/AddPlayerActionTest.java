package game.actions;

import game.GameStore;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wesley
 */
class AddPlayerActionTest {

    private AddPlayerAction getPlayerAction() {
        return new AddPlayerAction("Thierry Baudet", Color.LAVENDER);
    }

    @Test
    void canCreateAction() {
        assertDoesNotThrow(this::getPlayerAction);
    }

    @Test
    void canExecuteAction() {
        var action = getPlayerAction();
        var store = new GameStore();
        assertDoesNotThrow(() -> action.executeAction(store));
    }

    @Test
    void executingActionAddsPlayerToList() {
        var action = getPlayerAction();
        var store = new GameStore();
        assertEquals(0, store.getPlayers().size(), "Players in store should be 0");
        assertDoesNotThrow(() -> action.executeAction(store), "Executing action threw an Exception");
        var addedPlayer = store.getPlayers().get(0);
        assertEquals(1, store.getPlayers().size(), "Players in store should be 1");
        assertEquals(1, addedPlayer.getId(), "Player id");
        assertEquals("Thierry Baudet", addedPlayer.getPlayerName(), "Player name was wrong");
        assertEquals(Color.LAVENDER.toString(), addedPlayer.getColor(), "Player color was wrong");
    }
}
