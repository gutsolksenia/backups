package state;

import model.BackupServer;
import model.Channel;
import model.Computer;
import org.joda.time.DateTime;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BackupServerState {
    private static final int TIME_STEP_IN_MINUTES = 100;

    private DateTime time;
    private final DateTime startDate;
    private final Map<Computer, ComputerState>  computersStates;
    private final Flow flow;

    public BackupServerState(DateTime time, BackupServer backupServer) {
        this.startDate = time;
        this.time = time;
        this.flow = new Flow(backupServer.getChannels());
        computersStates = backupServer.getComputers().stream()
                .collect(Collectors.toMap(
                      Function.identity(),
                      computer -> new ComputerState(computer, startDate)
                ));
    }

    public Collection<Computer> getComputers() {
        return computersStates.keySet();
    }

    public void updateTime() {
        time = time.plusMinutes(TIME_STEP_IN_MINUTES);
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
        DateTime date = computersStates.get(computer).getLastCompleteBackup();
        return date == null ? startDate : date; //TODO
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

    public int getBackupSpeed(Computer computer) {
        return flow.maxFlow(computer.getSubNetwork());
    }
}