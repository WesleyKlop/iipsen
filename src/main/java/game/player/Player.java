package game.player;

import game.cards.CardStack;
import game.routecards.RouteCard;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class Player implements Serializable {
    private final String playerName;
    private final String color;
    private CardStack stack = new CardStack();
    private Set<RouteCard> routeCards = new HashSet<>();
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

    public Color getColorAsColor() {
        return Color.web(this.color);
    }
}
