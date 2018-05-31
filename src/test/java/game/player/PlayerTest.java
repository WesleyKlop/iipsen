package game.player;

import game.cards.CardType;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wesley
 */
class PlayerTest {
    private static final Color playerColor = Color.LAVENDER;
    private static final String playerName = "Thierry Baudet";
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(playerName, playerColor);
    }

    @AfterEach
    void tearDown() {
        player = null;
    }

    @Test
    void getId() {
        assertEquals(0, player.getId());
    }

    @Test
    void setId() {
        final var playerId = 1;
        player.setId(playerId);
        assertEquals(playerId, player.getId());
    }

    @Test
    void getCardStack() {
        assertNotNull(player.getCardStack());
    }

    @Test
    void addRouteCard() {
        assertTrue(player.getCardStack().isEmpty());
        player.getCardStack().addCard(CardType.CART_BLACK);
        assertFalse(player.getCardStack().isEmpty());
    }

    @Test
    void getPlayerName() {
        assertEquals(playerName, player.getPlayerName());
    }

    @Test
    void removeTrainCarts() {
        assertEquals(40, player.getTraincarts());
        player.removeTrainCarts(3);
        assertEquals(37, player.getTraincarts());
    }

    @Test
    void getColor() {
        assertEquals(playerColor.toString(), player.getColor());
    }

    @Test
    void toStringWorks() {
        assertNotNull(player.toString());
    }
}