package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraAlgorithm {

    private int totalNumberOfNodes;
    private int[] smallestWeightFromStart;
    private Node[] previousNodes;
    private List<Node> visitedNodes;
    private List<Node> unvisitedNodes;

    private Graph graph;
    private Node startNode;

    public DijkstraAlgorithm(Graph graph, Node startNode) {
        this.graph = graph;
        this.startNode = startNode;
    }

    public DijkstraAlgorithm() {

    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Map<Node, Path> findShortestPaths() {
        if (graph == null || startNode == null) {
            throw new IllegalArgumentException("Graph and start node should be set");
        }

        totalNumberOfNodes = graph.getNodes().size();
        smallestWeightFromStart = new int[totalNumberOfNodes];
        previousNodes = new Node[totalNumberOfNodes];
        visitedNodes = new ArrayList<>();
        unvisitedNodes = new ArrayList<>(graph.getNodes());

        initAlgorithm();

        while (!unvisitedNodes.isEmpty()) {
            Node nodeToVisit = selectNextNode();
            if (nodeToVisit != null) {
                List<Node> neighbours = graph.getNodeNeighbours(nodeToVisit);
                for (Node neighbour: neighbours) {
                    int weightToNeighbour = smallestWeightFromStart[nodeToVisit.getId()]
                            + graph.getWeight(nodeToVisit, neighbour);
                    if (weightToNeighbour < smallestWeightFromStart[neighbour.getId()]) {
                        smallestWeightFromStart[neighbour.getId()] = weightToNeighbour;
                        previousNodes[neighbour.getId()] = nodeToVisit;
                    }
                }

                visitedNodes.add(nodeToVisit);
                unvisitedNodes.remove(nodeToVisit);
            }
        }

        Map<Node, Path> shortestPaths = new HashMap<>();
        for (Node node: graph.getNodes()) {
            Path path = getPathTo(node);
            shortestPaths.put(node, path);
        }

        return shortestPaths;
    }

    private Node selectNextNode() {
        int min = Integer.MAX_VALUE;
        Node nodeToVisit = null;
        for (Node unvisitedNode: unvisitedNodes) {
            int weight = smallestWeightFromStart[unvisitedNode.getId()];
            if (weight < min) {
                min = weight;
                nodeToVisit = unvisitedNode;
            }
        }

        return nodeToVisit;
    }

    private void initAlgorithm() {
        for (int i = 0; i < totalNumberOfNodes; i++) {
            smallestWeightFromStart[i] = Integer.MAX_VALUE;
        }
        smallestWeightFromStart[startNode.getId()] = 0;
    }

    private Path getPathTo(Node node) {
        if (node.equals(startNode)) {
            return Path.empty();
        }

        Path path = new Path();
        do {
            node = previousNodes[node.getId()];
            path.prependNode(node);
        } while (!node.equals(startNode));

        return path;
    }
}
