package client.ui.controllers;

import client.ui.views.LobbyView;
import game.GameState;
import game.GameStore;
import game.GameStoreProvider;
import game.actions.ChangeStateAction;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Observable;

import java.rmi.RemoteException;

/**
 * @author Wesley Klop
 */
public class LobbyController implements Observable.Observer<GameStore> {
    private static final Logger Log = LogManager.getLogger(LobbyController.class);

    private final LobbyView view;

    public LobbyController(LobbyView view) {
        this.view = view;
        GameStoreProvider.getInstance().addObserver(this);
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
        GameStoreProvider.getInstance().removeObserver(this);
        Log.debug("Starting game");
        var action = new ChangeStateAction(GameState.GAME);
        Log.debug("Changing to GameState.GAME");
        GameStoreProvider.sendAction(action);
    }

    @Override
    public void onUpdate(GameStore gameStore) {
        Log.debug("List changed, new size: {}", gameStore.getPlayers().size());
        // TODO: This could be optimized
        // Maybe compare array size and add/remove based on that
        view.updateView(gameStore.getPlayers());
    }
}
