package client;

import game.GameState;
import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import server.GameStoreServer;
import server.Server;
import util.Observable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author wesley
 */
public class GameClient extends UnicastRemoteObject implements GameStoreClient {

    // Transient because we don't want to send this to the server
    private transient GameStoreServer server;
    private transient Observable<GameStore> storeObservable = GameStoreProvider.getInstance();

    public GameClient(String ip) throws RemoteException {
        super();

        try {
            System.out.println("Connecting to server via IP");
            server = (GameStoreServer) Naming.lookup("//" + ip + "/" + Server.REGISTRY_NAME);
            registerClient();
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public GameClient(GameStoreServer server) throws RemoteException {
        super();

        System.out.println("Connecting to server via GameStoreServer ref");
        this.server = server;
        registerClient();
    }

    private void registerClient() throws RemoteException {
        server.registerObserver(this);
    }

    @Override
    public void onGameStoreReceived(GameStore newState) {
        System.out.println("Received new gamestore");
        GameStore prevStore = storeObservable.getValue();
        if (prevStore == null) {
            storeObservable.setValue(newState);
            return;
        }
        // Compare gameStates and change views accordingly
        changeViewIfNeeded(newState.getCurrentState());

        // finally
        storeObservable.setValue(newState);
    }

    private void changeViewIfNeeded(GameState newState) {
        if (newState == storeObservable.getValue().getCurrentState()) {
            return;
        }

        switch (newState) {
            case GAME:
                // Switch to game view
                break;
            case INIT:
                // Switch to name/color select
                break;
            case LOBBY:
                // Switch to lobby
                break;
            case PAUSED:
                // Switch to pause screen
                break;
            case FINISHED:
                // Switch to end screen
                break;
        }
    }

    @Override
    public void sendAction(Action action) throws RemoteException {
        server.onActionReceived(action);
    }
}
