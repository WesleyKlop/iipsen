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
    private List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void startGame() {
        currentState = GameState.GAME;
    }

    public void goToLobby() {
        currentState = GameState.LOBBY;
    }

    public GameState getCurrentState() {
        return currentState;
    }
    //TODO
}
