package game.actions;

import game.GameStore;
import game.player.Player;
import javafx.scene.paint.Color;

/**
 * Action for adding a player to the game.
 *
 * @author Wesley Klop
 */
public class AddPlayerAction implements Action {
    /**
     * The player object to add.
     */
    private Player player;

    /**
     * Creates an AddPlayerAction which instantiates a Player object internally.
     *
     * @param name the player name
     * @param color the player color
     * @see Player
     */
    public AddPlayerAction(String name, Color color) {
        this.player = new Player(name, color);
    }

    /**
     * Add a player to the player list
     * @param store the GameStore the player should be added to.
     */
    @Override
    public void executeAction(GameStore store) {
        store.getPlayers().add(player);
        player.setId(store.getPlayers().size());
        for (int i = 0; i < 4; i++) {
            player.getCardStack().addCard(store.getCardStackController().getRandomCard());
        }
    }

    @Override
    public int getPlayerId() {
        return -1;
    }
}
