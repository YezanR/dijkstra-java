package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private static int ID_SEQUENCE = 0;

    private final List<Node> nodes;

    private final int[][] weights;

    public Graph() {
        this.nodes = new ArrayList<>();
        weights = new int[10000][10000];
    }

    public static Graph createFromFile(String fileName) throws IOException {
        InputStream inputStream = Graph.class.getResourceAsStream("/" + fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // read first line and ignore it
        bufferedReader.readLine();

        Graph graph = new Graph();
        for (String line; (line = bufferedReader.readLine()) != null; ) {
            String[] lineColumns = line.split(" ");
            Node fromNode = new Node(lineColumns[0]);
            Node toNode = new Node(lineColumns[1]);
            int weight = Integer.parseInt(lineColumns[2]);
            graph.addNode(fromNode);
            graph.addNode(toNode);
            graph.addEdge(fromNode, toNode, weight);
        }

        return graph;
    }

    public void addAllNodes(Node... nodes) {
        for (Node node: nodes) {
            addNode(node);
        }
    }

    public void addNode(Node node) {
        if (node == null) {
            return;
        }

        Node existingNode = findNodeByName(node.getName());
        if (existingNode == null) {
            node.setId(ID_SEQUENCE++);
            this.nodes.add(node);
        }
        else {
            node.setId(existingNode.getId());
        }
    }

    public void addEdge(Node from, Node to, int weight) {
        weights[from.getId()][to.getId()] = weight;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public int getWeight(Node from, Node to) {
        return weights[from.getId()][to.getId()];
    }

    public List<Node> getNodeNeighbours(Node node) {
        List<Node> neighbourNodes = new ArrayList<>();
        int[] row = weights[node.getId()];
        for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
            if (weights[node.getId()][columnIndex] > 0) {
                neighbourNodes.add(findNodeById(columnIndex));
            }
        }

        return neighbourNodes;
    }

    public Node findNodeById(int id) {
        for (Node node: nodes) {
            if (node.getId() == id) {
                return node;
            }
        }

        return null;
    }

    public Node findNodeByName(String name) {
        for (Node node: nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }
}
