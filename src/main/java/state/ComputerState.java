package state;

import model.Computer;
import org.joda.time.DateTime;

public class ComputerState {
    private int dataToBackup;
    private DateTime lastCompleteBackup;


    public ComputerState(Computer computer, DateTime startTime) {
        this.dataToBackup = dataToBackup;
        this.lastCompleteBackup = lastCompleteBackup;
    }

    public int getDataToBackup() {
        return dataToBackup;
    }

    public DateTime getLastCompleteBackup() {
        return lastCompleteBackup;
    }

    public void setDataToBackup(int dataToBackup) {
        this.dataToBackup = dataToBackup;
    }

    public void setLastCompleteBackup(DateTime lastCompleteBackup) {
        this.lastCompleteBackup = lastCompleteBackup;
    }
}