package game.routecards;

import game.location.ELocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Thom
 */
public class ConnectionKeeper {

    private static final Logger Log = LogManager.getLogger(ConnectionKeeper.class);
    private ArrayList<HashSet<ELocation>> connections = new ArrayList();

    /**
     * This method adds a route to the keeper by giving the 2 locations that the route connects
     *
     * @param ELocation1 First Location of the route to be added
     * @param ELocation2 Second Location of the route to be added
     */
    public void addLocations(ELocation ELocation1, ELocation ELocation2) {
        Log.debug("Adding routes: " + ELocation1 + " and " + ELocation2);
        int indexSet1 = getSetForELocation(ELocation1);
        int indexSet2 = getSetForELocation(ELocation2);
        if (indexSet1 != indexSet2 && indexSet1 != -1 && indexSet2 != -1) {
            Log.debug("Multiple sets found, merging sets!");
            mergeSets(indexSet1, indexSet2);
        } else if (indexSet1 != -1) {
            Log.debug("1 Location connects: " + ELocation1);
            Log.debug("Adding location " + ELocation2 + " to set" + indexSet1);
            addELocationToSet(indexSet1, ELocation2);
        } else if (indexSet2 != -1) {
            Log.debug("1 Location connects: " + ELocation2);
            Log.debug("Adding location " + ELocation1 + " to set" + indexSet2);
            addELocationToSet(indexSet2, ELocation1);
        } else {
            Log.debug("None of the locations connect, making a new set!");
            createNewSet(ELocation1, ELocation2);
        }
    }


    /**
     * @param ELocation1
     * @return set that the location is found in, returns -1 for no set found
     */
    private int getSetForELocation(ELocation ELocation1) {
        for (int i = 0; i < connections.size(); i++) {
            for (int j = 0; j < connections.get(i).size(); j++) {
                if (connections.get(i).contains(ELocation1)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void addELocationToSet(int set, ELocation ELocation) {
        Log.debug("Adding " + ELocation + " to set" + set);
        connections.get(set).add(ELocation);
    }

    private void createNewSet(ELocation ELocation1, ELocation ELocation2) {
        Log.debug("Creating new set for locations: " + ELocation1 + " and " + ELocation2);
        HashSet<ELocation> newSet = new HashSet<>();
        newSet.add(ELocation1);
        newSet.add(ELocation2);
        connections.add(newSet);
    }

    private void mergeSets(int set1, int set2) {
        Log.debug("Merging sets: " + set1 + " and " + set2);
        connections.get(set1).addAll(connections.get(set2));
        connections.remove(set2);
    }

    /**
     * This method checks whether or not 2 locations are connected by a players routes
     *
     * @param ELocation1 First location of the complete route
     * @param ELocation2 Second location of the complete route
     * @return if the 2 locations are connected by the players routes
     */
    public boolean checkForRouteCompleted(ELocation ELocation1, ELocation ELocation2) {
        return getSetForELocation(ELocation1) == getSetForELocation(ELocation2)
                && getSetForELocation(ELocation1) != -1;
    }
}
