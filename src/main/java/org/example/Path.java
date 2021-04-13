package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Path {

    private final List<Node> nodes;

    public Path(Node... nodes) {
        this.nodes = new ArrayList<>();
        this.nodes.addAll(Arrays.asList(nodes));
    }

    public static Path empty() {
        return new Path();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void prependNode(Node node) {
        nodes.add(0, node);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Path)) {
            return false;
        }

        Path path = (Path) obj;
        return this.nodes.equals(path.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < nodes.size(); i++) {
            stringBuilder.append(nodes.get(i));
            if (i < nodes.size() - 1) {
                stringBuilder.append(" -> ");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
