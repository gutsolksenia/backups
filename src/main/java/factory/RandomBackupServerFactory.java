package factory;

import model.BackupServer;
import model.SubNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksenia on 12.04.18.
 */
public class RandomBackupServerFactory {
    public static BackupServer getBackupServer() {
        int size = IntRandom.nextInt(
                Constants.MIN_SUB_NETWORKS_COUNT,
                Constants.MAX_SUB_NETWORKS_COUNT);
        List<SubNetwork> networks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            networks.add(RandomSubNetworkFactory.getSubNetwork());
        }
        return new BackupServer(networks);
    }

    private RandomBackupServerFactory() {}

}