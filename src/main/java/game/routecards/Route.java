package game.routecards;

import game.GameStoreProvider;
import game.cards.CardStack;
import game.cards.CardType;
import game.location.ELocation;
import game.player.PlayerController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 */
public class Route implements Serializable {
    private transient static final Logger Log = LogManager.getLogger(Route.class);

    private int id;
    private int length;
    private int locomotiveCost;
    private int owner;
    private ELocation location1, location2;
    private CardType color;
    private RouteType routeType;
    private int points;

    public Route(int id, int length, int locomotiveCost, ELocation location1, ELocation location2, CardType color, RouteType routeType) {
        this.id = id;
        this.length = length;
        this.locomotiveCost = locomotiveCost;
        this.location1 = location1;
        this.location2 = location2;
        this.color = color;
        this.routeType = routeType;
        this.points = calculatePoints();
    }

    public boolean hasOwner() {
        return owner != 0;
    }

    public int getCartCost() {
        return length - locomotiveCost;
    }

    public int getLocomotiveCost() {
        return locomotiveCost;
    }

    public CardType getCardType() {
        return color;
    }

    public int getOwner() {
        return this.owner;
    }

    public void setOwner(int newOwner) {
        owner = newOwner;
    }

    public ELocation[] getLocations() {
        return new ELocation[]{location1, location2};
    }

    public int getId() {
        return id;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    private int calculatePoints() {
        switch (length) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
            case 9:
                return 27;
            default:
                Log.warn("Route from {} to {} has 0 length?", location1, location2);
                return 0;
        }
    }

    public CardStack getCostsAsCardStack() {
        var stack = new CardStack();
        for (int i = 0; i < getLocomotiveCost(); i++) {
            stack.addCard(CardType.LOCOMOTIVE);
        }
        PlayerController controller = GameStoreProvider.getStore().getPlayerController();
        CardType cType = controller.getPlayerById(controller.getCurrentTurn()).getCardStack().getBiggestType();
        for (int i = 0; i < getCartCost(); i++) {
            if (color == CardType.CART_ANY) {
                stack.addCard(cType);
            } else {
                stack.addCard(color);
            }
        }

        return stack;
    }

    public int getPoints() {
        return points;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return String.format("Route connects %s to %s with length %d and color %s", location1, location2, length, color);
    }
}
