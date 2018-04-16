package random;

import model.Computer;
import model.Network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by ksenia on 12.04.18.
 */
public class RandomNetworkFactory {
    private static final int MAX_SIZE = 300;
    private static final Random random = new Random();

    public static Network getNetwork() {
        Collection<Computer> computers = new ArrayList<>();
        int size = random.nextInt(MAX_SIZE);
        for (int i = 0; i < size; i++) {
            computers.add(RandomComputerFactory.getComputer());
        }
        return new Network(computers);
    }

    private RandomNetworkFactory() {}
}