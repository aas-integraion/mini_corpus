

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NodeBfs02 {

	enum State {
		Visited, Unvisited;
	}

	static class Node {
		public State state = null;
		public List<Node> vertices = new LinkedList<Node>();
		public int value;

	}
	
	public static void main(String[] args) {
		Node root = createTree(Integer.parseInt(args[0]));
		NodeBfs02 bfs = new NodeBfs02();
		bfs.bfs(root, 3);
	}
	
	private static Node createTree(int depth) {
		Node n = new Node();
		n.value = depth;
		n.state = State.Unvisited;
		if (depth>0) {
			n.vertices.add(createTree(depth-1));
			n.vertices.add(createTree(depth-1));
		}
		return n;
	}

	/*
	 * Adopted implementation from:
	 * http://codefordummies.blogspot.com/2013/11/bfs-breadth-first-search-for.
	 * html
	 */
	public Node bfs(Node root, int element) {
		// #1: Initialize queue (q)
		Queue<Node> q = new ConcurrentLinkedQueue<Node>(); // some queue
		// implementation
		// #2: Push root node to queue
		q.add(root);

		// #3: While queue not empty
		while (!q.isEmpty()) {

			// #:4 Dequeue n
			Node n = q.poll();
			// visit this node
			n.state = State.Visited;

			// #5: If n == required_node, return n;
			if (n.value == element)
				return n;

			// #5: foreach vertices v of n
			for (Node v : n.vertices) {
				// #6: if v is visited, continue
				if (v.state == State.Visited)
					continue;
				// #7: else enque v
				q.add(v);
			}
		}
		// #8: return null;
		return null; // cannot find element
	}
}
