package game.routecards;

import game.GameStore;

public class RouteDoubleCheck {
    boolean answer;
    boolean answer2;

    public boolean checkDouble(Route route, GameStore store) {
        if (route.getId() % 2 == 1) {
            answer2 = uneven(route.getId(), store);
        }
        if (route.getId() % 2 == 0) {
            answer2 = even(route.getId(), store);
        }
        return answer2;
    }

    private boolean even(int id, GameStore store) {
        if (store.getRouteStore().getRouteById(id + 1).getOwner() == 0) {
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }

    private boolean uneven(int id, GameStore store) {
        if (store.getRouteStore().getRouteById(id - 1).getOwner() == 0) {
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }
}

