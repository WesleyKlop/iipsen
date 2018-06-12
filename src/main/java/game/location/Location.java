package game.location;

import game.routecards.Route;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class Location {
    private ELocation location;
    private Map<ELocation, Route> connections;

    public Location(ELocation location) {
        this.location = location;
        this.connections = new HashMap<>();
    }

    public Route getRouteToLocation(ELocation location) {
        return connections.get(location);
    }

    public void addRoute(ELocation destination, Route route) {
        connections.put(destination, route);
    }

    public Map<ELocation, Route> getConnections() {
        return connections;
    }

    public ELocation getLocation() {
        return this.location;
    }
}
