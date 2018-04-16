package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ksenia on 14.12.17.
 */
public class BackupServer {
    private final Collection<Network> networks;

    public BackupServer(Collection<Network> networks) {
        this.networks = networks;
    }

    public Collection<Network> getNetworks() {
        return networks;
    }

    public Collection<Computer> getComputers() {
        return networks.stream()
                .map(Network::getComputers)
                .collect(
                        ArrayList::new,
                        ArrayList::addAll,
                        ArrayList::addAll
                );
    }
}