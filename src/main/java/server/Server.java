package server;

import client.GameStateClient;
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
public class Server extends UnicastRemoteObject implements GameStateServer {
    public static final String REGISTRY_NAME = "TTRGameService";
    private static final int PORT = 1099;
    private List<GameStateClient> clients = new ArrayList<>();
    private GameStore currentGameStore = new GameStore();

    public Server() throws RemoteException, MalformedURLException {
        System.out.println("Starting server");

        LocateRegistry.createRegistry(PORT);
        Naming.rebind(REGISTRY_NAME, this);
        System.out.println("Server started");
    }

    @Override
    public synchronized void registerObserver(GameStateClient listener) throws RemoteException {
        clients.add(listener);
        listener.onGameStateReceived(currentGameStore);
    }

    @Override
    public synchronized void unregisterObserver(GameStateClient listener) {
        clients.remove(listener);
    }

    @Override
    public synchronized void notifyListeners(GameStore newState) throws RemoteException {
        for (GameStateClient client : clients) {
            client.onGameStateReceived(newState);
        }
    }

    @Override
    public synchronized void onActionReceived(Action action) throws RemoteException {
        try {
            action.executeAction(currentGameStore);
        } catch (Exception ex) {
            System.out.println("Server error while executing action");
            ex.printStackTrace();
        } finally {
            notifyListeners(currentGameStore);
        }
    }
}
