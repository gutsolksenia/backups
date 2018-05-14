package random;

import model.Computer;
import model.SubNetwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by ksenia on 12.04.18.
 */
public class RandomNetworkFactory {
    private static final int MAX_SIZE = 300;
    private static final int MIN_BACKUP_SPEED = 10;
    private static final int MAX_BACKUP_SPEED = 100;
    private static final Random random = new Random();

    public static SubNetwork getNetwork() {
        Collection<Computer> computers = new ArrayList<>();
        SubNetwork subNetwork = new SubNetwork(getBackupSpeed(), computers);
        int size = random.nextInt(MAX_SIZE);
        for (int i = 0; i < size; i++) {
            computers.add(RandomComputerFactory.getComputer(subNetwork));
        }
        return subNetwork;
    }

    private RandomNetworkFactory() {}

    private static int getBackupSpeed() {
        return random.nextInt(MAX_BACKUP_SPEED - MIN_BACKUP_SPEED) +
                MIN_BACKUP_SPEED;
    }
}