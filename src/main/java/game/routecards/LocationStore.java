package game.routecards;

import game.cards.CardType;

import java.util.HashMap;
import java.util.Map;

import static game.cards.CardType.*;
import static game.routecards.ELocation.*;

/**
 */
public class LocationStore {

    private Map<ELocation, Location> map = new HashMap<>();

    public static LocationStore Generate() {
        LocationStore store = new LocationStore();

        for (ELocation loc : ELocation.values()) {
            store.map.put(loc, new Location(loc));
        }

        store.addRoute(HONNINGSVAG, KIRKENES, CART_GREEN, 2, 1);
        store.addRoute(HONNINGSVAG, TROMSO, CART_PURPLE, 4, 2);
        store.addRoute(TROMSO, NARVIK, CART_YELLOW, 3, 1);
        store.addRoute(KIRKENES, MURMANSK, CART_WHITE, 3, 1);
        store.addRoute(KIRKENES, ROVANIEMI, CART_BLUE, 5);
        store.addRoute(ROVANIEMI, TORNIO, CART_RED, 1);
        store.addRoute(ROVANIEMI, OULU, CART_ORANGE, 2);
        store.addRoute(TORNIO, OULU, CART_WHITE, 1);
        store.addRoute(OULU, KAJAANI, CART_YELLOW, 2);
        store.addRoute(KAJAANI, LIEKSA, CART_BLUE, 1);
        store.addRoute(KAJAANI, KUOPIO, CART_GREEN, 2);
        store.addRoute(LIEKSA, KUOPIO, CART_BLACK, 1);
        store.addRoute(TORNIO, BODEN, CART_GREEN, 1);
        store.addRoute(OULU, VAASA, CART_BLACK, 3);
        store.addRoute(KUOPIO, IMATRA, CART_PURPLE, 2);
        store.addRoute(KUOPIO, LAHTI, CART_WHITE, 3);
        store.addRoute(IMATRA, LAHTI, CART_YELLOW, 2);
        store.addRoute(IMATRA, HELSINKI, CART_RED, 3);
        store.addRoute(LAHTI, HELSINKI, CART_BLACK, 1);
        store.addRoute(HELSINKI, TAMPERE, CART_ORANGE, 1);
        store.addRoute(LAHTI, TAMPERE, CART_BLUE, 1);
        store.addRoute(TAMPERE, TURKU, CART_RED, 1);
        store.addRoute(HELSINKI, TURKU, CART_WHITE, 1);
        store.addRoute(TAMPERE, VAASA, CART_PURPLE, 2);
        store.addRoute(NARVIK, MO_I_RANA, CART_ORANGE, 4, 2);
        store.addRoute(MO_I_RANA, TRONDHEIM, CART_RED, 6, 2);
        store.addRoute(VAASA, SUNDSVALL, CART_BLUE, 3, 1);
        store.addRoute(SUNDSVALL, OSTERSUND, CART_GREEN, 2);
        store.addRoute(SUNDSVALL, OREBRO, CART_ORANGE, 4);
        store.addRoute(TRONDHEIM, ANDALSNES, CART_WHITE, 2, 1);
        store.addRoute(ANDALSNES, BERGEN, CART_BLACK, 5, 2);
        store.addRoute(BERGEN, STAVANGER, CART_PURPLE, 2, 1);
        store.addRoute(STAVANGER, KRISTIANSAND, CART_ORANGE, 3, 1);
        store.addRoute(ALBORG, KRISTIANSAND, CART_RED, 2, 1);
        store.addRoute(ALBORG, ARHUS, CART_PURPLE, 1);
        store.addRoute(OSLO, GOTEBORG, CART_ORANGE, 2);
        store.addRoute(OSLO, ALBORG, CART_WHITE, 3, 1);
        store.addRoute(GOTEBORG, OREBRO, CART_BLUE, 2);
        store.addRoute(GOTEBORG, KOBENHAVN, CART_BLACK, 2, 1);

        // TODO: tunnel routes
        // TODO: any routes


        return store;
    }

    private void addRoute(ELocation loc1, ELocation loc2, CardType routeColor, int length, int locCost) {
        Route route = new Route(length, locCost, routeColor);
        map.get(loc1).addRoute(loc2, route);
        map.get(loc2).addRoute(loc1, route);
    }

    private void addRoute(ELocation loc1, ELocation loc2, CardType routeColor, int length) {
        addRoute(loc1, loc2, routeColor, length, 0);
    }

    public Location getLocation(ELocation loc) {
        return map.get(loc);
    }
}
