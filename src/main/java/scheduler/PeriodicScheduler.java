package scheduler;

import model.Backup;
import model.BackupInterval;
import model.BackupServer;
import model.Computer;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by ksenia on 13.04.18.
 */
public class PeriodicScheduler implements Scheduler {
    private final BackupServer backupServer;
    private final Period period;
    private final Period backupDuration;
    private final DateTime firstBackupDate;
    private DateTime now;
    private List<Backup> schedule = new ArrayList<>();
    private Queue<Computer> computers;

    public PeriodicScheduler(BackupServer backupServer, Period period, Period backupDuration, DateTime firstBackupDate) {
        this.backupServer = backupServer;
        if (isLonger(backupDuration, period)) {
            throw new IllegalArgumentException("Backup duration should not be greater than period");
        }
        this.period = period;
        this.backupDuration = backupDuration;
        this.firstBackupDate = firstBackupDate;
        this.now = firstBackupDate;
    }

    @Override
    public List<Backup> generateSchedule(DateTime startDate, DateTime endDate) {
        computers = new LinkedList<>(backupServer.getComputers());
        while (now.isBefore(endDate) && !now.equals(endDate)) {
            backupPeriod();
        }
        return schedule;
    }

    private static boolean isLonger(Period p1, Period p2) {
        Instant now = Instant.now();
        Duration d1 = p1.toDurationTo(now);
        Duration d2 = p2.toDurationTo(now);
        return d1.isLongerThan(d2);
    }

    private void backupPeriod() {
        DateTime backupIterationEnd = now.plus(backupDuration);
        if (computers.isEmpty()) {
            now = backupIterationEnd;
            return;
        }
        Computer firstComputer = computers.peek();
        while (now.isBefore(backupIterationEnd)) {
            Computer computer = computers.poll();
            computers.add(computer);
            BackupInterval interval = computer.getBackupWindows()
                    .stream()
                    .filter(w -> {
                        /*System.out.println(now);
                        System.out.println(now.plusSeconds(computer.getBackupTimeInSeconds()));
                        System.out.println( w.endsAfter(now.plusSeconds(computer.getBackupTimeInSeconds())));*/
                        return w.endsAfter(now.plusSeconds(computer.getBackupTimeInSeconds()));

                    })
                    .findAny()
                    .orElse(null);
            if (interval != null) {
                DateTime backupEnd = now.plusSeconds(computer.getBackupTimeInSeconds());
                schedule.add(new Backup(computer, now, backupEnd));
                now = backupEnd;
            }
            if (computers.peek().equals(firstComputer)) {
                now = backupIterationEnd;
                return;
            }
        }
        now = backupIterationEnd;
    }
}