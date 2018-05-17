package state;

import model.Computer;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BackupServerState {
    private static final int TIME_STEP_IN_MINUTES = 1;

    private DateTime time;
    private final DateTime startDate;
    private final Map<Computer, ComputerState>  computersStates;

    public BackupServerState(DateTime time, List<Computer> computers) {
        this.startDate = time;
        this.time = time;
        computersStates = computers.stream()
                .collect(Collectors.toMap(
                      Function.identity(),
                      computer -> new ComputerState(computer, startDate)
                ));
    }

    public void updateTime() {
        time.plusMinutes(TIME_STEP_IN_MINUTES);
    }

    public DateTime getNow() {
        return time;
    }

    public void doBackup(List<Computer> computers) {
        computers.forEach(
                computer -> updateState(computer, getBackupSpeed(computer))
        );
    }

    public DateTime getLastBackupDate(Computer computer) {
        return computersStates.get(computer).getLastCompleteBackup();
    }

    private void updateState(Computer computer, int backupedDataAmount) {
        ComputerState state = computersStates.get(computer);
        int dataToBackup = state.getDataToBackup() - backupedDataAmount;
        if (dataToBackup <= 0) {
            state.setDataToBackup(computer.getStoredData());
            state.setLastCompleteBackup(time);
            return;
        }
        state.setDataToBackup(dataToBackup);
    }

    private int getBackupSpeed(Computer computer) {
        //TODO
        return 1;
    }

}