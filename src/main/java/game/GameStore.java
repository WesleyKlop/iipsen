package game;

import game.cards.CardStackController;
import game.location.LocationStore;
import game.player.Player;
import game.routecards.RouteCardStackBank;
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
    private SelectableRouteCards routeCardStack = new SelectableRouteCards(new RouteCardStackBank());
    private LocationStore locationStore = LocationStore.Generate();

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

    public void addPlayer(Player player) {
        players.add(player);
        // Calling setId after adding makes the player id start at 1
        player.setId(players.size());
    }

    public SelectableRouteCards getSelectableRouteCards() {
        return routeCardStack;
    }

    public CardStackController getCardStackController() {
        return cardStackController;
    }
}
