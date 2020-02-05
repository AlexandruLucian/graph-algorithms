package com.udemy.depthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) {

		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		Vertex v4 = new Vertex("4");
		Vertex v5 = new Vertex("5");

		List<Vertex> vertexList = new ArrayList<>();

		v1.addNeighbour(v2);
		v1.addNeighbour(v3);
		v3.addNeighbour(v4);
		v4.addNeighbour(v5);

		vertexList.add(v1);
		vertexList.add(v2);
		vertexList.add(v3);
		vertexList.add(v4);
		vertexList.add(v5);

		DFS dfs = new DFS();

		dfs.depthFirstSearchAlgorithm(vertexList);

	}

}
