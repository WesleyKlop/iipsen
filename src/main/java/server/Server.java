package server;

import client.GameStoreClient;
import game.GameStore;
import game.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class Server extends UnicastRemoteObject implements GameStoreServer {
    public static final String REGISTRY_NAME = "TTRGameService";
    private static final Logger Log = LogManager.getLogger(Server.class);
    private static final int PORT = 1099;

    private List<GameStoreClient> clients = new ArrayList<>();
    private GameStore gameStore;

    public Server() throws RemoteException, MalformedURLException, UnknownHostException {
        Log.debug("Starting server");
        String ip = InetAddress.getLocalHost().getHostAddress();
        gameStore = new GameStore(ip);

        LocateRegistry.createRegistry(PORT);
        Naming.rebind(REGISTRY_NAME, this);
        Log.debug("Server started at: " + ip);

        gameStore.getCardStackController().populateOpenCards();
        gameStore.getSelectableRouteCards().populatePickableCards();
        Log.info("Initial open/pickable cards are set");
    }

    @Override
    public synchronized void registerObserver(GameStoreClient listener) throws RemoteException {
        clients.add(listener);
        listener.onConnect(gameStore);
    }

    @Override
    public synchronized void unregisterObserver(GameStoreClient listener) {
        clients.remove(listener);
    }

    @Override
    public synchronized void notifyListeners() throws RemoteException {
        for (GameStoreClient client : clients) {
            client.onGameStoreReceived(gameStore);
        }
    }

    @Override
    public synchronized void onActionReceived(Action action) {
        try {
            action.executeAction(gameStore);
            Log.debug("Executed action");
            notifyListeners();
            Log.debug("Notified listeners");
        } catch (Exception ex) {
            Log.error("Server error while executing action", ex);
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        try {
            new Server();
        } catch (RemoteException | MalformedURLException e) {
            Log.error("Error occured while running server", e);
        }
    }
}
