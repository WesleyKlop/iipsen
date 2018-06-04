package game.actions;

import game.GameState;
import game.GameStore;

/**
 * @author Wesley Klop <wesley19097@gmail.com>
 */
public class ChangeStateAction implements Action {

    private GameState newState;

    public ChangeStateAction(GameState newState) {
        this.newState = newState;
    }

    @Override
    public void executeAction(GameStore store) throws Exception {
        if (newState == null) {
            throw new Exception("New state should not be null!");
        }
        store.setGameState(newState);
    }
}
