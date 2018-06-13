package game.routecards;

import java.io.Serializable;

public class SelectableRouteCards implements Serializable {

    private static final int PICKABLE_CARD_COUNT = 3;
    private RouteCardStackBank bank;
    private RouteCard[] pickableCards = new RouteCard[PICKABLE_CARD_COUNT];

    public SelectableRouteCards(RouteCardStackBank bank) {
        this.bank = bank;
        populatePickableCards();
    }


    public void populatePickableCards() {
        for (int i = 0; i < PICKABLE_CARD_COUNT; i++) {
            pickableCards[i] = bank.getRandomRouteCard();
        }
    }

    public RouteCard[] getPickableCards() {
        return pickableCards;
    }
}
