package com.udemy.astarSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarAlgorithm {

	// all nodes on the grid
	private Node[][] searchSpace;

	private Node startNode;

	private Node finalNode;

	// the set of nodes that are already evaluated
	private List<Node> closedSet;

	// not yet evaluated
	private Queue<Node> openSet;

	public AStarAlgorithm() {
		this.searchSpace = new Node[Constants.NUM_ROWS][Constants.NUM_COLS];
		this.openSet = new PriorityQueue<>(new NodeComparator());
		this.closedSet = new ArrayList<Node>();
		initializeSearchSpace();
	}

	private void initializeSearchSpace() {

		// initialize all the nodes on theh grid
		for (int rowIndex = 0; rowIndex < Constants.NUM_ROWS; rowIndex++) {
			for (int colIndex = 0; colIndex < Constants.NUM_COLS; colIndex++) {
				Node node = new Node(rowIndex, colIndex);
				this.searchSpace[rowIndex][colIndex] = node;
			}
		}

		// set obstacle
		this.searchSpace[1][7].setBlock(true);
		this.searchSpace[2][3].setBlock(true);
		this.searchSpace[2][4].setBlock(true);
		this.searchSpace[2][5].setBlock(true);
		this.searchSpace[2][6].setBlock(true);
		this.searchSpace[2][7].setBlock(true);

		//set start and final node
		this.startNode = this.searchSpace[3][3];
		this.finalNode = this.searchSpace[1][6];

	}

	public void search() {

		// start with the start node
		startNode.setH(manhattanHeuristic(startNode, finalNode));
		openSet.add(startNode);

		// the algorithm terminates when there is no item left in the open set
		while (!openSet.isEmpty()) {

			// poll: returns the node with the smallest f=g+h value
			Node currentNode = openSet.poll();
			System.out.println(currentNode + " Predecessor is : " + currentNode.getPredecessor());

			// if we find the final node we are done
			if (currentNode.equals(finalNode))
				return;

			// we have to update the sets
			openSet.remove(currentNode);
			closedSet.add(currentNode);

			// visit all the neighbours of the currentNode
			for (Node neighbour : getAllNeighbours(currentNode)) {

				// we already consider that node so continue
				if (closedSet.contains(neighbour))
					continue;
				if (!openSet.contains(neighbour))
					openSet.add(neighbour);

				// set the predecessor to be bable to track tghe shortest path
				neighbour.setPredecessor(currentNode);

			}
		}
	}

	public List<Node> getAllNeighbours(Node node) {
		// store the neighbours in a list
		// NOTE: in this implementation every node can have 4 neighbours at most(right,
		// left, above, below)
		List<Node> neighbours = new ArrayList<>();

		int row = node.getRowIndex();
		int col = node.getColIndex();

		// block above
		if (row - 1 >= 0 && !this.searchSpace[row - 1][col].isBlock()) {
			searchSpace[row - 1][col].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
			searchSpace[row - 1][col].setH(manhattanHeuristic(searchSpace[row - 1][col], finalNode));
			neighbours.add(this.searchSpace[row - 1][col]);
		}

		// block below
		if (row + 1 < Constants.NUM_ROWS && !this.searchSpace[row + 1][col].isBlock()) {
			searchSpace[row + 1][col].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
			searchSpace[row + 1][col].setH(manhattanHeuristic(searchSpace[row + 1][col], finalNode));
			neighbours.add(this.searchSpace[row + 1][col]);
		}

		// block right
		if (col + 1 < Constants.NUM_COLS && !this.searchSpace[row][col + 1].isBlock()) {
			searchSpace[row][col + 1].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
			searchSpace[row][col + 1].setH(manhattanHeuristic(searchSpace[row][col + 1], finalNode));
			neighbours.add(this.searchSpace[row][col + 1]);
		}

		// block left
		if (col - 1 >= 0 && !this.searchSpace[row][col - 1].isBlock()) {
			searchSpace[row][col - 1].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
			searchSpace[row][col - 1].setH(manhattanHeuristic(searchSpace[row][col - 1], finalNode));
			neighbours.add(this.searchSpace[row][col - 1]);
		}
		return neighbours;
	}

	// manhattan distance
	public int manhattanHeuristic(Node node1, Node node2) {
		return (Math.abs(node1.getRowIndex() - node2.getRowIndex())
				+ Math.abs(node1.getColIndex() - node2.getColIndex())) * 10;
	}

	public void showPath() {

		System.out.println("SHORTEST PATH WITH A* SEARCH: ");

		Node node = this.finalNode;

		while (node != null) {
			System.out.println(node);
			node = node.getPredecessor();
		}
	}
}
