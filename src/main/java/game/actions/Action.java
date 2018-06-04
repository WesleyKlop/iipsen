package game.actions;

import game.GameStore;

import java.io.Serializable;

/**
 * @author Wesley Klop <wesley19097@gmail.com>
 */
public interface Action extends Serializable {
    void executeAction(GameStore store) throws Exception;
}
