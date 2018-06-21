package client;

import game.GameState;
import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.AddPlayerAction;
import game.actions.ChangeStateAction;
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

    public GameClient(String ip, SceneListener sceneListener) throws RemoteException {
        super();
        this.sceneListener = sceneListener;

        try {
            Log.debug("Connecting to server via IP");
            server = (GameStoreServer) Naming.lookup("//" + ip + "/" + Server.REGISTRY_NAME);
            registerClient();
        } catch (NotBoundException | MalformedURLException e) {
            Log.error("Could not connect to server", e);
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

        if (GameStoreProvider.getPlayer() != null) {
            // Update the player on the GameStoreProvider
            GameStoreProvider.setPlayer(newStore.getPlayerById(GameStoreProvider.getPlayer().getId()));
            Log.debug("Updated player");
        }

        if (newStore.getGameState() != storeObservable.getValue().getGameState()) {
            Log.debug("Received new state, switching..");
            sceneListener.onSceneChange(newStore.getGameState());
        }

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
            GameStoreProvider.setPlayer(players.get(players.size() - 1));
            sceneListener.onSceneChange(GameState.LOBBY);
        } else if (lastAction instanceof ChangeStateAction) {
            sceneListener.onSceneChange(store.getGameState());
        }
    }

    @Override
    public void sendAction(Action action) {
        // Run
        new Thread(() -> {
            lastAction = action;
            try {
                server.onActionReceived(action);
            } catch (RemoteException e) {
                Log.error("Error sending action to server", e);
            }
        }).start();
    }

    @Override
    public void onConnect(GameStore initialStore) {
        storeObservable.setValue(initialStore);
        Log.debug("Connected to server");
    }

    public Observable<GameStore> getStoreObservable() {
        return storeObservable;
    }
}
