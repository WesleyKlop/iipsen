package client;

import game.GameState;
import game.GameStore;
import game.actions.Action;
import server.GameStateServer;
import server.Server;
import util.Observable;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author wesley
 */
public class GameClient extends UnicastRemoteObject implements GameStateClient {

    // Transient because we don't want to send this to the server
    private transient Observable<GameStore> gameStore = new Observable<>();
    private transient GameStateServer server;

    public GameClient(String ip) throws RemoteException {
        super();

        try {
            System.out.println("Connecting to server");
            server = (GameStateServer) Naming.lookup("//" + ip + "/" + Server.REGISTRY_NAME);
            server.registerObserver(this);
        } catch (Exception ex) {
            System.out.println("Shit happened");
            ex.printStackTrace();
        }
    }

    @Override
    public void onGameStateReceived(GameStore newState) {
        if (gameStore.getValue() == null) {
            gameStore.setValue(newState);
            return;
        }
        // Compare gameStates and change views accordingly
        changeViewIfNeeded(newState.getCurrentState());

        // finally
        gameStore.setValue(newState);
    }

    private void changeViewIfNeeded(GameState newState) {
        if (newState == gameStore.getValue().getCurrentState()) {
            return;
        }

        switch (newState) {
            case GAME:
                // Switch to game view
                break;
            case INIT:
                // Switch to name/color select
                break;
            case LOBBY:
                // Switch to lobby
                break;
            case PAUSED:
                // Switch to pause screen
                break;
            case FINISHED:
                // Switch to end screen
                break;
        }
    }

    @Override
    public void sendAction(Action action) throws RemoteException {
        server.onActionReceived(action);
    }
}
