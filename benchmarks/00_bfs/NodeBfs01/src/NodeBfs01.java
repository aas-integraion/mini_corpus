

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class NodeBfs01 {
	enum State {
		Visited, Unvisited;
	}

	static class Node {
		public State state = null;
		public List<Node> vertices;
		public int value;

	}
	public static void main(String[] args) {
		Node root = createTree(Integer.parseInt(args[0]));
		NodeBfs01 bfs = new NodeBfs01();
		bfs.bfs(root);
	}

	private static Node createTree(int depth) {
		Node n = new Node();
		n.value = depth;
		n.state = State.Unvisited;
		if (depth > 0) {
			n.vertices.add(createTree(depth - 1));
			n.vertices.add(createTree(depth - 1));
		}
		return n;
	}

	/*
	 * Taken from
	 * http://codereview.stackexchange.com/questions/48518/depth-first
	 * -search-breadth-first-search-implementation
	 */
	public void bfs(Node root) {
		// Since queue is a interface
		Queue<Node> queue = new LinkedList<Node>();

		if (root == null)
			return;

		root.state = State.Visited;
		// Adds to end of queue
		queue.add(root);

		while (!queue.isEmpty()) {
			// removes from front of queue
			Node r = queue.remove();
			// Visit child first before grandchild
			for (Node n : r.vertices) {
				if (n.state == State.Unvisited) {
					queue.add(n);
					n.state = State.Visited;
				}
			}
		}
	}
}
