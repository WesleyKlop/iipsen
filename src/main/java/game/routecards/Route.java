package game.routecards;

import game.cards.CardType;
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
    private CardType color;

    public Route(int length, int locomotiveCost, CardType color) {
        this.length = length;
        this.locomotiveCost = locomotiveCost;
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
            Log.debug("player doesnt have the needed cards!!");
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
}
