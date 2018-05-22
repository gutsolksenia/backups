package scheduler;

import model.Computer;

import java.util.List;

/**
 * Created by ksenia on 13.04.18.
 */
public interface Scheduler {
    List<Computer> getNext();
}