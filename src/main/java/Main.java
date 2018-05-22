import data.BackupServersHolder;
import evolution.Evolution;
import model.*;
import org.joda.time.DateTime;
import org.joda.time.chrono.GregorianChronology;
import scheduler.PeriodicScheduler;
import scheduler.PriorityScheduler;
import state.BackupServerState;
import state.DataTransferSystem;
import state.FailureFactory;
import scheduler.Scheduler;

/**
 * Created by ksenia on 13.04.18.
 */
public class Main {
    public static void main(String[] args) {
        Evolution evolution = new Evolution();
        System.out.println(evolution.evolve().getMaxFlowConst());
    }

 /*   private static DataTransferSystem getSimpleDTS() {
        BackupServerState backupServerState = initialState();
        Scheduler scheduler = new PeriodicScheduler(BACKUP_SERVER.getComputers());
        return getDTS(scheduler, backupServerState);
    }

    private static DataTransferSystem getPriorityDTS() {
        BackupServerState backupServerState = initialState();
        Scheduler scheduler = new PriorityScheduler(
                1.1, 1.1, 1.1, 1.1, backupServerState, BACKUP_SERVER.getComputers()
        );
        return getDTS(scheduler, backupServerState);
    }

    private static void printResults(DataTransferSystem dts, String shedulerName) {
        long damage = dts.countDamage();
        System.out.println(shedulerName + ": " + damage);
    }

    private static BackupServerState initialState() {
        return new BackupServerState(START_DATE, BACKUP_SERVER);
    }

    private static DataTransferSystem getDTS(
            Scheduler scheduler,
            BackupServerState initialState
    ) {
        return new DataTransferSystem(
                scheduler,
                COMPUTER_FAILURE_FACTORY,
                SUB_NETWORK_FAILURE_FACTORY,
                initialState,
                START_DATE,
                END_DATE);
    }*/
}