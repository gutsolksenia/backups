package random;

import model.Failure;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by ksenia on 12.04.18.
 */
public class FailureFactory<T> {
    private static final Random RANDOM = new Random();
    private final DateTime startDate;
    private final DateTime endDate;

    public FailureFactory(DateTime startDate, DateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Failure<T>> getFailures(Collection<T> entities, double failureProbability) {
        List<Failure<T>> failures = new ArrayList<>();
        entities.forEach(entity -> {
            if (RANDOM.nextFloat() < failureProbability) {
                failures.add(new Failure<>(getDate(), entity));
            }
        });
        failures.sort((f1, f2) -> f1.getDate().compareTo(f2.getDate()));
        return failures;
    }

    private DateTime getDate() {
        return startDate.plus(Math.round(
                        RANDOM.nextFloat() * (endDate.getMillis() - startDate.getMillis())
                ));
    }
}