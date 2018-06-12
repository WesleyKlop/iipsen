package client;

import game.GameStore;
import game.actions.Action;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Client stub
 */
public interface GameStoreClient extends Remote, Serializable {

    void onGameStoreReceived(GameStore newStore) throws RemoteException;

    void sendAction(Action action) throws RemoteException;

    void onConnect(GameStore initialStore) throws RemoteException;
}
