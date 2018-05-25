package model;

import java.io.Serializable;

/**
 * Created by ksenia on 23.04.18.
 */
public class Channel implements Serializable {
    private final SubNetwork from;
    private final SubNetwork to;
    private final int speed;

    public Channel(SubNetwork from, SubNetwork to, int speed) {
        this.from = from;
        this.to = to;
        this.speed = speed;
    }

    public SubNetwork getFrom() {
        return from;
    }

    public SubNetwork getTo() {
        return to;
    }

    public int getSpeed() {
        return speed;
    }
}