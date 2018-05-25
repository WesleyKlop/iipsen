package game.actions;

import game.GameStore;

/**
 * @author wesley
 */
public interface Action {
    void executeAction(GameStore state) throws Exception;
}
