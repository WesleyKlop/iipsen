package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wesley
 */
class GameStoreTest {

    @Test
    void testGameState() {
        var store = new GameStore("");
        var finishedGameState = GameState.FINISHED;

        assertEquals(GameState.INIT, store.getGameState());
        store.setGameState(finishedGameState);
        assertEquals(finishedGameState, store.getGameState());
    }
}
