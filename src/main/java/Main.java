import model.*;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.chrono.GregorianChronology;
import state.DataTransferSystem;
import state.FailureFactory;
import factory.RandomBackupServerFactory;
import scheduler.Scheduler;

/**
 * Created by ksenia on 13.04.18.
 */
public class Main {
    private static final BackupServer BACKUP_SERVER = RandomBackupServerFactory.getBackupServer();
    private static final DateTime START_DATE = new DateTime(2018, 1, 1, 0, 0, 0, GregorianChronology.getInstance());
    private static final DateTime END_DATE = new DateTime(2018, 2, 1, 0, 0, 0, GregorianChronology.getInstance());
    private static final FailureFactory<Computer> COMPUTER_FAILURE_FACTORY = new FailureFactory<>(0.0001, BACKUP_SERVER.getComputers());
    private static final FailureFactory<SubNetwork> SUB_NETWORK_FAILURE_FACTORY = new FailureFactory<>(0.00001, BACKUP_SERVER.getSubNetworks());


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
        DataTransferSystem dts = new DataTransferSystem(
                scheduler,
                COMPUTER_FAILURE_FACTORY,
                SUB_NETWORK_FAILURE_FACTORY,
                START_DATE,
                END_DATE);
        long damage = dts.countDamage();
        System.out.println(shedulerName + ": " + damage);
    }
}