package server;

import client.GameStoreClient;
import game.GameState;
import game.GameStore;
import game.actions.Action;
import game.player.Player;
import game.routecards.RouteCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class Server extends UnicastRemoteObject implements GameStoreServer {
    public static final String REGISTRY_NAME = "TTRGameService";
    private static final Logger Log = LogManager.getLogger(Server.class);
    private static final int PORT = 1099;

    private List<GameStoreClient> clients = new ArrayList<>();
    private GameStore gameStore;

    public Server() throws RemoteException, MalformedURLException, UnknownHostException {
        Log.debug("Starting server");
        String ip = InetAddress.getLocalHost().getHostAddress();
        gameStore = new GameStore(ip);

        LocateRegistry.createRegistry(PORT);
        Naming.rebind(REGISTRY_NAME, this);
        Log.debug("Server started at: " + ip);

        gameStore.getCardStackController().populateOpenCards();
        gameStore.getSelectableRouteCards().populatePickableCards();
        Log.info("Initial open/pickable cards are set");
    }

    @Override
    public synchronized void registerObserver(GameStoreClient listener) throws RemoteException {
        clients.add(listener);
        listener.onConnect(gameStore);
    }

    @Override
    public synchronized void unregisterObserver(GameStoreClient listener) {
        clients.remove(listener);
    }

    @Override
    public synchronized void notifyListeners() {
        for (GameStoreClient client : clients) {
            try {
                client.onGameStoreReceived(gameStore);
            } catch (RemoteException e) {
                Log.error("Failed to update client.. removing him from listeners");
                clients.remove(client);
            }
        }
    }

    @Override
    public synchronized void onActionReceived(Action action) {
        try {
            action.executeAction(gameStore);

            doSideEffects(action);

            Log.debug("Executed action");
            notifyListeners();
            Log.debug("Notified listeners");
        } catch (Exception ex) {
            Log.error("Server error while executing action", ex);
        }
    }

    private void doSideEffects(Action action) {
        // Only do side effects for actions that are caused by a "player"
        if (action.getPlayerId() == -1) {
            return;
        }

        if (gameStore.shouldGoToLastTurn(action.getPlayerId())) {
            // When the player that did his turn has 2 ore less trainscarts we should set the last turn param
            Log.info("Player has 2 or less carts left.. Going to final round..");
            gameStore.setLastTurn(action.getPlayerId());
        } else if (gameStore.getLastTurn() == action.getPlayerId()) {
            // Else if the action player id is the same as the id of the last turn player
            // We should go to finish.
            calculateEndScore();
            Log.info("Going to finished state!");
            gameStore.setGameState(GameState.FINISHED);
        }
    }

    private void calculateEndScore() {
        // Loop through all players their routecards, and check which ones are completed
        for (Player player : gameStore.getPlayers()) {
            for (RouteCard routeCard : player.getRouteCards()) {
                if (player.getConnectionKeeper().isRouteCardCompleted(routeCard)) {
                    Log.debug("Player completed routecard, awarding points... {}", routeCard);
                    player.givePoints(routeCard.getValue());
                }
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        try {
            new Server();
        } catch (RemoteException | MalformedURLException e) {
            Log.error("Error occured while running server", e);
        }
    }
}
