package game.routecards;

import game.cards.CardType;
import game.location.ELocation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wesley
 */
class RouteTest {
    private Route testRoute;

    @AfterEach
    void tearDown() {
        testRoute = null;
    }

    @BeforeEach
    void setUp() {
        testRoute = new Route(1, 3, 1, ELocation.ALBORG, ELocation.ANDALSNES, CardType.CART_BLACK, RouteType.NORMAL);
    }

    @Test
    void costsAreCorrect() {
        assertEquals(2, testRoute.getCartCost());
        assertEquals(1, testRoute.getLocomotiveCost());
    }
}
