package game.routecards;

import game.cards.CardType;
import game.location.ELocation;
import game.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 */
public class Route {
    private static final Logger Log = LogManager.getLogger(Route.class);

    private int length;
    private int locomotiveCost;
    private int owner;
    private ELocation location1, location2;
    private CardType color;

    public Route(int length, int locomotiveCost, ELocation location1, ELocation location2, CardType color) {
        this.length = length;
        this.locomotiveCost = locomotiveCost;
        this.location1 = location1;
        this.location2 = location2;
        this.color = color;
    }

    public boolean build(Player builder) {
        Log.debug(color + " " + getCartCost() + " " + getLocomotiveCost());
        if (
            builder.getCardStack().containsCards(color, getCartCost()) &&
                builder.getCardStack().containsCards(CardType.LOCOMOTIVE, locomotiveCost)
            ) {
            try {
                builder.getCardStack().takeCards(color, getCartCost());
                builder.getCardStack().takeCards(CardType.LOCOMOTIVE, locomotiveCost);

                builder.removeTrainCarts(length);
                this.owner = builder.getId();
                Log.debug("Route build! by " + builder.getPlayerName());
                return true;

            } catch (Exception e) {
                return false;
            }
        } else {
            Log.debug("player doesn't have the needed cards!!");
            Log.debug(builder.getCardStack());
        }
        return false;
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

    public CardType getType() {
        return color;
    }

    public int getOwner() {
        return this.owner;
    }

    public void setOwner(int newOwner) {
        owner = newOwner;
    }

    @Override
    public String toString() {
        return String.format("Route connects %s to %s with length %d and color %s", location1, location2, length, color);
    }
}
