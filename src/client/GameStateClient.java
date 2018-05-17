package client;

import game.GameState;
import game.Turn;
import game.TurnNotFinishedException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wesley
 */
public interface GameStateClient extends Remote {

    void onGameStateReceived(GameState newState) throws RemoteException;

    void sendTurn(Turn turn) throws RemoteException, TurnNotFinishedException;
}
