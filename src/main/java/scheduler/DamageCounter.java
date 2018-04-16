package scheduler;

import model.Backup;
import model.Computer;
import model.Failure;
import model.Network;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ksenia on 13.04.18.
 */
public class DamageCounter {

    public static long count(List<Backup> backups,
                             List<Failure<Computer>> smallFailures,
                             List<Failure<Network>> bigFailures,
                             DateTime startDate) {

        long smallFailureDamage = countSmallFailureDamage(backups,smallFailures,startDate);
        long bigFailureDamage = countBigFailureDamage(backups, bigFailures, startDate);
        return smallFailureDamage + bigFailureDamage;
    }

    private static long countSmallFailureDamage(List<Backup> backups, List<Failure<Computer>> smallFailures, DateTime startDate) {
        Map<Computer, DateTime> lastBackup = new HashMap<>();
        final int[] i = {0, 0};
        final long[] damage = {0, 0};

        smallFailures.forEach(f -> {
            while (i[0] < backups.size() && backups.get(i[0]).getEnd().isBefore(f.getDate())) {
                lastBackup.put(backups.get(i[0]).getComputer(), backups.get(i[0]).getEnd());
                i[0]++;
            }
            damage[0] += damage(f.getFailed(), lastBackup.getOrDefault(f.getFailed(), startDate),f.getDate());
        });
        return damage[0];
    }

    private static long countBigFailureDamage(List<Backup> backups, List<Failure<Network>> bigFailures, DateTime startDate) {
        Map<Computer, DateTime> lastBackup = new HashMap<>();
        final int[] i = {0, 1};
        final int[] damage = {0, 1};

        bigFailures.forEach(f -> {
            while (i[0] < backups.size() && backups.get(i[0]).getEnd().isBefore(f.getDate())) {
                lastBackup.put(backups.get(i[0]).getComputer(), backups.get(i[0]).getEnd());
                i[0]++;
            }
            f.getFailed().getComputers().forEach(computer -> {
                damage[0] += damage(computer, lastBackup.getOrDefault(computer, startDate), f.getDate());
            });
        });

        return damage[0];
    }

    private static long damage(Computer computer, DateTime lastBackup, DateTime fail) {
        return Math.round(fail.minus(lastBackup.getMillis()).getMillis() * computer.getImportance());
    }
}