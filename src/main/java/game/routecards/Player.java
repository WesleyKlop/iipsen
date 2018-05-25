package game.routecards;

import game.cards.CardStack;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wesley
 */
public class Player {
    private CardStack stack = new CardStack();
    private Set<RouteCard> routeCards = new HashSet<>();
    private final String playerName;
    private final Color color;
    private int id;
    private int score;
    private int traincarts = 40;

    public Player(String name, Color color) {
        this.color = color;
        this.playerName = name;
    }

    public int getId() {
        return id;
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

    public void setId(int player) {
        this.id = player;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("Player{id=%d, playerName='%s', color=%s, score=%d, traincarts=%d}", id, playerName, color, score, traincarts);
    }
}
