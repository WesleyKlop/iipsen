package game;

import game.cards.CardStackController;
import game.player.Player;
import game.routecards.RouteCardStackSelected;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class GameStore implements Serializable {
    private GameState gameState = GameState.INIT;
    private List<Player> players = new ArrayList<>();
    private CardStackController cardStackController = new CardStackController();
    private RouteCardStackSelected routeCardStack = new RouteCardStackSelected();

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

    public CardStackController getCardStackController() {
        return cardStackController;
    }

    public RouteCardStackSelected getRouteCardStack() {
        return routeCardStack;
    }
    //TODO
}
