package game;

import game.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wesley Klop <wesley19097@gmail.com>
 */
public class GameStore implements Serializable {
    private GameState gameState = GameState.INIT;
    private List<Player> players = new ArrayList<>();

    public GameStore() {
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void addPlayer(Player player) {
        players.add(player);
        // Calling setId after adding makes the player id start at 1
        player.setId(players.size());
    }

    public void setGameState(GameState newState) {
        gameState = newState;
    }
    //TODO
}
