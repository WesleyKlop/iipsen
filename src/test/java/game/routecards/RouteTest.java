package game.routecards;

import game.cards.CardType;
import game.location.ELocation;
import game.player.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void canBuildRoute() {
        var player = new Player("Thierry Baudet", Color.LAVENDER);
        player.setId(1);
        // Add enough cards to the players stack
        player.getCardStack().addCard(CardType.CART_BLACK);
        player.getCardStack().addCard(CardType.CART_BLACK);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);

        assertFalse(testRoute.hasOwner());
        assertTrue(testRoute.build(player));
        assertTrue(testRoute.hasOwner());
    }

    @Test
    void cantBuildRouteWithoutCards() {
        var player = new Player("Thierry Baudet", Color.LAVENDER);
        player.setId(1);
        assertFalse(testRoute.hasOwner());
        assertFalse(testRoute.build(player));
        assertFalse(testRoute.hasOwner());
    }

    @Test
    void cantBuildRouteWithOnlyLocomotiveOrOnlyTrains() {
        var player = new Player("Thierry Baudet", Color.LAVENDER);
        player.setId(1);
        player.getCardStack().addCard(CardType.CART_BLACK);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);

        assertFalse(testRoute.hasOwner());
        assertFalse(testRoute.build(player));
        assertFalse(testRoute.hasOwner());
    }

    @Test
    void costsAreCorrect() {
        assertEquals(2, testRoute.getCartCost());
        assertEquals(1, testRoute.getLocomotiveCost());
    }
}
