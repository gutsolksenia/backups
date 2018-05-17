package factory;

import org.joda.time.Period;

public class Constants {
    public static final int MAX_SUB_NETWORKS_COUNT = 100;
    public static final int MIN_SUB_NETWORKS_COUNT = 3;
    public static final int MAX_DATA_AMOUNT = 400000;
    public static final int MIN_DATA_AMOUNT = 200000;
    public static final Period EVENING = new Period(18, 0, 0, 0);
    public static final Period MORNING = new Period(8, 0, 0, 0);
    public static final int MAX_SUB_NETWORK_SIZE = 300;
    public static final int MIN_BACKUP_SPEED = 10;
    public static final int MAX_BACKUP_SPEED = 100;

    private Constants() {}
}
