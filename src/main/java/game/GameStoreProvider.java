package game;

import client.GameStoreClient;
import game.actions.Action;
import util.Observable;

import java.rmi.RemoteException;

/**
 */
public class GameStoreProvider {
    private static final Observable<GameStore> instance = new Observable<>();
    private static GameStoreClient sender;

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
        sender.sendAction(action);
    }
}
