package scheduler;

import model.Computer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PeriodicScheduler implements Scheduler {
    private final Queue<Computer> computerQueue;

    public PeriodicScheduler(List<Computer> computers) {
        computerQueue = new LinkedList<>(computers);
    }

    @Override
    public List<Computer> getNext() {
        Computer next =  computerQueue.poll();
        if (next == null) {
            return Collections.EMPTY_LIST;
        }
        computerQueue.add(next);
        return Collections.singletonList(next);
    }
}