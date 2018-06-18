package game.routecards;

import game.location.ELocation;

import java.io.Serializable;

public class RouteCard implements Serializable {
    private final ELocation start;
    private final ELocation end;
    private final int value;
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

    public ELocation getStart() {
        return start;
    }

    public ELocation getEnd() {
        return end;
    }

    public String getImagePath() {
        return String.format("/routecards/nc-%s-%s.png",
            start.toString().toLowerCase(),
            end.toString().toLowerCase()
        );
    }
}
