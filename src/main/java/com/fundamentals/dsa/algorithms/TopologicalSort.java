package com.fundamentals.dsa.algorithms;

import java.util.*;

/*
    Topological Sort - ordering of vertices or nodes such if there is an edge between (u,v) then u should come before v in topological sorting.
                     - top. sort is possible only for Directed Acyclic Graphs (DAG)
    Sources:
        https://leetcode.com/problems/course-schedule-ii/solutions/190393/topological-sort-template-general-approach/
        https://leetcode.com/problems/course-schedule-ii/solutions/59472/java-13ms-iterative-dfs-solution-with-explanation/
        https://www.youtube.com/watch?v=cIBFEhD77b4
        https://www.youtube.com/watch?v=73sneFXuTEg
        How to populate adjacency matrix (Step 2)     https://www.youtube.com/watch?v=4R7chuhzq7k
        Recursive Topological Sort Java Example: https://java2blog.com/topological-sort-java/
*/

public class TopologicalSort {

    // Represents a node in a directed graph
    static class Node {
        // The node's value
        int value;
        // The list of nodes that this node points to
        List<Node> children;

        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }

    // Performs a topological sort on the given directed graph
    public static List<Integer> topologicalSort(List<Node> graph) {
        // Initialize the stack to hold nodes that have no dependencies
        Queue<Node> queue = new LinkedList<>();
        // Initialize a map to store the number of dependencies for each node
        Map<Node, Integer> dependencies = new HashMap<>();
        // Initialize the dependencies for each node
        for (Node node : graph) {
            node.children.forEach(n -> {
                int amount = dependencies.getOrDefault(n, 0);
                dependencies.put(n, ++amount);
            });
        }
        // Iterate through the dependencies map & add nodes with no dependencies.
        for (Node node : graph) {
            if (dependencies.getOrDefault(node, 0) == 0)
                queue.add(node);
        }

        // Initialize the result list
        List<Integer> result = new ArrayList<>();

        // Remove node from queue, add to res, dec. child dependencies by 1, and process children
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            result.add(node.value);

            List<Node> children = node.children;
            for (int i = 0; i < children.size(); i++) {
                // reduce indegree of children
                // add to queue if indegree = 0
                Node child = children.get(i);
                int indegree = dependencies.get(child);
                indegree--;
                dependencies.put(child, indegree);
                if (indegree == 0) {
                    queue.add(child);
                }
            }
        }
        // Return the result list
        return result;
    }

    // Performs a topological sort on the given directed graph via indegree as an array. Also checks that the sorting was possible (that no cycles existed)
    public static List<Integer> topologicalSortIndegreeAsArray(List<Node> graph) {
        // Initialize the stack to hold nodes that have no dependencies
        Queue<Node> queue = new LinkedList<>();
        // Initialize a map to store the number of dependencies for each node
        int[] indegree = new int[graph.size()+1];

        // Initialize the dependencies for each node
        for (Node node : graph) {
            node.children.forEach(child -> ++indegree[child.value]);
        }
        // Iterate through the dependencies map & add nodes with no dependencies.
        for (Node node : graph) {
            if (indegree[node.value] == 0) queue.add(node);
        }

        // Initialize the result list
        List<Integer> result = new ArrayList<>();
        // Remove node from queue, add to res, dec. child dependencies by 1, and process children
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            result.add(node.value);

            List<Node> children = node.children;
            for (int i = 0; i < children.size(); i++) {
                // reduce indegree of children
                // add to queue if indegree = 0
                Node child = children.get(i);
                if (--indegree[child.value] == 0) {
                    queue.add(child);
                }
            }
        }
        // Return the result list
        return result.size() == graph.size() ? result : new ArrayList<>(); // If a cycle exists, then not all items will have been processed --> return an empty list
    }

    public static void main(String[] args) {
        // Create a list of nodes to represent the graph
        List<Node> graph = new ArrayList<>();
        // Create the nodes
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);

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
         */

        // Perform the topological sort
        List<Integer> result = TopologicalSort.topologicalSortIndegreeAsArray(graph);
        // Print the result
        System.out.println(Arrays.toString(result.toArray())); // prints [3, 2, 1, 4, 5]
//        When we perform the topological sort, we get the following result: [3, 2, 1, 4, 5], which indicates that node 3 should be processed first, followed by node 2, then node 1, and so on.
    }
}
