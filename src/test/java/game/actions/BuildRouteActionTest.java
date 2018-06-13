package game.actions;

import game.GameStore;
import game.cards.CardType;
import game.location.ELocation;
import game.player.Player;
import game.routecards.Route;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildRouteActionTest {

    private Player player;
    private Route testRoute1;
    private BuildRouteAction action;
    private GameStore store;

    @BeforeEach
    void setUp() {
        player = new Player("Vitas", Color.BLUEVIOLET);
        testRoute1 = new Route(2, 1, ELocation.ALBORG, ELocation.ANDALSNES, CardType.CART_YELLOW);
        store = new GameStore();
        player.setId(1);
        action = new BuildRouteAction(player, testRoute1);
    }

    @AfterEach
    void tearDown() {
        player = null;
        testRoute1 = null;
        store = null;
        action = null;
    }

    @Test
    void buildRouteNotEnoughCards() {
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        action.executeAction(store);
        assertFalse(testRoute1.hasOwner());
    }

    @Test
    void buildRouteEnoughCards() {
        player.getCardStack().addCard(CardType.CART_YELLOW);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        action.executeAction(store);
        assertTrue(testRoute1.hasOwner());
    }

    @Test
    void overWriteOwnership() {
        testRoute1.setOwner(2);
        player.getCardStack().addCard(CardType.CART_YELLOW);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        action.executeAction(store);
        assertEquals(2, testRoute1.getOwner());
    }

}
