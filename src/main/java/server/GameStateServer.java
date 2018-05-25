package server;

import client.GameStateClient;
import game.GameStore;
import game.actions.Action;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wesley
 */
public interface GameStateServer extends Remote {
    void registerObserver(GameStateClient listener) throws RemoteException;

    void unregisterObserver(GameStateClient listener) throws RemoteException;

    void notifyListeners(GameStore newState) throws RemoteException;

    void onActionReceived(Action action) throws RemoteException;

}
