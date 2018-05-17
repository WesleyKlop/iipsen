package game.routecards;

import game.cards.CardStack;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wesley
 */
public class Player {
    private CardStack stack = new CardStack();
    private Set<RouteCard> routeCards = new HashSet<>();
    private EPlayer player;
    private String playerName;
    private int score;
    private int traincarts = 40;

    public Player(EPlayer player, String name) {
        this.player = player;
        this.playerName = name;
    }

    public EPlayer getPlayer() {
        return player;
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
}
