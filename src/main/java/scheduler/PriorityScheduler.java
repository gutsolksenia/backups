package scheduler;

import model.Computer;
import state.BackupServerState;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriorityScheduler implements Scheduler {
    private final Double lastBackupConst;
    private final Double priorityConst;
    private final Double dataConst;
    private final Double  maxFlowConst;
    private final BackupServerState serverState;


    public PriorityScheduler(Double lastBacklupConst,
                             Double priorityConst,
                             Double dataConst,
                             Double maxFlowConst,
                             BackupServerState serverState) {
        this.lastBackupConst = lastBacklupConst;
        this.priorityConst = priorityConst;
        this.dataConst = dataConst;
        this.maxFlowConst = maxFlowConst;
        this.serverState = serverState;
    }


    @Override
    public List<Computer> getNext() {
        Computer computer = serverState.getComputers().stream()
        .sorted(Comparator.comparingLong(
                c -> getPriority(c)
        )).findFirst().get();
        return Collections.singletonList(computer);
    }

    private long getPriority(Computer computer) {
        return Math.round(
                lastBackupConst * serverState.getLastBackupDate(computer).getMillis() +
                priorityConst * computer.getImportance() +
                dataConst * computer.getStoredData() +
                maxFlowConst * serverState.getBackupSpeed(computer)
        );
    }
}
