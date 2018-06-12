package game.actions;

import game.GameStore;
import game.cards.CardType;
import game.location.ELocation;
import game.location.LocationStore;
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
    private LocationStore locStore;

    @BeforeEach
    void setUp() {
        player = new Player("Vitas", Color.BLUEVIOLET);
        store = new GameStore();
        player.setId(1);
        locStore = LocationStore.Generate();
        action = new BuildRouteAction(player, locStore.getLocation(ELocation.HONNINGSVAG), locStore.getLocation(ELocation.KIRKENES));
    }

    @AfterEach
    void tearDown() {
        player = null;
        testRoute1 = null;
        store = null;
        action = null;
        locStore = null;
    }

    @Test
    void buildRouteNotEnoughCards() {
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        action.executeAction(store);
        assertFalse(locStore.getLocation(ELocation.HONNINGSVAG).getRouteToLocation(ELocation.KIRKENES).hasOwner());
    }

    @Test
    void buildRouteEnoughCards() {
        player.getCardStack().addCard(CardType.CART_GREEN);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        action.executeAction(store);
        assertTrue(locStore.getLocation(ELocation.HONNINGSVAG).getRouteToLocation(ELocation.KIRKENES).hasOwner());
    }

    @Test
    void overWriteOwnership() {
        locStore.getLocation(ELocation.HONNINGSVAG).getRouteToLocation(ELocation.KIRKENES).setOwner(2);
        player.getCardStack().addCard(CardType.CART_GREEN);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        action.executeAction(store);
        assertEquals(2, locStore.getLocation(ELocation.HONNINGSVAG).getRouteToLocation(ELocation.KIRKENES).getOwner());
    }

    @Test
    void checkRouteConnection() {
        player.getCardStack().addCard(CardType.CART_GREEN);
        player.getCardStack().addCard(CardType.LOCOMOTIVE);
        action.executeAction(store);
        assertTrue(player.getConnectionKeeper().checkForRouteCompleted(ELocation.HONNINGSVAG, ELocation.KIRKENES));
    }

}
