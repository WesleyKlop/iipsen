package game.actions;

import game.GameStore;

import java.io.Serializable;

/**
 * Action interface should be used for creating Actions that can be send to the server.
 * These actions have one method which takes GameStore as an argument.
 *
 * @author Wesley Klop
 */
public interface Action extends Serializable {
    /**
     * Execute the action on GameStore
     *
     * @param store the GameStore to execute the action on
     * @throws Exception when something goes wrong. The store <b>should</b> not be changed when an Exception is thrown.
     */
    void executeAction(GameStore store) throws Exception;

    int getPlayerId();
}
