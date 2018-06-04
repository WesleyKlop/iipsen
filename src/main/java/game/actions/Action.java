package game.actions;

import game.GameStore;

import java.io.Serializable;

/**
 */
public interface Action extends Serializable {
    void executeAction(GameStore store) throws Exception;
}
