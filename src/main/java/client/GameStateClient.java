package client;

import game.GameStore;
import game.actions.Action;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wesley
 */
public interface GameStateClient extends Remote, Serializable {

    void onGameStateReceived(GameStore newState) throws RemoteException;

    void sendAction(Action action) throws RemoteException;
}
