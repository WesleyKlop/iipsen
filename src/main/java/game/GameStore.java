package game;

import game.cards.CardStackController;
import game.player.PlayerController;
import game.routecards.RouteCardStackBank;
import game.routecards.RouteStore;
import game.routecards.SelectableRouteCards;

import java.io.Serializable;

/**
 * GameStore keeps track of all game state.
 * This is the object that should be saved to disk and be passed around via RMI
 * By using this we're keeping a single source of truth
 *
 * @author Wesley Klop
 */
public class GameStore implements Serializable {
    private GameState gameState = GameState.INIT;
    private PlayerController playerController = new PlayerController();
    private CardStackController cardStackController = new CardStackController();
    private SelectableRouteCards selectableRouteCards = new SelectableRouteCards(new RouteCardStackBank());
    private RouteStore routeStore = new RouteStore();
    private String serverIp;

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setServerIp(String ip) {
        serverIp = ip;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState newState) {
        gameState = newState;
    }

    public SelectableRouteCards getSelectableRouteCards() {
        return selectableRouteCards;
    }

    public CardStackController getCardStackController() {
        return cardStackController;
    }

    public RouteStore getRouteStore() {
        return routeStore;
    }

    public String getServerIp() {
        return serverIp;
    }
    //TODO
}
