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
    private static GameStoreClient client;

    private GameStoreProvider() {
    }
    // Player that corresponds to the client

    public static GameStoreClient getClient() {
        return client;
    }

    public static void setClient(GameStoreClient client) {
        GameStoreProvider.client = client;
    }

    public static Player getPlayer() {
        try {
            return client.getPlayer();
        } catch (RemoteException e) {
            Log.error("Failed to get player from client..", e);
            return null;
        }
    }

    public static void setPlayer(Player player) {
        try {
            client.setPlayer(player);
        } catch (RemoteException e) {
            Log.catching(e);
        }
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
            client.sendAction(action);
        }
    }
}
