package game.routecards;

import game.location.ELocation;

import java.io.Serializable;

public class RouteCard implements Serializable {
    private ELocation start;
    private ELocation end;
    private int value;
    private boolean completed = false;

    public RouteCard(ELocation start, ELocation end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public void setCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }
}
