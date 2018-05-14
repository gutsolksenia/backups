package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ksenia on 14.12.17.
 */
public class BackupServer {
    private final Collection<SubNetwork> subNetworks;

    public BackupServer(Collection<SubNetwork> subNetworks) {
        this.subNetworks = subNetworks;
    }

    public Collection<SubNetwork> getSubNetworks() {
        return subNetworks;
    }

    public Collection<Computer> getComputers() {
        return subNetworks.stream()
                .map(SubNetwork::getComputers)
                .collect(
                        ArrayList::new,
                        ArrayList::addAll,
                        ArrayList::addAll
                );
    }
}