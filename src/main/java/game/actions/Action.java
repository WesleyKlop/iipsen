package game.actions;

import game.GameStore;

import java.io.Serializable;

/**
 * @author wesley
 */
public interface Action extends Serializable {
    void executeAction(GameStore store) throws Exception;
}
