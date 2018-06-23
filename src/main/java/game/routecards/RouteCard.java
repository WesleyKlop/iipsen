package game.routecards;

import game.location.ELocation;

import java.io.Serializable;

public class RouteCard implements Serializable {
    private final ELocation start;
    private final ELocation end;
    private final int value;

    public RouteCard(ELocation start, ELocation end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("RouteCard{ start=%s, end=%s, value=%d }", start, end, value);
    }
}
