package game.routecards;

import game.GameStore;
import game.player.Player;

public class RouteDoubleCheck {

    final int coupleId = -1;

    public boolean checkDouble(Route route, GameStore store) {
        return store.getRouteStore().getRouteById(route.getcoupleId()).hasOwner();
    }

    public boolean checkSameOwner(Route route, GameStore store, Player player) {
        return store.getRouteStore().getRouteById(route.getcoupleId()).getOwner() == player.getId();
    }
}

