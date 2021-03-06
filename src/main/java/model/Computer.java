package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by ksenia on 14.12.17.
 */
public class Computer implements Serializable {
    private int storedData;
    private final Double importance = 0.001;
    private final Collection<BackupInterval> backupIntervals;
    private final SubNetwork subNetwork;

    public Computer(int storedData, Collection<BackupInterval> backupIntervals, SubNetwork subNetwork) {
        this.storedData = storedData;
        this.backupIntervals = backupIntervals;
        this.subNetwork = subNetwork;
    }

    public Collection<BackupInterval> getBackupWindows() {
        return backupIntervals;
    }

    public int getBackupTime() {
        return storedData / subNetwork.getBackupSpeed();
    }

    public SubNetwork getSubNetwork() {
        return subNetwork;
    }
    public int getStoredData() {
        return storedData;
    }

    public Double getImportance() {
        return importance;
    }
}