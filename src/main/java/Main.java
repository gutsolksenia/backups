import model.*;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.chrono.GregorianChronology;
import random.FailureFactory;
import random.RandomBackupServerFactory;
import scheduler.DamageCounter;
import scheduler.PeriodicScheduler;
import scheduler.Scheduler;

import java.util.List;

/**
 * Created by ksenia on 13.04.18.
 */
public class Main {
    private static final BackupServer BACKUP_SERVER = RandomBackupServerFactory.getBackupServer();
    private static final DateTime START_DATE = new DateTime(2018, 1, 1, 0, 0, 0, GregorianChronology.getInstance());
    private static final DateTime END_DATE = new DateTime(2018, 2, 1, 0, 0, 0, GregorianChronology.getInstance());
    private static final List<Failure<Computer>> SMALL_FAILURES;
    private static final List<Failure<SubNetwork>> BIG_FAILURES;

    static {
        FailureFactory failureFactory = new FailureFactory(START_DATE, END_DATE);
        SMALL_FAILURES = failureFactory.getFailures(BACKUP_SERVER.getComputers(), 0.2);
        BIG_FAILURES = failureFactory.getFailures(BACKUP_SERVER.getSubNetworks(), 0.0005);
    }

    public static void main(String[] args) {
        printResults(fourHoursScheduler(), "4 hours scheduler");
        printResults(nightScheduler(), "night scheduler");
        printResults(weekendScheduler(), "weekend sheduler");
    }

    private static Scheduler fourHoursScheduler() {
        Period forHours = new Period(4, 0, 0, 0);
        return new PeriodicScheduler(BACKUP_SERVER, forHours, forHours, START_DATE);
    }

    private static Scheduler nightScheduler() {
        Period day = new Period(0, 0, 0, 1, 0, 0, 0, 0);
        Period night = new Period(7, 0, 0, 0);
        return new PeriodicScheduler(BACKUP_SERVER, day, night, START_DATE);
    }

    private static Scheduler weekendScheduler() {
        Period week = new Period(0, 0, 1, 0, 0, 0, 0, 0);
        Period weekend = new Period(0, 0, 0, 2, 0, 0, 0, 0);
        return new PeriodicScheduler(BACKUP_SERVER, week, weekend, START_DATE);
    }

    private static void printResults(Scheduler scheduler, String shedulerName) {
        List<Backup> schedule = scheduler.generateSchedule(START_DATE, END_DATE);
        long damage = DamageCounter.count(schedule, SMALL_FAILURES, BIG_FAILURES, START_DATE);
        System.out.println(shedulerName + ": " + damage);
    }
}