package game;

import game.player.Player;
import game.routecards.LocationStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class GameStore implements Serializable {
    private GameState gameState = GameState.INIT;
    private List<Player> players = new ArrayList<>();
    private LocationStore locationStore = new LocationStore();

    public GameStore() {
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public LocationStore getLocationStore() {
        return locationStore;
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
