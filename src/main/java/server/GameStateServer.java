package server;

import client.GameStateClient;
import game.GameState;
import game.Turn;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wesley
 */
public interface GameStateServer extends Remote {
    void registerObserver(GameStateClient listener) throws RemoteException;

    void unregisterObserver(GameStateClient listener) throws RemoteException;

    void onTurnReceived(Turn newState) throws RemoteException;

    void notifyListeners(GameState newState) throws RemoteException;

}
