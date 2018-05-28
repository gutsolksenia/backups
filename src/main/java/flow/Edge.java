package flow;

public class Edge {
    private final Node from;
    private final Node to;
    private final int capacity;
    private Edge reversed;
    private int flow = 0;

    public Edge(Node from, Node to, int maxFlow) {
        this.from = from;
        this.to = to;
        this.capacity = maxFlow;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public int getFlow() {
        return flow;
    }

    public void addFlow(int flow) {
        setFlow(flow + this.flow);
    }

    public void setFlow(int flow) {
        if (flow > capacity || flow < 0) {
            throw new IllegalArgumentException();
        }
        this.flow = flow;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return flow == capacity;
    }

    public Edge getReversed() {
        return reversed;
    }

    public void setReversed(Edge reversed) {
        this.reversed = reversed;
    }
}