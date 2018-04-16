package model;

import org.joda.time.DateTime;

/**
 * Created by ksenia on 13.04.18.
 */
public class Backup {
    private final Computer computer;
    private final DateTime start;
    private final DateTime end;

    public Backup(Computer computer, DateTime start, DateTime end) {
        this.computer = computer;
        this.start = start;
        this.end = end;
    }

    public Computer getComputer() {
        return computer;
    }

    public DateTime getStart() {
        return start;
    }

    public DateTime getEnd() {
        return end;
    }
}