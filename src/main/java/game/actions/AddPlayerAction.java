package game.actions;

import game.GameStore;
import game.routecards.Player;

/**
 * @author wesley
 */
public class AddPlayerAction implements Action {
    private Player player;

    public AddPlayerAction(Player player) {
        this.player = player;
    }

    @Override
    public void executeAction(GameStore state) throws Exception {
        if (player == null) {
            throw new Exception("Player should not be null!");
        }
        state.getPlayers().add(player);
    }
}
