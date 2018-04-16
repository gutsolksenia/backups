package model;

import java.util.Collection;

/**
 * Created by ksenia on 12.04.18.
 */
public class Network {
    private final Collection<Computer> computers;

    public Network(Collection<Computer> computers) {
        this.computers = computers;
    }

    public Collection<Computer> getComputers() {
        return computers;
    }
}