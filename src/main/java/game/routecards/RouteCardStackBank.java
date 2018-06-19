package game.routecards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.location.ELocation.*;

/**
 * Contains methods for managing the route cards that can be picked by players.
 *
 * @author Wesley Klop
 */
public class RouteCardStackBank implements Serializable {
    private final Random generator = new Random();
    private List<RouteCard> routeCards = new ArrayList<>();

    public RouteCardStackBank() {
        fillRouteBank();
    }

    public RouteCard getRandomRouteCard() {
        int index = generator.nextInt(routeCards.size());
        RouteCard card = routeCards.get(index);
        routeCards.remove(index);
        return card;
    }

    public void fillRouteBank() {
        routeCards.add(new RouteCard(ALBORG, NORRKOPING, 5));
        routeCards.add(new RouteCard(ALBORG, UMEA, 11));
        routeCards.add(new RouteCard(ARHUS, LILLEHAMMER, 6));
        routeCards.add(new RouteCard(BERGEN, KOBENHAVN, 8));
        routeCards.add(new RouteCard(BERGEN, NARVIK, 16));
        routeCards.add(new RouteCard(BERGEN, TORNIO, 17));
        routeCards.add(new RouteCard(BERGEN, TRONDHEIM, 7));
        routeCards.add(new RouteCard(GOTEBORG, ANDALSNES, 6));
        routeCards.add(new RouteCard(GOTEBORG, OULU, 12));
        routeCards.add(new RouteCard(GOTEBORG, TURKU, 7));
        routeCards.add(new RouteCard(HELSINKI, BERGEN, 12));
        routeCards.add(new RouteCard(HELSINKI, KIRKENES, 13));
        routeCards.add(new RouteCard(HELSINKI, KIRUNA, 10));
        routeCards.add(new RouteCard(HELSINKI, KOBENHAVN, 10));
        routeCards.add(new RouteCard(HELSINKI, LIEKSA, 5));
        routeCards.add(new RouteCard(HELSINKI, OSTERSUND, 8));
        routeCards.add(new RouteCard(KOBENHAVN, MURMANSK, 24));
        routeCards.add(new RouteCard(KOBENHAVN, NARVIK, 18));
        routeCards.add(new RouteCard(KOBENHAVN, OULU, 14));
        routeCards.add(new RouteCard(KRISTIANSAND, MOIRANA, 12));
        routeCards.add(new RouteCard(NARVIK, MURMANSK, 12));
        routeCards.add(new RouteCard(NARVIK, TALLINN, 13));
        routeCards.add(new RouteCard(NORRKOPING, BODEN, 11));
        routeCards.add(new RouteCard(OREBRO, KUOPIO, 10));
        routeCards.add(new RouteCard(OSLO, HELSINKI, 8));
        routeCards.add(new RouteCard(OSLO, HONNINGSVAG, 21));
        routeCards.add(new RouteCard(OSLO, KOBENHAVN, 4));
        routeCards.add(new RouteCard(OSLO, MOIRANA, 10));
        routeCards.add(new RouteCard(OSLO, STAVANGER, 4));
        routeCards.add(new RouteCard(OSLO, STOCKHOLM, 4));
        routeCards.add(new RouteCard(OSLO, VAASA, 9));
        routeCards.add(new RouteCard(STAVANGER, KARLSKRONA, 8));
        routeCards.add(new RouteCard(STAVANGER, ROVANIEMI, 18));
        routeCards.add(new RouteCard(STOCKHOLM, BERGEN, 8));
        routeCards.add(new RouteCard(STOCKHOLM, IMATRA, 7));
        routeCards.add(new RouteCard(STOCKHOLM, KAJAANI, 10));
        routeCards.add(new RouteCard(STOCKHOLM, KOBENHAVN, 6));
        routeCards.add(new RouteCard(STOCKHOLM, TROMSO, 17));
        routeCards.add(new RouteCard(STOCKHOLM, UMEA, 7));
        routeCards.add(new RouteCard(SUNDSVALL, LAHTI, 6));
        routeCards.add(new RouteCard(TAMPERE, BODEN, 6));
        routeCards.add(new RouteCard(TAMPERE, KRISTIANSAND, 10));
        routeCards.add(new RouteCard(TAMPERE, TALLINN, 3));
        routeCards.add(new RouteCard(TORNIO, IMATRA, 6));
        routeCards.add(new RouteCard(TROMSO, VAASA, 11));
        routeCards.add(new RouteCard(TURKU, TRONDHEIM, 10));
    }
}
