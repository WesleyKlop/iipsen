package server;

import client.GameStateListener;
import game.GameState;
import game.Turn;

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
    private List<GameStateListener> clients = new ArrayList<>();
    private GameState currentGameState = new GameState();

    public Server() throws RemoteException, MalformedURLException {
        System.out.println("Starting server");

        LocateRegistry.createRegistry(PORT);
        Naming.rebind(REGISTRY_NAME, this);
        System.out.println("Server started");
    }

    @Override
    public synchronized void registerObserver(GameStateListener listener) {
        clients.add(listener);
    }

    @Override
    public synchronized void unregisterObserver(GameStateListener listener) {
        clients.remove(listener);
    }

    @Override
    public synchronized void onTurnReceived(Turn newState) throws RemoteException {
        newState.updateGameState(currentGameState);
        // TODO: Next turn
        notifyListeners(currentGameState);
    }

    @Override
    public synchronized void notifyListeners(GameState newState) throws RemoteException {
        for (GameStateListener client : clients) {
            client.onGameStateReceived(newState);
        }
    }
}
