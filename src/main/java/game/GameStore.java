package game;

import game.routecards.Player;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wesley
 */
public class GameStore implements Serializable {
    private GameState currentState = GameState.INIT;
    private List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String name, Color color) {
        for (Player player : players) {
            if (player.getPlayerName().equals(name) && player.getColor() == color) {
                return player;
            }
        }
        return null;
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
