import model.*;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.chrono.GregorianChronology;
import scheduler.PeriodicScheduler;
import state.BackupServerState;
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
    private static final DateTime END_DATE = new DateTime(2018, 1, 2, 0, 0, 0, GregorianChronology.getInstance());
    private static final FailureFactory<Computer> COMPUTER_FAILURE_FACTORY = new FailureFactory<>(0.0001, BACKUP_SERVER.getComputers());
    private static final FailureFactory<SubNetwork> SUB_NETWORK_FAILURE_FACTORY = new FailureFactory<>(0.00001, BACKUP_SERVER.getSubNetworks());

    public static void main(String[] args) {
        Scheduler simpleScheduler = new PeriodicScheduler(BACKUP_SERVER.getComputers());
        printResults(simpleScheduler, "simple scheduler");
    }


    private static void printResults(Scheduler scheduler, String shedulerName) {
        DataTransferSystem dts = new DataTransferSystem(
                scheduler,
                COMPUTER_FAILURE_FACTORY,
                SUB_NETWORK_FAILURE_FACTORY,
                new BackupServerState(START_DATE, BACKUP_SERVER),
                START_DATE,
                END_DATE);
        long damage = dts.countDamage();
        System.out.println(shedulerName + ": " + damage);
    }
}