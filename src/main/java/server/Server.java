package server;

import client.GameStoreClient;
import game.GameStore;
import game.actions.Action;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wesley
 */
public class Server extends UnicastRemoteObject implements GameStoreServer {

    public static final String REGISTRY_NAME = "TTRGameService";
    private static final int PORT = 1099;

    private List<GameStoreClient> clients = new ArrayList<>();
    private GameStore currentGameStore = new GameStore();

    public Server() throws RemoteException, MalformedURLException {
        System.out.println("Starting server");

        LocateRegistry.createRegistry(PORT);
        Naming.rebind(REGISTRY_NAME, this);
        System.out.println("Server started");
    }

    @Override
    public synchronized void registerObserver(GameStoreClient listener) throws RemoteException {
        clients.add(listener);
        listener.onConnect(currentGameStore);
    }

    @Override
    public synchronized void unregisterObserver(GameStoreClient listener) {
        clients.remove(listener);
    }

    @Override
    public synchronized void notifyListeners(GameStore newState) throws RemoteException {
        for (GameStoreClient client : clients) {
            client.onGameStoreReceived(newState);
        }
    }

    @Override
    public synchronized void onActionReceived(Action action) {
        try {
            action.executeAction(currentGameStore);
            System.out.println("Executed action");
            notifyListeners(currentGameStore);
            System.out.println("Notified listeners");
        } catch (Exception ex) {
            System.out.println("Server error while executing action");
            ex.printStackTrace();
        }
    }
}
