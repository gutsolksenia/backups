package state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by ksenia on 12.04.18.
 */

public class FailureFactory<T> {
    private static final Random RANDOM = new Random();
    private final double failureProbability;
    private final Collection<T> entities;

    public FailureFactory(double failureProbability, Collection<T> entities) {
        this.failureProbability = failureProbability;
        this.entities = entities;
    }

    public List<T> getNext() {
        List<T> failed = new ArrayList<>();
        entities.forEach(entity -> {
            if (RANDOM.nextFloat() < failureProbability) {
                failed.add(entity);
            }
        });
        return failed;
    }
}