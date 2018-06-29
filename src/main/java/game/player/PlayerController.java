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

    /**
     * Returns the player object with the given ID
     *
     * @param id the id of the player to find
     * @return the associated Player object or null when the player is not found
     */
    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    /**
     * Cycles the turn to the next player
     */
    public void cyclePlayerTurn() {
        currentTurn = (currentTurn + 1) % players.size();
    }

    /**
     * Returns the id of the player who's turn it is
     * @return the id of the current player
     */
    public int getCurrentTurn() {
        return currentTurn + 1;
    }

    public int getFinalTurn() {
        return finalTurn;
    }

    /**
     * Sets the property to the id of the player who will get the last turn.
     * This player will get one final turn before it goes to the end screen
     * @param lastTurn the player who gets the last turn
     */
    public void setLastTurn(int lastTurn) {
        this.finalTurn = lastTurn;
    }

    /**
     * Checks if the playerId has the properties to continue to the last turn.
     * @param playerId the player to check
     * @return true if the we should set the last turn prop or false
     */
    public boolean shouldGoToLastTurn(int playerId) {
        return getPlayerById(playerId).getTrainCarts() <= 2 && finalTurn == -1;
    }

    /**
     * Set hasClient property to false on all players, so we can associate a new one to them
     */
    public void removeClients() {
        for (Player player : players) {
            player.setHasClient(false);
        }
    }
}
