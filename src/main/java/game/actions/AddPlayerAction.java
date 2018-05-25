package game.actions;

import game.GameStore;
import game.routecards.Player;
import javafx.scene.paint.Color;

/**
 * @author wesley
 */
public class AddPlayerAction implements Action {
    private Player player;

    public AddPlayerAction(String name, Color color) {
        this.player = new Player(name, color);
    }

    @Override
    public void executeAction(GameStore state) throws Exception {
        if (player == null) {
            throw new Exception("Player should not be null!");
        }

        state.addPlayer(player);
    }
}
