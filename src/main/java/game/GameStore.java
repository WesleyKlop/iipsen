package game;

import game.cards.CardStackController;
import game.player.Player;
import game.routecards.RouteCardStackBank;
import game.routecards.RouteStore;
import game.routecards.SelectableRouteCards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * GameStore keeps track of all game state.
 * This is the object that should be saved to disk and be passed around via RMI
 * By using this we're keeping a single source of truth
 *
 * @author Wesley Klop
 */
public class GameStore implements Serializable {
    private GameState gameState = GameState.LOBBY;
    private List<Player> players = new ArrayList<>();
    private CardStackController cardStackController = new CardStackController();
    private SelectableRouteCards selectableRouteCards = new SelectableRouteCards(new RouteCardStackBank());
    private RouteStore routeStore = new RouteStore();
    private int playersTurn = 0;

    public GameStore() {
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
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

    public void cyclePlayerTurn() {
        playersTurn = (playersTurn + 1) % 3;
    }

    public int getPlayersTurn() {
        return playersTurn + 1;
    }

    //TODO
}
