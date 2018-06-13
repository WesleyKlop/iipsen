package game.routecards;

import game.location.ELocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Thom
 */
class ConnectionKeeperTest {

    private static final Logger Log = LogManager.getLogger(ConnectionKeeperTest.class);

    private ConnectionKeeper testKeeper;

    @BeforeEach
    void setUp() {
        testKeeper = new ConnectionKeeper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void routeConnects() {
        ELocation loc1 = ELocation.NARVIK;
        ELocation loc2 = ELocation.TROMSO;
        testKeeper.addLocations(loc1, loc2);
        assertTrue(testKeeper.checkForRouteCompleted(loc1, loc2));
    }

    @Test
    void routeNotConnects() {
        ELocation loc1 = ELocation.NARVIK;
        ELocation loc2 = ELocation.TROMSO;
        ELocation loc3 = ELocation.ALBORG;
        testKeeper.addLocations(loc1, loc2);
        assertFalse(testKeeper.checkForRouteCompleted(loc1, loc3));
    }

    @Test
    void routeConnectsMultiple() {
        ELocation loc1 = ELocation.NARVIK;
        ELocation loc2 = ELocation.TROMSO;
        ELocation loc3 = ELocation.ANDALSNES;
        testKeeper.addLocations(loc1, loc2);
        testKeeper.addLocations(loc1, loc3);
        assertTrue(testKeeper.checkForRouteCompleted(loc2, loc3));
    }

    @Test
    void routeMergeSets() {
        ELocation loc1 = ELocation.NARVIK;
        ELocation loc2 = ELocation.TROMSO;
        ELocation loc3 = ELocation.ANDALSNES;
        ELocation loc4 = ELocation.KAJAANI;
        testKeeper.addLocations(loc1, loc2);
        testKeeper.addLocations(loc3, loc4);
        testKeeper.addLocations(loc1, loc3);
        assertTrue(testKeeper.checkForRouteCompleted(loc2, loc4));
    }

    @Test
    void routeMergeSetsKeepsOthersIntact() {
        ELocation loc1 = ELocation.NARVIK;
        ELocation loc2 = ELocation.TROMSO;
        ELocation loc3 = ELocation.ANDALSNES;
        ELocation loc4 = ELocation.KAJAANI;
        ELocation loc5 = ELocation.ARHUS;
        ELocation loc6 = ELocation.BODEN;
        testKeeper.addLocations(loc1, loc2);
        testKeeper.addLocations(loc3, loc4);
        testKeeper.addLocations(loc1, loc3);
        testKeeper.addLocations(loc5, loc6);
        assertTrue(testKeeper.checkForRouteCompleted(loc5, loc6));
    }

    @Test
    void doubleRouteAdded() {
        ELocation loc1 = ELocation.ALBORG;
        ELocation loc2 = ELocation.ANDALSNES;
        testKeeper.addLocations(loc1, loc2);
        testKeeper.addLocations(loc1, loc2);
        assertTrue(testKeeper.checkForRouteCompleted(loc1, loc2));
    }

    @Test
    void speed() {
        ELocation[] loc = new ELocation[19];
        loc[1] = ELocation.ALBORG;
        loc[2] = ELocation.ANDALSNES;
        loc[3] = ELocation.ARHUS;
        loc[4] = ELocation.BERGEN;
        loc[5] = ELocation.GOTEBORG;
        loc[6] = ELocation.HELSINKI;
        loc[7] = ELocation.HONNINGSVAG;
        loc[8] = ELocation.IMATRA;
        loc[9] = ELocation.KAJAANI;
        loc[10] = ELocation.KARLSKRONA;
        loc[11] = ELocation.KIRKENES;
        loc[12] = ELocation.KIRUNA;
        loc[13] = ELocation.KOBENHAVN;
        loc[14] = ELocation.KRISTIANSAND;
        loc[15] = ELocation.KUOPIO;
        loc[16] = ELocation.LAHTI;
        loc[17] = ELocation.LIEKSA;
        loc[18] = ELocation.LILLEHAMMER;
        testKeeper.addLocations(loc[1], loc[2]);        // New Set (0)
        testKeeper.addLocations(loc[3], loc[4]);        // New Set (1)
        testKeeper.addLocations(loc[1], loc[6]);        // Adding set0
        testKeeper.addLocations(loc[5], loc[6]);        // Adding set0
        testKeeper.addLocations(loc[7], loc[8]);        // New Set (2)
        testKeeper.addLocations(loc[10], loc[3]);       // Adding set1
        testKeeper.addLocations(loc[12], loc[11]);      // New Set(3)
        testKeeper.addLocations(loc[9], loc[11]);       // Adding set3
        testKeeper.addLocations(loc[13], loc[14]);      // New Set(4)
        testKeeper.addLocations(loc[15], loc[13]);      // Adding set4
        testKeeper.addLocations(loc[13], loc[1]);       // Merging set0, set4
        testKeeper.addLocations(loc[16], loc[17]);      // New Set4
        testKeeper.addLocations(loc[17], loc[18]);      // Adding set4
        testKeeper.addLocations(loc[16], loc[7]);       // Merging set2, set4
        //Set0: 1, 2, 5, 6, 13, 14, 15
        //Set1: 3, 4, 10
        //Set2: 7, 8, 16, 17, 18
        //Set3: 9, 11, 12

        assertTrue(testKeeper.checkForRouteCompleted(loc[1], loc[15]));         // TRUE
        assertTrue(testKeeper.checkForRouteCompleted(loc[7], loc[18]));         // TRUE
        assertTrue(testKeeper.checkForRouteCompleted(loc[9], loc[12]));         // TRUE
        assertTrue(testKeeper.checkForRouteCompleted(loc[3], loc[10]));         // TRUE
        assertFalse(testKeeper.checkForRouteCompleted(loc[3], loc[8]));         // FALSE
        assertFalse(testKeeper.checkForRouteCompleted(loc[9], loc[10]));        // FALSE
        assertFalse(testKeeper.checkForRouteCompleted(loc[15], loc[12]));       // FALSE
        assertFalse(testKeeper.checkForRouteCompleted(loc[10], loc[12]));       // FALSE
    }
}