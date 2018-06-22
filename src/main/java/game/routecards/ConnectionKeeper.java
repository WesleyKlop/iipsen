package game.routecards;

import game.location.ELocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Thom
 */
public class ConnectionKeeper implements Serializable {

    private transient static final Logger Log = LogManager.getLogger(ConnectionKeeper.class);
    private List<Set<ELocation>> connections = new ArrayList<>();
    private static final int NOT_A_SET = -1;

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
        if (isMergeable(indexSet1, indexSet2)) {
            Log.debug("Sets can be merged!");
            mergeSets(indexSet1, indexSet2);
        } else if (isAddable(indexSet1)) {
            Log.debug("Adding location to set!");
            addELocationToSet(indexSet1, ELocation2);
        } else if (isAddable(indexSet2)) {
            Log.debug("Adding location to set!");
            addELocationToSet(indexSet2, ELocation1);
        } else {
            Log.debug("Creating new set!");
            createNewSet(ELocation1, ELocation2);
        }
    }


    private boolean isMergeable(int set1, int set2) {
        return set1 != set2 && set1 != NOT_A_SET && set2 != NOT_A_SET;
    }

    private boolean isAddable(int set) {
        return set != NOT_A_SET;
    }


    /**
     * This method return the set that a location is found in
     *
     * @param ELocation1 Location to be checked
     * @return set that the location is found in
     */
    private int getSetForELocation(ELocation ELocation1) {
        for (int i = 0, length = connections.size(); i < length; i++) {
            if (connections.get(i).contains(ELocation1)) {
                return i;
            }
        }
        return NOT_A_SET;
    }

    private void addELocationToSet(int set, ELocation loc) {
        connections.get(set).add(loc);
    }

    private void createNewSet(ELocation ELocation1, ELocation ELocation2) {
        Log.debug("Creating new set for locations: " + ELocation1 + " and " + ELocation2);
        Set<ELocation> newSet = new HashSet<>();
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
        int set1 = getSetForELocation(ELocation1);
        int set2 = getSetForELocation(ELocation2);
        return set1 == set2 && set1 != NOT_A_SET;
    }
}


/*
__/\\\________/\\\_______________________________________________________________/\\\\\\\\\________________________________________________________________________________________________________/\\\__
 _\/\\\_______\/\\\__________________________________/\\\_______________________/\\\\\\\\\\\\\_____________________________________________________________________________________________________\/\\\__
  _\/\\\_______\/\\\_________________________________\/\\\______________________/\\\/////////\\\____/\\\\\\\\\_____/\\\\\\\\\_______________________________________________________________________\/\\\__
   _\/\\\\\\\\\\\\\\\______/\\\\\\\\____/\\/\\\\\\____\/\\\\\\\\________________\/\\\_______\/\\\___/\\\/////\\\___/\\\/////\\\___/\\/\\\\\\\_______/\\\\\______/\\\____/\\\______/\\\\\\\\__________\/\\\__
    _\/\\\/////////\\\____/\\\/////\\\__\/\\\////\\\___\/\\\////\\\______________\/\\\\\\\\\\\\\\\__\/\\\\\\\\\\___\/\\\\\\\\\\___\/\\\/////\\\____/\\\///\\\___\//\\\__/\\\_____/\\\/////\\\____/\\\\\\\\\__
     _\/\\\_______\/\\\___/\\\\\\\\\\\___\/\\\__\//\\\__\/\\\\\\\\/_______________\/\\\/////////\\\__\/\\\//////____\/\\\//////____\/\\\___\///____/\\\__\//\\\___\//\\\/\\\_____/\\\\\\\\\\\____/\\\////\\\__
      _\/\\\_______\/\\\__\//\\///////____\/\\\___\/\\\__\/\\\///\\\_______________\/\\\_______\/\\\__\/\\\__________\/\\\__________\/\\\__________\//\\\__/\\\_____\//\\\\\_____\//\\///////____\/\\\__\/\\\__
       _\/\\\_______\/\\\___\//\\\\\\\\\\__\/\\\___\/\\\__\/\\\_\///\\\_____________\/\\\_______\/\\\__\/\\\__________\/\\\__________\/\\\___________\///\\\\\/_______\//\\\_______\//\\\\\\\\\\__\//\\\\\\\/\\_
        _\///________\///_____\//////////___\///____\///___\///____\///______________\///________\///___\///___________\///___________\///______________\/////__________\///_________\//////////____\///////\//__

 */
