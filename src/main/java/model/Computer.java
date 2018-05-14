package model;

import java.util.Collection;
import java.util.List;

/**
 * Created by ksenia on 14.12.17.
 */
public class Computer {
    private int storedData;
    private final Double importance = 0.001;
    private Collection<BackupInterval> backupIntervals;
    private final SubNetwork subNetwork;
    private List<Channel> connections;

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

    public List<Channel> getConnections() {
        return connections;
    }

    public void setConnections(List<Channel> connections) {
        this.connections = connections;
    }
}