package factory;

import model.Computer;
import model.SubNetwork;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ksenia on 12.04.18.
 */
public class RandomSubNetworkFactory {
    public static SubNetwork getSubNetwork() {
        Collection<Computer> computers = new ArrayList<>();
        int backupSpeed = IntRandom.nextInt(
                Constants.MIN_BACKUP_SPEED,
                Constants.MAX_BACKUP_SPEED);
        SubNetwork subNetwork = new SubNetwork(backupSpeed, computers);
        int size = IntRandom.nextInt(Constants.MAX_SUB_NETWORK_SIZE);
        for (int i = 0; i < size; i++) {
            computers.add(RandomComputerFactory.getComputer(subNetwork));
        }
        return subNetwork;
    }

    private RandomSubNetworkFactory() {}
}