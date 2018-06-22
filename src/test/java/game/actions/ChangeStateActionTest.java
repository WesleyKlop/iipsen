package game.actions;

import game.GameState;
import game.GameStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wesley
 */
class ChangeStateActionTest {
    private Action testAction;

    @BeforeEach
    void setUp() {
        testAction = new ChangeStateAction(GameState.GAME);
    }

    @Test
    void canExecuteAction() {
        var store = new GameStore("");
        assertDoesNotThrow(() -> testAction.executeAction(store));
    }

    @Test
    void executingActionChangesState() {
        var store = new GameStore("");
        assertNull(store.getGameState());
        assertDoesNotThrow(() -> testAction.executeAction(store));
        assertEquals(GameState.GAME, store.getGameState());
    }

    @Test
    void throwsWhenActionIsNull() {
        var action = new ChangeStateAction(null);
        var store = new GameStore("");
        assertThrows(Exception.class, () -> action.executeAction(store));
    }
}
