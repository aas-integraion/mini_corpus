

import java.util.LinkedList;
import java.util.Queue;

public class IntBfs02 {

	public static void main(String[] args) {
		int dimension = Integer.parseInt(args[0]);
		int[][] adjMatrix = new int[dimension][dimension];

		IntBfs02 bfs = new IntBfs02();
		bfs.bfs(adjMatrix, 0);
	}
	
	/*
	 * Taken from
	 * http://www.sanfoundry.com/java-program-traverse-graph-using-bfs/
	 */
	public void bfs(int adjacency_matrix[][], int source) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int number_of_nodes = adjacency_matrix[source].length - 1;

		int[] visited = new int[number_of_nodes + 1];
		int i, element;

		visited[source] = 1;
		queue.add(source);

		while (!queue.isEmpty()) {
			element = queue.remove();
			i = element;
			System.out.print(i + "\t");
			while (i <= number_of_nodes) {
				if (adjacency_matrix[element][i] == 1 && visited[i] == 0) {
					queue.add(i);
					visited[i] = 1;
				}
				i++;
			}
		}
	}

}
