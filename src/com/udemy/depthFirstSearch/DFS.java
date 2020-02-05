package com.udemy.depthFirstSearch;

import java.util.List;
import java.util.Stack;

public class DFS {

	private Stack<Vertex> stack;

	public DFS() {
		this.stack = new Stack<>();
	}

	public void depthFirstSearchAlgorithm(List<Vertex> vertexList) {

		for (Vertex v : vertexList) {
			if (!v.isVisited()) {
				v.setVisited(true);
				dfsRecursive(v);
			}
		}
	}

	private void dfsRecursive(Vertex rootVertex) {

		System.out.print(rootVertex + " ");

		for (Vertex v : rootVertex.getNeighbourList()) {
			if (!v.isVisited()) {
				v.setVisited(true);
				dfsRecursive(v);
			}
		}
	}

	private void dfsWithStack(Vertex rootVertex) {

		this.stack.add(rootVertex);
		rootVertex.setVisited(true);

		while (!stack.isEmpty()) {
			Vertex actualVertex = this.stack.pop();
			System.out.print(actualVertex + " ");

			for (Vertex v : actualVertex.getNeighbourList()) {
				if (!v.isVisited()) {
					v.setVisited(true);
					this.stack.push(v);
				}
			}
		}
	}
}
