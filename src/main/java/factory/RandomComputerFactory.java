package factory;

import model.BackupInterval;
import model.Computer;
import model.SubNetwork;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ksenia on 14.12.17.
 */
public class RandomComputerFactory {

    public static Computer getComputer(SubNetwork subNetwork) {
        Collection<BackupInterval> backupIntervals = new ArrayList<>();
        backupIntervals.add(randomNight());
        backupIntervals.add(randomLunch());
        int backupTime = IntRandom.nextInt(
                Constants.MIN_DATA_AMOUNT,
                Constants.MAX_DATA_AMOUNT);
        return new Computer(backupTime, backupIntervals, subNetwork);
     }

    private static BackupInterval randomNight() {
        Period start = Constants.EVENING.plusHours(IntRandom.nextInt(4));
        Period end = Constants.MORNING.plusHours(IntRandom.nextInt(4));
        return new BackupInterval(start, end);
    }

    private static BackupInterval randomLunch() {
        Period start = Constants.MORNING.plusHours(IntRandom.nextInt(3, 6));
        Period end = start.plusHours(1);
        return new BackupInterval(start, end);
    }

    private RandomComputerFactory() {}
}