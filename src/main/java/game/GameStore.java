package game;

import game.routecards.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wesley
 */
public class GameStore implements Serializable {
    private GameState currentState = GameState.INIT;
    private List<Player> players;

    public GameStore() {
        players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void addPlayer(Player player) {
        players.add(player);
        // Calling setId after adding makes the player id start at 1
        player.setId(players.size());
    }

    public void setGameState(GameState newState) {
        currentState = newState;
    }
    //TODO
}
