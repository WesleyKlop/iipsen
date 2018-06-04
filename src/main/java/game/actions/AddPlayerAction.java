package game.actions;

import game.GameStore;
import game.player.Player;
import javafx.scene.paint.Color;

/**
 * @author Wesley Klop <wesley19097@gmail.com>
 */
public class AddPlayerAction implements Action {
    private Player player;

    public AddPlayerAction(String name, Color color) {
        this.player = new Player(name, color);
    }

    @Override
    public void executeAction(GameStore state) {

        state.addPlayer(player);
    }
}
