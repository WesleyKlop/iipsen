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
        // Generate int for player id
        player.setId(players.size());
        players.add(player);
    }
    //TODO
}
