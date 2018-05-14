package random;

import model.BackupServer;
import model.SubNetwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by ksenia on 12.04.18.
 */
public class RandomBackupServerFactory {
    private static final int MAX_AMOUNT_OF_NETWORKS = 100;
    private static final int MIN_AMOUNT_OF_NETWORKS = 3;
    private static final Random RANDOM = new Random();

    public static BackupServer getBackupServer() {
        int size = RANDOM.nextInt(MAX_AMOUNT_OF_NETWORKS - MIN_AMOUNT_OF_NETWORKS) +
                MIN_AMOUNT_OF_NETWORKS;
        Collection<SubNetwork> subNetworks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            subNetworks.add(RandomNetworkFactory.getNetwork());
        }
        return new BackupServer(subNetworks);
    }

}
