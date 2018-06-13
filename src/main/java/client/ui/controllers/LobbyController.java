package client.ui.controllers;

import client.ui.views.LobbyView;
import game.GameState;
import game.GameStoreProvider;
import game.actions.ChangeStateAction;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;

/**
 * @author Wesley Klop
 */
public class LobbyController {
    private static final Logger Log = LogManager.getLogger(LobbyController.class);

    public LobbyController(LobbyView view) {
        GameStoreProvider.getInstance().addObserver(gameState -> {
            Log.debug("List changed, new size: {}", gameState.getPlayers().size());
            // TODO: This could be optimized
            // Maybe compare array size and add/remove based on that
            view.updateView(gameState.getPlayers());
        });
    }

    public String contrastCalculator(Color color) {
        double brightness = color.getBrightness();
        double hue = color.getHue();
        double saturation = color.getSaturation();

        brightness = (brightness < 0.5) ? brightness + 0.5 : brightness - 0.5;

        color = Color.hsb(hue, saturation, brightness);

        return String.format("#%02X%02X%02X",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255));
    }

    public void onStartButtonClicked() throws RemoteException {
        Log.debug("Starting game");
        var action = new ChangeStateAction(GameState.GAME);
        Log.debug("Changing to GameState.GAME");
        GameStoreProvider.sendAction(action);
    }
}
