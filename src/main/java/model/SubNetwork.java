package model;

import java.util.Collection;

/**
 * Created by ksenia on 12.04.18.
 */
public class SubNetwork {
    private final int backupSpeed;
    private final Collection<Computer> computers;

    public SubNetwork(int backupSpeed, Collection<Computer> computers) {
        this.backupSpeed = backupSpeed;
        this.computers = computers;
    }

    public Collection<Computer> getComputers() {
        return computers;
    }

    public int getBackupSpeed() {
        return backupSpeed;
    }
}