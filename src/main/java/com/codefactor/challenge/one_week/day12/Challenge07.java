package com.codefactor.challenge.one_week.day12;

import java.util.*;

/**
 * Challenge 07 - Graph Representation and BFS/DFS
 *
 * Implement a graph with adjacency list and perform BFS and DFS traversal.
 *
 * BFS: uses Queue - level-order, shortest path in unweighted graphs
 * DFS: uses Stack/recursion - explores as deep as possible first
 */
public class Challenge07 {

    static class Graph {
        private final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        public void addEdge(int from, int to) {
            adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            adjacencyList.computeIfAbsent(to, k -> new ArrayList<>()).add(from); // undirected
        }

        /** BFS traversal */
        public List<Integer> bfs(int start) {
            List<Integer> visited = new ArrayList<>();
            Set<Integer> seen = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            seen.add(start);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                visited.add(node);
                for (int neighbor : adjacencyList.getOrDefault(node, Collections.emptyList())) {
                    if (seen.add(neighbor)) {
                        queue.offer(neighbor);
                    }
                }
            }
            return visited;
        }

        /** DFS traversal (iterative with stack) */
        public List<Integer> dfs(int start) {
            List<Integer> visited = new ArrayList<>();
            Set<Integer> seen = new HashSet<>();
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(start);
            while (!stack.isEmpty()) {
                int node = stack.pop();
                if (seen.add(node)) {
                    visited.add(node);
                    List<Integer> neighbors = adjacencyList.getOrDefault(node, Collections.emptyList());
                    for (int i = neighbors.size() - 1; i >= 0; i--) {
                        if (!seen.contains(neighbors.get(i))) {
                            stack.push(neighbors.get(i));
                        }
                    }
                }
            }
            return visited;
        }

        /** DFS recursive */
        public List<Integer> dfsRecursive(int start) {
            List<Integer> visited = new ArrayList<>();
            dfsHelper(start, new HashSet<>(), visited);
            return visited;
        }

        private void dfsHelper(int node, Set<Integer> seen, List<Integer> visited) {
            seen.add(node);
            visited.add(node);
            for (int neighbor : adjacencyList.getOrDefault(node, Collections.emptyList())) {
                if (!seen.contains(neighbor)) {
                    dfsHelper(neighbor, seen, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);

        System.out.println("BFS from 0:           " + graph.bfs(0));
        System.out.println("DFS from 0 (iter):    " + graph.dfs(0));
        System.out.println("DFS from 0 (recurse): " + graph.dfsRecursive(0));
    }
}
