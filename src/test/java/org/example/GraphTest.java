package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void addAllNodes_GivenNodes_AddsThem() {
        Graph graph = new Graph();
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        graph.addAllNodes(nodeA, nodeB, nodeC, nodeD);

        List<Node> nodes = Arrays.asList(
                new Node(nodeA.getId(), "A"),
                new Node(nodeB.getId(), "B"),
                new Node(nodeC.getId(), "C"),
                new Node(nodeD.getId(), "D"));
        assertEquals(nodes, graph.getNodes());
    }

    @Test
    public void addNode_GivenNode_AddsIt() {
        Graph graph = new Graph();
        Node nodeA = new Node("A");
        graph.addNode(nodeA);

        List<Node> nodes = Collections.singletonList(
                new Node(nodeA.getId(), "A"));
        assertEquals(nodes, graph.getNodes());
    }

    @Test
    public void addNode_GivenDuplicateNode_ShouldNotAddIt() {
        Graph graph = new Graph();
        Node nodeA = new Node("A");
        Node nodeA1 = new Node("A");
        graph.addNode(nodeA);
        graph.addNode(nodeA1);

        List<Node> nodes = Collections.singletonList(
                new Node(nodeA.getId(), "A"));
        assertEquals(nodes, graph.getNodes());
    }

    @Test
    public void addEdge_GivenFromToAndWeight_AddsThemAsEdge() {
        Graph graph = new Graph();
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        graph.addAllNodes(nodeA, nodeB, nodeC);
        graph.addEdge(nodeA, nodeB, 1);
        graph.addEdge(nodeA, nodeC, 3);

        assertEquals(Arrays.asList(nodeB, nodeC), graph.getNodeNeighbours(nodeA));
        assertEquals(1, graph.getWeight(nodeA, nodeB));
        assertEquals(3, graph.getWeight(nodeA, nodeC));
    }
}
