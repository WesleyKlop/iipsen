package game.actions;

import game.GameState;
import game.GameStore;

/**
 * ChangeStateAction changes the GameState on a GameStore object
 */
public class ChangeStateAction implements Action {

    /**
     * The new GameState
     */
    private GameState newState;

    /**
     * Creates a new ChangeStateAction with the given GameState to change to
     *
     * @param newState the new GameState
     */
    public ChangeStateAction(GameState newState) {
        this.newState = newState;
    }

    /**
     * Sets the GameState on GameStore
     * @param store the GameStore to execute the action on
     */
    @Override
    public void executeAction(GameStore store) throws NullPointerException {
        if (newState == null) {
            throw new NullPointerException("New state should not be null!");
        }
        store.setGameState(newState);
    }
}
