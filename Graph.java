package com.paypal.risk.riskonboardingmsgd.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

	int v;
	boolean marked[];
	List<Integer> adj[];
	private int E; // Number of edges

	public Graph(int val) {
		this.v = val;
		marked = new boolean[val];
		adj = (ArrayList<Integer>[]) new ArrayList[val];

		for (int i = 0; i < val; i++) {
			adj[i] = new ArrayList<Integer>();
		}

	}

	private void addEdge(int v, int w) {
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	private List<Integer> adjacent(int v) {
		// System.out.println("Adjacent of " + v + " is " + adj[v].toString());
		return adj[v];
	}

	private void dfs(Graph G, int v) {
		System.out.println("Vertex " + v);
		marked[v] = true;
		for (int i : G.adjacent(v)) {
			if (!marked[i]) {
				dfs(G, i);
			}
		}
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);

		while (!q.isEmpty()) {
			int v = q.remove();
			for (int w : adjacent(v)) {
				
				if (!marked[w]) {
					marked[w] = true;
					System.out.println("Visiting " + w);
					q.add(w);
				}
				
			}
		}

	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(v + " vertices, " + E + " edges " + "\n");
		for (int i = 0; i < v; i++) {
			s.append(i + ": ");
			for (int w : adj[i]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {

		Graph graph = new Graph(10);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 4);
		graph.addEdge(0, 5);

		graph.addEdge(5, 7);
		graph.addEdge(3, 7);
		graph.addEdge(6, 7);

		System.out.println("GRAPH " + graph.toString());

		//graph.dfs(graph, 0);
		
		graph.bfs(graph, 0);

	}

}
