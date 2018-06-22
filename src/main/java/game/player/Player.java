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
    private CardStack stack = new CardStack();
    private List<RouteCard> routeCards = new ArrayList<>();
    private ConnectionKeeper connectionKeeper = new ConnectionKeeper();
    private int id;
    private int score;
    private int traincarts = 40;

    public Player(String name, Color color) {
        this.color = color.toString();
        this.playerName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int player) {
        this.id = player;
    }

    public CardStack getCardStack() {
        return stack;
    }

    public void addRouteCard(RouteCard card) {
        routeCards.add(card);
    }

    public List<RouteCard> getRouteCards() {
        return routeCards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void removeTrainCarts(int count) {
        this.traincarts -= count;
    }

    public int getTraincarts() {
        return this.traincarts;
    }

    public String getColor() {
        return color;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Color getColorAsColor() {
        return Color.web(this.color);
    }

    public ConnectionKeeper getConnectionKeeper() {
        return connectionKeeper;
    }

    public int getScore() {
        return score;
    }

    public void givePoints(int points) {
        score += points;
    }

    public void takeTrains(int trains) {
        traincarts -= trains;
    }
}
