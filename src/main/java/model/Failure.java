package model;

import org.joda.time.DateTime;

/**
 * Created by ksenia on 12.04.18.
 */
public class Failure<T> {
    private final DateTime date;
    private final T failed;

    public Failure(DateTime date, T failed) {
        this.date = date;
        this.failed = failed;
    }

    public DateTime getDate() {
        return date;
    }

    public T getFailed() {
        return failed;
    }
}
