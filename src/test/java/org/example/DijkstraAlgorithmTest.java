package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class DijkstraAlgorithmTest {

    @Test
    public void test() {
        Graph graph = new Graph();
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        graph.addAllNodes(nodeA, nodeB, nodeC, nodeD, nodeE);
        graph.addEdge(nodeA, nodeB, 6);
        graph.addEdge(nodeA, nodeD, 1);
        graph.addEdge(nodeD, nodeB, 2);
        graph.addEdge(nodeD, nodeE, 1);
        graph.addEdge(nodeE, nodeB, 2);
        graph.addEdge(nodeE, nodeC, 5);
        graph.addEdge(nodeB, nodeC, 5);

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        dijkstraAlgorithm.setGraph(graph);
        dijkstraAlgorithm.setStartNode(nodeA);
        Map<Node, Path> solutions = dijkstraAlgorithm.findShortestPaths();

        Map<Node, Path> expectedSolutions = new HashMap<>();
        expectedSolutions.put(nodeA, Path.empty());
        expectedSolutions.put(nodeB, new Path(nodeA, nodeD));
        expectedSolutions.put(nodeD, new Path(nodeA));
        expectedSolutions.put(nodeE, new Path(nodeA, nodeD));
        expectedSolutions.put(nodeC, new Path(nodeA ,nodeD, nodeE));

        System.out.println(solutions);

        assertEquals(expectedSolutions, solutions);
    }
}
