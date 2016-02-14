

import java.util.LinkedList;
import java.util.Queue;

public class IntBfs01 {

	public static void main(String[] args) {
		int dimension = Integer.parseInt(args[0]);
		int[][] adjMatrix = new int[dimension][dimension];
		boolean[] visited = new boolean[dimension];

		IntBfs01 bfs = new IntBfs01();
		bfs.bfs(adjMatrix, 0, dimension, visited);
	}

	/*
	 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Graph/Progs/bfs/
	 * Graph1.java
	 */
	public void bfs(int[][] adjMatrix, int rootNode, int NNodes, boolean[] visited) {

		Queue<Integer> q = new LinkedList<Integer>();

		q.add(rootNode);
		visited[rootNode] = true;

		printNode(rootNode);

		while (!q.isEmpty()) {
			int n, child;

			n = (q.peek()).intValue();

			child = getUnvisitedChildNode(n, adjMatrix, NNodes, visited); // Returns
																			// -1
																			// if
																			// no
																			// unvisited
			// niode left

			if (child != -1) { // Found an unvisted node

				visited[child] = true; // Mark as visited

				printNode(child);

				q.add(child); // Add to queue
			} else {
				q.remove(); // Process next node
			}
		}
	}

	int getUnvisitedChildNode(int n, int[][] adjMatrix, int NNodes, boolean[] visited) {
		int j;
		for (j = 0; j < NNodes; j++) {
			if (adjMatrix[n][j] > 0) {
				if (!visited[j])
					return (j);
			}
		}
		return (-1);
	}

	void printNode(int node) {

	}
}
