package evolution;

public class Params {
    private final Double lastBackupConst;
    private final Double priorityConst;
    private final Double dataConst;
    private final Double  maxFlowConst;

    public Params(
            Double lastBackupConst,
            Double priorityConst,
            Double dataConst,
            Double maxFlowConst
    ) {
        this.lastBackupConst = lastBackupConst;
        this.priorityConst = priorityConst;
        this.dataConst = dataConst;
        this.maxFlowConst = maxFlowConst;
    }

    public Double getLastBackupConst() {
        return lastBackupConst;
    }

    public Double getPriorityConst() {
        return priorityConst;
    }

    public Double getDataConst() {
        return dataConst;
    }

    public Double getMaxFlowConst() {
        return maxFlowConst;
    }
}
