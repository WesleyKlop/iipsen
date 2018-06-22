package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author wesley
 */
class GameStoreTest {

    @Test
    void testGameState() {
        var store = new GameStore("");
        var finishedGameState = GameState.FINISHED;

        assertNull(store.getGameState());
        store.setGameState(finishedGameState);
        assertEquals(finishedGameState, store.getGameState());
    }
}
