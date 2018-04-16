package model;

import java.util.Collection;

/**
 * Created by ksenia on 14.12.17.
 */
public class Computer {
    private int backupTimeInSeconds;
    private final Double importance = 0.001;
    private Collection<BackupInterval> backupIntervals;

    public Computer(int backupTimeInSeconds, Collection<BackupInterval> backupIntervals) {
        this.backupTimeInSeconds = backupTimeInSeconds;
        this.backupIntervals = backupIntervals;
    }

    public Collection<BackupInterval> getBackupWindows() {
        return backupIntervals;
    }

    public int getBackupTimeInSeconds() {
        return backupTimeInSeconds;
    }

    public Double getImportance() {
        return importance;
    }
}