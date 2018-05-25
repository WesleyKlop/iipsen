package client;

import game.GameStore;
import game.actions.Action;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wesley
 */
public interface GameStoreClient extends Remote, Serializable {

    void onGameStoreReceived(GameStore newState) throws RemoteException;

    void sendAction(Action action) throws RemoteException;
}
