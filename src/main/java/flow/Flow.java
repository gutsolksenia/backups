package flow;

import java.util.*;

public class Flow {
    private final Node start = new Node();
    private final Node end = new Node();
    private Set<Node> visited;

    public void maximize(List<Node> vertices) {
        vertices.forEach(v ->
                addPositiveEdge(start, v, Integer.MAX_VALUE - 1)
        );
        List<Edge> augmentingPath = getAugmentingPath();
        while (augmentingPath != null) {
            updateFlow(augmentingPath);
            augmentingPath = getAugmentingPath();
        }
    }

    public long getValue() {
        return start.getEdgesFrom().stream()
                .mapToLong(e -> e.getFlow() - e.getReversed().getFlow())
                .sum();
    }

    private void updateFlow(List<Edge> augmentingPath) {
        int flow = augmentingPath.stream()
                .mapToInt(e -> e.getCapacity())
                .min().orElse(0);
        augmentingPath.forEach(e -> e.addFlow(flow));
    }

    private List<Edge> getAugmentingPath() {
        visited = new HashSet<>();
        List<Edge> path = getReversedPath(start);
        Collections.reverse(path);
        path.remove(path.size() -1); //remove start
        return path;
    }

    private List<Edge> getReversedPath(Node from) {
        if (from.equals(end)) {
            return new ArrayList<>();
        }
        if (visited.contains(from)) {
            return null;
        }
        visited.add(from);
        List<Edge> edges = from.getEdgesFrom();
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            if (e.isFull()) {
                continue;
            }
            if (e.isFull()) {
                throw new  RuntimeException();
            }
            List<Edge> path = getReversedPath(e.getTo());
            if (path != null) {
                path.add(e);
                return path;
            }
        }
        return null;
    }

    private void addPositiveEdge(Node from, Node to, int maxFlow) {
        Edge edge = new Edge(from, to, maxFlow);
        Edge reversed = new Edge(to, from, maxFlow);
        edge.setReversed(reversed);
        reversed.setReversed(edge);
    }

    private int getFlow(Node node) {
        return start.getEdgesFrom().stream()
                .filter(e -> e.getTo().equals(node))
                .mapToInt(e -> e.getFlow() - e.getReversed().getFlow())
                .findAny().orElse(0);

    }
}