package client;

import game.GameState;
import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.AddPlayerAction;
import game.routecards.Player;
import server.GameStoreServer;
import server.Server;
import util.Observable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

interface SceneListener {
    void onSceneChange(GameState state);
}

/**
 * @author wesley
 */
public class GameClient extends UnicastRemoteObject implements GameStoreClient {

    // Transient because we don't want to send this to the server
    private transient GameStoreServer server;
    private transient Observable<GameStore> storeObservable = GameStoreProvider.getInstance();
    private transient SceneListener sceneListener;
    private transient Action lastAction;
    private Player player;

    public GameClient(String ip, SceneListener sceneListener) throws RemoteException {
        super();
        this.sceneListener = sceneListener;

        try {
            System.out.println("Connecting to server via IP");
            server = (GameStoreServer) Naming.lookup("//" + ip + "/" + Server.REGISTRY_NAME);
            registerClient();
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public GameClient(GameStoreServer server, SceneListener sceneListener) throws RemoteException {
        super();
        this.sceneListener = sceneListener;

        System.out.println("Connecting to server via GameStoreServer ref");
        this.server = server;
        registerClient();
    }

    private void registerClient() throws RemoteException {
        server.registerObserver(this);
        GameStoreProvider.setSender(this);
    }

    @Override
    public void onGameStoreReceived(GameStore newState) {
        System.out.println("Received new gamestore");

        // Compare gameStates and change views accordingly
        if (newState.getCurrentState() != GameStoreProvider.getStore().getCurrentState()) {
            sceneListener.onSceneChange(newState.getCurrentState());
        }

        // Set player
        if (lastAction instanceof AddPlayerAction) {
            var players = newState.getPlayers();
            player = players.get(players.size() - 1);
        }

        // finally
        storeObservable.setValue(newState);
    }

    @Override
    public void sendAction(Action action) throws RemoteException {
        lastAction = action;
        server.onActionReceived(action);
    }

    @Override
    public void onConnect(GameStore initialStore) {
        storeObservable.setValue(initialStore);
        System.out.println("Connected");
        sceneListener.onSceneChange(GameState.INIT);
    }
}
