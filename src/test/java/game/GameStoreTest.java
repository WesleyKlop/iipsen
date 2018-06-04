package game;

import game.player.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wesley
 */
class GameStoreTest {
    @Test
    void addingAPlayerIncreasesPlayerListSize() {
        var store = new GameStore();
        assertEquals(0, store.getPlayers().size());
        var player = new Player("Thierry Baudet", Color.LAVENDER);
        store.addPlayer(player);
        assertEquals(1, store.getPlayers().size());
        assertEquals(player, store.getPlayers().get(0));
    }

    @Test
    void addingAPlayerGivesPlayerAnId() {
        var store = new GameStore();
        var player = new Player("Thierry Baudet", Color.LAVENDER);
        assertEquals(0, player.getId());
        store.addPlayer(player);
        assertEquals(1, player.getId());
    }

    @Test
    void testGameState() {
        var store = new GameStore();
        var initialGameState = GameState.INIT;
        var finishedGameState = GameState.FINISHED;

        assertEquals(initialGameState, store.getGameState());
        store.setGameState(finishedGameState);
        assertEquals(finishedGameState, store.getGameState());
    }
}