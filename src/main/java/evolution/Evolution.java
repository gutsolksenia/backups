package evolution;

import data.BackupServersHolder;
import model.BackupServer;
import model.Computer;
import model.SubNetwork;
import org.joda.time.DateTime;
import org.joda.time.chrono.GregorianChronology;
import scheduler.PriorityScheduler;
import scheduler.Scheduler;
import state.BackupServerState;
import state.DataTransferSystem;
import state.FailureFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Evolution {
    private static final int GENERATIONS_COUNT = 10;
    private static final int INDIVIDUALS_COUNT = 10;
    private static final DateTime START_DATE = new DateTime(2018, 1, 1, 0, 0, 0, GregorianChronology.getInstance());
    private static final DateTime END_DATE = new DateTime(2018, 1, 2, 0, 0, 0, GregorianChronology.getInstance());
    private static List<Params> individuals = new ArrayList<>(INDIVIDUALS_COUNT);

    static {
        //init individuals
        Random random = new Random();
        for (int i = 0; i < INDIVIDUALS_COUNT; i++) {
            Double lastBackupConst = random.nextDouble();
            Double priorityConst = random.nextDouble();
            Double dataConst = random.nextDouble();
            Double  maxFlowConst = random.nextDouble();
            individuals.add(new Params(
                    lastBackupConst,
                    priorityConst,
                    dataConst,
                    maxFlowConst
            ));
        }
    }

    public static Params evolve() {
        for (int i = 0; i < GENERATIONS_COUNT; i++) {
            multiply();
            individuals = individuals.stream()
                    .sorted(Comparator.comparingLong(p -> countDamage(p)))
                    .limit(INDIVIDUALS_COUNT)
                    .collect(Collectors.toList());
            System.out.println(i);
        }
        return individuals.get(0);
    }

    private static void multiply() {
        //TODO
    }

    private static long countDamage(Params params) {
        return BackupServersHolder.SERVERS.stream()
                .mapToLong(server -> countDamageForServer(params, server))
                .sum();
    }

    private static long countDamageForServer(Params params, BackupServer server) {
        BackupServerState state = new BackupServerState(START_DATE, server);
        FailureFactory<Computer> computerFailureFactory = new FailureFactory<>(0.0001, server.getComputers()); //TODO probability
        FailureFactory<SubNetwork> subNetworkFailureFactory = new FailureFactory<>(0.00001, server.getSubNetworks()); //TODO probabiliry//
        Scheduler scheduler = new PriorityScheduler(
                params.getLastBackupConst(),
                params.getPriorityConst(),
                params.getDataConst(),
                params.getMaxFlowConst(),
                state
        );
        return new DataTransferSystem(
                scheduler,
                computerFailureFactory,
                subNetworkFailureFactory,
                state,
                START_DATE,
                END_DATE
        ).countDamage();
    }
}