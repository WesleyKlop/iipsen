package server;

import client.GameStoreClient;
import game.actions.Action;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Server skeleton
 */
public interface GameStoreServer extends Remote {
    void registerObserver(GameStoreClient listener) throws RemoteException;

    void unregisterObserver(GameStoreClient listener) throws RemoteException;

    void notifyListeners() throws RemoteException;

    void onActionReceived(Action action) throws RemoteException;

}
