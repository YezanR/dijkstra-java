package org.example;

import java.io.IOException;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Graph graph = Graph.createFromFile("graph.txt");

        Node startNode = graph.findNodeByName("A");
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph, startNode);
        Map<Node, Path> solutions = dijkstraAlgorithm.findShortestPaths();

        System.out.println("Shortest paths from " + startNode.getName()  + ": ");
        System.out.println(solutions);
    }
}
