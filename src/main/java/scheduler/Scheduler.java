package scheduler;

import model.Backup;
import model.BackupServer;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by ksenia on 13.04.18.
 */
public interface Scheduler {
    List<Backup> generateSchedule(DateTime startDate, DateTime endDate);
}
