package client;

import game.GameState;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wesley
 */
public interface GameStateListener extends Remote {

    void onGameStateReceived(GameState newState) throws RemoteException;
}
