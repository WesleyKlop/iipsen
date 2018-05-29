package game.routecards;

import game.cards.CardType;
import game.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author wesley
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

    public void build(Player builder) {
        Log.debug(color + " " + getCartCost() + " " + getLocomotiveCost());
        var costsLocomotives = getLocomotiveCost() > 0;
        var costsCarts = getCartCost() > 0;
        if ((costsCarts || builder.getCardStack().containsCards(color, getCartCost())) &&
            (costsLocomotives || builder.getCardStack().containsCards(CardType.LOCOMOTIVE, locomotiveCost))
        ) {
            try {
                if (costsCarts)
                    builder.getCardStack().takeCards(color, getCartCost());
                if (costsLocomotives)
                    builder.getCardStack().takeCards(CardType.LOCOMOTIVE, locomotiveCost);

                builder.removeTrainCarts(length);
                this.owner = builder.getId();
                Log.debug("Route build! by " + builder.getPlayerName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.debug("player doesnt have the needed game.cards!!");
            Log.debug(builder.getCardStack());
        }
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
