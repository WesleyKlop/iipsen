package game.player;

import game.cards.CardStack;
import game.routecards.ConnectionKeeper;
import game.routecards.RouteCard;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Player model contains all things that should be kept on the player
 */
public class Player implements Serializable {
    private final String playerName;
    private final String color;
    private final CardStack stack = new CardStack();
    private final List<RouteCard> routeCards = new ArrayList<>();
    private final ConnectionKeeper connectionKeeper = new ConnectionKeeper();
    private int id;
    private int score;
    private int trainCarts = 40;
    private boolean hasClient = false;

    public Player(String name, Color color) {
        this.color = color.toString();
        this.playerName = name;
    }

    public int getId() {
        return id;
    }

    public void setHasClient(boolean val) {
        hasClient = val;
    }

    public boolean hasClient() {
        return hasClient;
    }

    public void setId(int player) {
        this.id = player;
    }

    public CardStack getCardStack() {
        return stack;
    }

    /**
     * Add a route card to the players RouteCards
     *
     * @param card the card to add
     */
    public void addRouteCard(RouteCard card) {
        routeCards.add(card);
    }

    public List<RouteCard> getRouteCards() {
        return routeCards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getTrainCarts() {
        return this.trainCarts;
    }

    public String getColor() {
        return color;
    }

    /**
     * Get the color property of the player as a Color object
     * @return the players' color
     */
    public Color getColorAsColor() {
        return Color.web(this.color);
    }

    public ConnectionKeeper getConnectionKeeper() {
        return connectionKeeper;
    }

    public int getScore() {
        return score;
    }

    /**
     * Add x points to the player
     * @param points amount of points to add
     */
    public void givePoints(int points) {
        score += points;
    }

    /**
     * Remove x amount of train pieces from a player
     *
     * @param trains the amount of trains to remove
     */
    public void takeTrains(int trains) {
        trainCarts -= trains;
    }
}
