package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wesley
 */
class GameStoreTest {

    @Test
    void testGameState() {
        var store = new GameStore();
        var initialGameState = GameState.LOBBY;
        var finishedGameState = GameState.FINISHED;

        assertEquals(initialGameState, store.getGameState());
        store.setGameState(finishedGameState);
        assertEquals(finishedGameState, store.getGameState());
    }
}
