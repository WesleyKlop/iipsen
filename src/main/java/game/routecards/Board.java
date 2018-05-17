package game.routecards;

import game.cards.CardType;

/**
 * @author wesley
 */
public class Board {
    LocationStore locationStore = LocationStore.Generate();


    public Board() {

    }

    public static void main(String[] args) {
        Board test = new Board();
        Player wesley = new Player(EPlayer.PLAYER_ONE, "Wesley");
//        wesley.addRouteCard(new RouteCard(test.locationSet.get()));
        wesley.getCardStack().addCard(CardType.CART_ORANGE);
        wesley.getCardStack().addCard(CardType.CART_ORANGE);
        test.buildRoute(ELocation.OSLO, ELocation.GOTEBORG, wesley);
    }

    public void buildRoute(ELocation start, ELocation end, Player player) {
        Route route = locationStore.getLocation(start).getRouteToLocation(end);
        route.build(player);
    }
}
