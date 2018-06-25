package game.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wesley Klop
 */
public class PlayerController implements Serializable {
    private List<Player> players = new ArrayList<>();

    private int currentTurn = 0;
    private int finalTurn = -1;

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public void cyclePlayerTurn() {
        currentTurn = (currentTurn + 1) % players.size();
    }

    public int getCurrentTurn() {
        return currentTurn + 1;
    }

    public int getFinalTurn() {
        return finalTurn;
    }

    public void setLastTurn(int lastTurn) {
        this.finalTurn = lastTurn;
    }

    public boolean shouldGoToLastTurn(int playerId) {
        return getPlayerById(playerId).getTraincarts() <= 2 && finalTurn == -1;
    }
}
