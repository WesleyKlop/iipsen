package game;

import client.GameStateClient;
import game.actions.Action;
import util.Observable;

import java.rmi.RemoteException;

/**
 * @author wesley
 */
public class GameStoreProvider {
    private static final Observable<GameStore> instance = new Observable<>(new GameStore());
    private static GameStateClient sender;

    public GameStoreProvider(GameStateClient sender) {
        GameStoreProvider.sender = sender;
    }

    private GameStoreProvider() {
    }

    public static Observable<GameStore> getInstance() {
        return instance;
    }

    public static void sendAction(Action action) throws RemoteException {
        sender.sendAction(action);
    }
}
