package game.routecards;

import game.GameStore;
import game.actions.BuildRouteAction;
import game.location.ELocation;
import game.location.LocationStore;
import game.player.Player;
import game.player.PlayerMock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.*;

import static game.location.ELocation.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Wesley Klop
 */
class RouteCardPathFinderTest {
    private static Level ORIGINAL_LEVEL;

    private GameStore store;
    private LocationStore locationStore;
    private RouteCardPathFinder pathFinder;
    private Player richBoi;

    @BeforeAll
    static void setLogLevel() {
        ORIGINAL_LEVEL = LogManager.getRootLogger().getLevel();
        // Reduce console noise
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.INFO);
    }

    @AfterAll
    static void revertLogLevel() {
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), ORIGINAL_LEVEL);
    }

    @BeforeEach
    void setUp() {
        // Refresh the pathFinder with a new LocationStore every test
        locationStore = LocationStore.Generate();
        pathFinder = new RouteCardPathFinder(locationStore);
        richBoi = new PlayerMock();
        store = new GameStore();
        store.addPlayer(richBoi);
    }

    @AfterEach
    void tearDown() {
        locationStore = null;
        pathFinder = null;
        richBoi = null;
        store = null;
    }

    @Test
    void canFindEasyPath() {
        RouteCard routeCard = new RouteCard(ARHUS, KRISTIANSAND, 1337);
        var action = new BuildRouteAction(richBoi, locationStore.getLocation(ALBORG).getRouteToLocation(ARHUS));
        action.executeAction(store);
        action = new BuildRouteAction(richBoi, locationStore.getLocation(KRISTIANSAND).getRouteToLocation(ALBORG));
        action.executeAction(store);
        assertTrue(pathFinder.solveForRouteCard(richBoi, routeCard));
    }

    @Test
    void canFindLongPath() {
        RouteCard routeCard = new RouteCard(ANDALSNES, TAMPERE, 2000);
        ELocation[] route = {ANDALSNES, TRONDHEIM, MO_I_RANA, NARVIK, TROMSO, HONNINGSVAG, KIRKENES, ROVANIEMI, OULU, VAASA, TAMPERE};

        for (int i = 1, routeLength = route.length; i < routeLength; i++) {
            ELocation eLocation = route[i];
            var action = new BuildRouteAction(richBoi, locationStore.getLocation(route[i - 1]).getRouteToLocation(eLocation));
            action.executeAction(store);
        }

        assertTrue(pathFinder.solveForRouteCard(richBoi, routeCard));
    }
}
