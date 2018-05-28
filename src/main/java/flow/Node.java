package flow;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private List<Edge> edgesFrom = new ArrayList<>();

    public List<Edge> getEdgesFrom() {
        return edgesFrom;
    }

    public void addEdgeFrom(Edge edge) {
        this.edgesFrom.add(edge);
    }
}