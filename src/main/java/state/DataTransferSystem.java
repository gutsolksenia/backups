package state;

import model.Computer;
import model.SubNetwork;
import org.joda.time.DateTime;
import scheduler.Scheduler;

import java.util.Set;
import java.util.stream.Collectors;

public class DataTransferSystem {
    private final Scheduler scheduler;
    private final FailureFactory<Computer> computerFailureFactory;
    private final FailureFactory<SubNetwork> subNetworkFailureFactory;
    private final DateTime startDate;
    private final DateTime endDate;
    private BackupServerState backupServerState;
    private long damage = 0;

    public DataTransferSystem(Scheduler scheduler,
                              FailureFactory<Computer> computerFailureFactory,
                              FailureFactory<SubNetwork> subNetworkFailureFactory,
                              DateTime startDate,
                              DateTime endDate) {
        this.scheduler = scheduler;
        this.computerFailureFactory = computerFailureFactory;
        this.subNetworkFailureFactory = subNetworkFailureFactory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.backupServerState = new BackupServerState(startDate);
    }

    public long countDamage() {
        while (backupServerState.getNow().isBefore(endDate)) {
            step();
        }
        return damage;
    }

    private void step() {
        backupServerState.updateTime();
        updateDamage();
        backupServerState.doBackup(scheduler.getNext());
    }

    private void updateDamage() {
        Set<Computer> failed = subNetworkFailureFactory.getNext().stream()
                .flatMap(network -> network.getComputers().stream())
                .collect(Collectors.toSet());
        failed.addAll(computerFailureFactory.getNext());
        damage += failed.stream()
                .mapToLong(this::getComputerFailureDamage)
                .sum();
    }

    private long getComputerFailureDamage(Computer computer) {
        DateTime lastBackup = backupServerState.getLastBackupDate(computer);
        long timeSinceLastBackupInMillis = backupServerState.getNow().getMillis() -
                lastBackup.getMillis();
        return Math.round(timeSinceLastBackupInMillis * computer.getImportance());
    }
}