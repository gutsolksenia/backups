package model;

import flow.Edge;
import flow.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ksenia on 12.04.18.
 */
public class SubNetwork extends Node implements Serializable{
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

    public List<Channel> getChannels() {
        return super.getEdgesFrom();
    }
}