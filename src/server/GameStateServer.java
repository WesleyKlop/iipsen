package server;

import client.GameStateListener;
import game.GameState;
import game.Turn;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wesley
 */
public interface GameStateServer extends Remote {
    void registerObserver(GameStateListener listener) throws RemoteException;

    void unregisterObserver(GameStateListener listener) throws RemoteException;

    void onTurnReceived(Turn newState) throws RemoteException;

    void notifyListeners(GameState newState) throws RemoteException;

}
