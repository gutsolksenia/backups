package random;

import model.BackupInterval;
import model.Computer;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by ksenia on 14.12.17.
 */
public class RandomComputerFactory {
    private static final Period EVENING = new Period(18, 0, 0, 0);
    private static final Period MORNING = new Period(8, 0, 0, 0);
    private static final int MAX_BACKUP_TIME_IN_SECONDS = 40000;
    private static final int MIN_BACKUP_TIME_IN_SECONDS = 20000;
    private static final Random random = new Random();

    public static Computer getComputer() {
        Collection<BackupInterval> backupIntervals = new ArrayList<>();
        backupIntervals.add(randomNight());
        backupIntervals.add(randomLunch());
        int backupTime = random.nextInt(MAX_BACKUP_TIME_IN_SECONDS - MIN_BACKUP_TIME_IN_SECONDS) +
                MIN_BACKUP_TIME_IN_SECONDS;
        return new Computer(backupTime, backupIntervals);
     }

    private static BackupInterval randomNight() {
        Period start = EVENING.plusHours(random.nextInt(4));
        Period end = MORNING.plusHours(random.nextInt(4));
        return new BackupInterval(start, end);
    }

    private static BackupInterval randomLunch() {
        Period start = MORNING.plusHours(3 + random.nextInt(3));
        Period end = start.plusHours(1);
        return new BackupInterval(start, end);
    }

    private RandomComputerFactory() {}
}