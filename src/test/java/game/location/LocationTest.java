package game.location;

import game.cards.CardType;
import game.routecards.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wesley
 */
class LocationTest {

    private static final ELocation eloc = ELocation.BERGEN;
    private final ELocation destinationLoc = ELocation.ALBORG;
    private final Route destinationRoute = new Route(1, 1, eloc, destinationLoc, CardType.CART_BLACK);
    private Location loc;

    @AfterEach
    void tearDown() {
        loc = null;
    }

    @BeforeEach
    void setUp() {
        loc = new Location(eloc);
        loc.addRoute(destinationLoc, destinationRoute);
    }

    @Test
    void getRouteToLocation() {
        assertEquals(destinationRoute, loc.getRouteToLocation(destinationLoc));
    }

    @Test
    void addRoute() {
        assertEquals(1, loc.getConnections().size());
    }

    @Test
    void getLocation() {
        assertEquals(eloc, loc.getLocation());
    }
}
