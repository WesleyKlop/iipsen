package game;

import client.GameStoreClient;
import game.actions.Action;
import game.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Observable;

import java.rmi.RemoteException;

/**
 * Singleton to give all views access tot the current GameStore so they can subscribe to changes
 *
 * @author Wesley Klop
 */
public class GameStoreProvider {
    private static final Logger Log = LogManager.getLogger(GameStoreProvider.class);
    private static final Observable<GameStore> instance = new Observable<>();
    private static GameStoreClient sender;
    // Player that corresponds to the client

    public static Player getPlayer() {
        try {
            return sender.getPlayer();
        } catch (RemoteException e) {
            Log.error("Failed to get player from sender..", e);
            return null;
        }
    }

    public static void setPlayer(Player player) {
        try {
            sender.setPlayer(player);
        } catch (RemoteException e) {
            Log.catching(e);
        }
    }

    private GameStoreProvider() {
    }

    public static void setSender(GameStoreClient sender) {
        GameStoreProvider.sender = sender;
    }

    public static Observable<GameStore> getInstance() {
        return instance;
    }

    public static GameStore getStore() {
        return instance.getValue();
    }

    public static void sendAction(Action action) throws RemoteException {
        var store = instance.getValue();
        if (store.getGameState() != GameState.GAME || action.getPlayerId() == store.getPlayerController().getCurrentTurn()) {
            sender.sendAction(action);
        }
    }
}
