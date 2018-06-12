package client;

import game.GameState;
import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.AddPlayerAction;
import game.actions.ChangeStateAction;
import game.player.Player;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.GameStoreServer;
import server.Server;
import util.Observable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 */
public class GameClient extends UnicastRemoteObject implements GameStoreClient {
    private transient static final Logger Log = LogManager.getLogger(GameClient.class);

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
            Log.debug("Connecting to server via IP");
            server = (GameStoreServer) Naming.lookup("//" + ip + "/" + Server.REGISTRY_NAME);
            registerClient();
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public GameClient(GameStoreServer server, SceneListener sceneListener) throws RemoteException {
        super();
        this.sceneListener = sceneListener;

        Log.debug("Connecting to server via GameStoreServer ref");
        this.server = server;
        registerClient();
    }

    private void registerClient() throws RemoteException {
        server.registerObserver(this);
        GameStoreProvider.setSender(this);
    }

    @Override
    public void onGameStoreReceived(GameStore newStore) {
        Log.debug("Received new gamestore");

        // Process previous actions' response
        if (lastAction != null) {
            Platform.runLater(() -> {
                processLastActionResponse();
                lastAction = null;
            });
        }

        // finally
        storeObservable.setValue(newStore);
    }

    private void processLastActionResponse() {
        var store = storeObservable.getValue();

        if (lastAction instanceof AddPlayerAction) {
            var players = store.getPlayers();
            player = players.get(players.size() - 1);
            sceneListener.onSceneChange(GameState.LOBBY);
        } else if (lastAction instanceof ChangeStateAction) {
            sceneListener.onSceneChange(store.getGameState());
        }
    }

    @Override
    public void sendAction(Action action) throws RemoteException {
        lastAction = action;
        server.onActionReceived(action);
    }

    @Override
    public void onConnect(GameStore initialStore) {
        storeObservable.setValue(initialStore);
        Log.debug("Connected to server");
    }
}
