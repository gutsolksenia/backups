package model;


import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * Created by ksenia on 12.04.18.
 */
public class BackupInterval {
    private final Period start;
    private final Period end;

    public BackupInterval(Period start, Period end) {
        this.start = start;
        this.end = end;
    }

    public Period getStart() {
        return start;
    }

    public Period getEnd() {
        return end;
    }

    public boolean endsAfter(DateTime date) {
        return end.toStandardDuration().getMillis() >= date.getMillisOfDay();
    }
}
