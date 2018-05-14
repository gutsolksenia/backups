package model;

/**
 * Created by ksenia on 23.04.18.
 */
public class Channel {
    private final Computer from;
    private final Computer to;
    private final int speed;

    public Channel(Computer from, Computer to, int speed) {
        this.from = from;
        this.to = to;
        this.speed = speed;
    }

    public Computer getFrom() {
        return from;
    }

    public Computer getTo() {
        return to;
    }

    public int getSpeed() {
        return speed;
    }
}