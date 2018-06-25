package client;

import game.GameStore;
import game.actions.Action;
import game.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Client stub
 */
public interface GameStoreClient extends Remote {

    void onGameStoreReceived(GameStore newStore) throws RemoteException;

    void sendAction(Action action) throws RemoteException;

    void onConnect(GameStore initialStore) throws RemoteException;

    Player getPlayer();

    void setPlayer(Player player);
}
