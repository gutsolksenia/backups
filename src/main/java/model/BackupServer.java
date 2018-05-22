package model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ksenia on 14.12.17.
 */
public class BackupServer implements Serializable {
    private final List<SubNetwork> subNetworks;

    public BackupServer(List<SubNetwork>subNetworks) {
        this.subNetworks = subNetworks;
    }

    public List<SubNetwork> getSubNetworks() {
        return subNetworks;
    }

    public List<Computer> getComputers() {
        return subNetworks.stream()
                .flatMap(subNetwork -> subNetwork.getComputers().stream())
                .collect(Collectors.toList());
    }
}