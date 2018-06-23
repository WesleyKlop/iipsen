package game.actions;

import game.GameStore;
import game.GameStoreProvider;
import game.cards.CardType;
import game.player.Player;
import game.routecards.Route;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildRouteActionTest {
    private static final int TEST_ROUTE_ID = 47;
    private Player player;
    private Route testRoute1;
    private BuildRouteAction action;
    private GameStore store;

    @BeforeEach
    void setUp() {
        player = new Player("Vitas", Color.BLUEVIOLET);
        player.setId(1);
        store = new GameStore("");
        GameStoreProvider.getInstance().setValue(store);
        testRoute1 = store.getRouteStore().getRouteById(TEST_ROUTE_ID);
        store.getPlayers().add(player);
        action = new BuildRouteAction(player.getId(), testRoute1);
    }

    @AfterEach
    void tearDown() {
        player = null;
        testRoute1 = null;
        action = null;
        store = null;
    }

    @Test
    void buildRouteNotEnoughCards() {
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        assertThrows(Exception.class, () -> action.executeAction(store));
        assertFalse(testRoute1.hasOwner());
    }

    @Test
    void buildRouteEnoughCards() {
        player.getCardStack().addCard(CardType.CART_YELLOW);
        player.getCardStack().addCard(CardType.CART_YELLOW);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        assertDoesNotThrow(() -> action.executeAction(store));
        assertEquals(1, testRoute1.getOwner());
    }

    @Test
    void overWriteOwnership() {
        testRoute1.setOwner(2);
        player.getCardStack().addCard(CardType.CART_YELLOW);
        player.getCardStack().addCard(CardType.CART_YELLOW);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        assertDoesNotThrow(() -> action.executeAction(store));
        assertEquals(1, testRoute1.getOwner());
    }

}
