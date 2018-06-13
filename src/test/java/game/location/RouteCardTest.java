package game.location;

import game.routecards.RouteCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Wesley Klop
 */
class RouteCardTest {
    @Test
    void routeCardCanBeCompleted() {
        var card = new RouteCard(ELocation.BERGEN, ELocation.ALBORG, 1337);
        assertFalse(card.isCompleted());
        card.setCompleted();
        assertTrue(card.isCompleted());
    }
}
