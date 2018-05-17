package client;

import game.GameState;
import server.GameStateServer;
import server.Server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author wesley
 */
public class GameClient extends UnicastRemoteObject implements GameStateListener {

    // Transient because we don't want to send this to the server
    private transient GameState gameState;

    public GameClient(String ip) throws RemoteException {
        super();

        try {
            System.out.println("Connecting to server");
            GameStateServer server = (GameStateServer) Naming.lookup("//" + ip + "/" + Server.REGISTRY_NAME);
            server.registerObserver(this);
        } catch (Exception ex) {
            System.out.println("Shit happened");
            ex.printStackTrace();
        }
    }

    @Override
    public void onGameStateReceived(GameState newState) {
        this.gameState = newState;
        // TODO: UI stuff when state changes
    }
}
