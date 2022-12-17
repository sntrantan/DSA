package com.fundamentals.dsa.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.GsonTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortTest {

    @Test
    void topologicalSortIndegreeAsArray() {
        // Create a list of nodes to represent the graph
        List<TopologicalSort.Node> graph = new ArrayList<>();
        // Create the nodes
        TopologicalSort.Node a = new TopologicalSort.Node(1);
        TopologicalSort.Node b = new TopologicalSort.Node(2);
        TopologicalSort.Node c = new TopologicalSort.Node(3);
        TopologicalSort.Node d = new TopologicalSort.Node(4);
        TopologicalSort.Node e = new TopologicalSort.Node(5);

        // Add the nodes to the graph
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);

        // Add edges to the graph
        a.children.add(d);
        b.children.add(a);
        c.children.add(a);
        c.children.add(b);
        d.children.add(e);

        /*
        In this example, we create a directed graph with 5 nodes and 5 edges. The graph looks like this:
            3 --> 1 --> 4 --> 5
            |     ^
            |     |
            ----> 2
  //        When we perform the topological sort, we get the following result: [3, 2, 1, 4, 5], which indicates that node 3 should be processed first, followed by node 2, then node 1, and so on.
         */

        // Perform the topological sort
        List<Integer> result = TopologicalSort.topologicalSortIndegreeAsArray(graph);
        List<Integer> expected = List.of(3,2,1,4,5);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void topologicalSortIndegreeAsArrayCycleExists() {
        // Create a list of nodes to represent the graph
        List<TopologicalSort.Node> graph = new ArrayList<>();
        // Create the nodes
        TopologicalSort.Node a = new TopologicalSort.Node(1);
        TopologicalSort.Node b = new TopologicalSort.Node(2);
        TopologicalSort.Node c = new TopologicalSort.Node(3);
        TopologicalSort.Node d = new TopologicalSort.Node(4);
        TopologicalSort.Node e = new TopologicalSort.Node(5);

        // Add the nodes to the graph
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);

        // Add edges to the graph
        a.children.add(d);
        b.children.add(a);
        c.children.add(a);
        c.children.add(b);
        d.children.add(e);
        e.children.add(d); // Add a cycle to the graph

        /*
        In this example, we create a directed graph with 5 nodes and 5 edges. The graph looks like this:
            3 --> 1 --> 4 --> 5
            |     ^      <----
            |     |
            ----> 2
         Cycle exists, so a topological ordering shouldn't be possible.
         */

        // Perform the topological sort
        List<Integer> result = TopologicalSort.topologicalSortIndegreeAsArray(graph);
        System.out.println("Result is: " + Arrays.toString(result.toArray()));
        Assertions.assertEquals(result.size(), 0);

    }
}