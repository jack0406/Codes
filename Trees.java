/*
 * DA-IICT
 * Author: Jugal Kalal
 */
import java.util.*;
import java.io.*;

class Node{
	int data;
	Node lchild,rchild,right;
	public Node(int data) {
		this.data=data;
		lchild=null;
		rchild=null;
		right=null;
	}
}

public class BTree {

	private static Node root = null;

	public static void main(String[] args) {

		root = new Node(1);
		root.lchild = new Node(2);
		root.rchild = new Node(3);
		root.lchild.lchild = new Node(4);
		root.lchild.rchild = new Node(5);
		print_diag();

	}

	private static void inorder(Node node) {
		if (node == null)
			return;
		inorder(node.lchild);
		System.out.println(node.data);
		inorder(node.rchild);
	}

	private static void inorder_widout_rec(Node node) {
		Stack<Node> st = new Stack<Node>();

		while (node != null) {
			st.push(node);
			node = node.lchild;

		}

		while (!st.isEmpty()) {
			node = st.pop();

			System.out.print(node.data + " ");
			if (node.rchild != null) {
				node = node.rchild;

				while (node != null) {
					st.push(node);
					node = node.lchild;

				}

			}
		}
	}

	private static void preorder(Node node) {
		if (node == null)
			return;
		System.out.print(node.data + " ");
		preorder(node.lchild);
		preorder(node.rchild);
	}

	private static void pre_widout_rec(Node node) {
		Stack<Node> st = new Stack<Node>();

		while (node != null) {
			System.out.print(node.data + " ");
			st.push(node);
			node = node.lchild;

		}

		while (!st.isEmpty()) {
			node = st.pop();

			if (node.rchild != null) {
				node = node.rchild;

				while (node != null) {
					System.out.print(node.data + " ");
					st.push(node);
					node = node.lchild;

				}

			}
		}
	}

	private static void post(Node node) {
		if (root == null)
			return;
		post(node.lchild);
		post(node.rchild);
		System.out.print(node.data + " ");
	}

	private static void level(Node node) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(node);
		while (!q.isEmpty()) {
			Node top = q.poll();
			System.out.print(top.data + " ");
			if (top.lchild != null)
				q.add(top.lchild);
			if (top.rchild != null)
				q.add(top.rchild);
		}
	}

	static int width = 1;

	private static void lev_by_line(Node node) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(node);
		q.add(null);
		while (!q.isEmpty()) {
			Node top = q.poll();
			if (top == null) {
				System.out.println();
				width = Math.max(width, q.size());
				if (!q.isEmpty())
					q.add(null);
			} else {
				System.out.print(top.data + " ");
				if (top.lchild != null)
					q.add(top.lchild);
				if (top.rchild != null)
					q.add(top.rchild);

			}
		}
	}

	private static void lev_spiral(Node node) {

		Stack<Node> st1 = new Stack<Node>();
		Stack<Node> st2 = new Stack<Node>();
		st1.push(node);
		while (!st1.isEmpty() || !st2.isEmpty()) {

			while (!st1.isEmpty()) {
				Node top = st1.pop();
				System.out.print(top.data + " ");
				if (top.rchild != null) {
					st2.push(top.rchild);
				}
				if (top.lchild != null) {
					st2.push(top.lchild);
				}
			}
			while (!st2.isEmpty()) {
				Node top = st2.pop();
				System.out.print(top.data + " ");
				if (top.lchild != null) {
					st1.push(top.lchild);
				}
				if (top.rchild != null) {
					st1.push(top.rchild);
				}
			}
		}
	}

	private static int size(Node node) {
		if (node == null)
			return 0;
		return size(node.lchild) + size(node.rchild) + 1;
	}

	private static boolean identical(Node a, Node b) {
		if (a == null && b == null)
			return true;
		if (a != null && b != null) {
			return a.data == b.data && identical(a.lchild, b.lchild) && identical(a.rchild, b.rchild);
		}
		return false;
	}

	private static int height(Node node) {
		if (node == null)
			return 0;

		return Math.max(height(node.lchild), height(node.rchild)) + 1;
	}

	private static Node mirror(Node node) {
		if (node == null)
			return node;
		Node left = mirror(node.lchild);
		Node right = mirror(node.rchild);
		node.lchild = right;
		node.rchild = left;
		return node;
	}

	static int path[] = new int[1000];

	private static void all_root_to_leaf_paths(Node node, int ind) {
		path[ind] = node.data;
		if (node.lchild == null && node.rchild == null) {
			for (int i = 0; i <= ind; i++) {
				System.out.print(path[i] + " ");
			}
			System.out.println();
			return;
		}
		all_root_to_leaf_paths(node.lchild, ind + 1);
		all_root_to_leaf_paths(node.rchild, ind + 1);

	}

	static boolean ans = true;

	private static boolean child_sum_pro(Node node) {
		if (node.lchild == null && node.rchild == null) {
			return true;
		}
		if (node.lchild == null) {
			if (node.data != node.rchild.data) {
				return false;
			}
		}
		if (node.rchild == null) {
			if (node.data != node.lchild.data) {
				return false;

			}
		}

		return (node.data == (node.lchild.data + node.rchild.data)) && child_sum_pro(node.lchild)
				&& child_sum_pro(node.rchild);

	}

	private static int height_bal(Node node) {

		if (node == null)
			return 0;
		int lh = height(node.lchild);
		int rh = height(node.rchild);

		if (Math.abs(rh - lh) > 1) {
			ans = false;
		}
		return Math.max(lh, rh) + 1;
	}

	private static void doubleTree(Node node) {
		if (node == null)
			return;
		doubleTree(node.lchild);
		doubleTree(node.rchild);
		Node temp = node.lchild;
		node.lchild = new Node(node.data);
		node.lchild.lchild = temp;

	}

	private static void connect_same_lev(Node node) {
		Queue<Node> q1 = new LinkedList<Node>();
		Queue<Node> q2 = new LinkedList<Node>();
		q1.add(node);
		while (!q1.isEmpty() || !q2.isEmpty()) {
			Node start = q1.poll();
			if (start.lchild != null)
				q2.add(start.lchild);
			if (start.rchild != null)
				q2.add(start.rchild);
			while (!q1.isEmpty()) {

				Node top = q1.poll();

				start.right = top;
				start = top;
				if (top.lchild != null)
					q2.add(top.lchild);
				if (top.rchild != null)
					q2.add(top.rchild);

			}

			if (q2.isEmpty())
				continue;
			start = q2.poll();
			if (start.lchild != null)
				q1.add(start.lchild);
			if (start.rchild != null)
				q1.add(start.rchild);

			while (!q2.isEmpty()) {

				Node top = q2.poll();

				start.right = top;
				start = top;
				if (start.lchild != null)
					q1.add(start.lchild);
				if (start.rchild != null)
					q1.add(start.rchild);

			}

		}

	}

	private static boolean is_complete_tree(Node node) {

		Queue<Node> q = new LinkedList<Node>();
		q.add(node);
		boolean left_present = false;
		while (!q.isEmpty()) {

			Node top = q.poll();
			if (top.lchild != null) {

				if (left_present) {
					return false;
				}
				q.add(top.lchild);

			} else {
				left_present = true;
			}

			if (top.rchild != null) {
				if (left_present)
					return false;
				q.add(top.rchild);
			} else {
				left_present = true;
			}
		}
		return true;

	}

	private static void printBoundary(Node node) {

		System.out.println(node.data);
		printBoundaryleft(node.lchild);
		printLeaves(node);
		printBoundaryRight(node.rchild);
	}

	private static void printBoundaryleft(Node node) {

		if (node != null) {

			if (node.lchild != null) {
				System.out.println(node.data);
				printBoundaryleft(node.lchild);
			} else if (node.rchild != null) {
				System.out.println(node.data);
				printBoundaryleft(node.rchild);
			}
		}
	}

	private static void printLeaves(Node node) {
		if (node != null) {
			printLeaves(node.lchild);
			if (node.lchild == null && node.rchild == null) {
				System.out.println(node.data);
			}
			printLeaves(node.rchild);
		}
	}

	private static void printBoundaryRight(Node node) {
		if (node != null) {
			if (node.rchild != null) {
				printBoundaryRight(node.rchild);
				System.out.println(node.data);
			} else if (node.lchild != null) {
				printBoundary(node.lchild);
				System.out.println(node.data);
			}
		}
	}

	private static Node remove_nodes_in_path_sum_less_than_k(Node node, int sum) {
		if (node == null)
			return null;

		node.lchild = remove_nodes_in_path_sum_less_than_k(node.lchild, sum - node.data);
		node.rchild = remove_nodes_in_path_sum_less_than_k(node.rchild, sum - node.data);
		if (node.lchild == null && node.rchild == null) {
			if (sum > node.data) {

				return null;
			}
			return node;
		}
		return node;
	}

	private static void con_tree_to_child_sum_pro(Node node) {
		if (node == null)
			return;
		else if (node.lchild == null && node.rchild == null)
			return;
		else {
			con_tree_to_child_sum_pro(node.lchild);
			con_tree_to_child_sum_pro(node.rchild);

			int left_data = 0, right_data = 0, diff = 0;
			if (node.lchild != null) {
				left_data += node.lchild.data;
			}
			if (node.rchild != null) {
				right_data += node.rchild.data;
			}
			diff = left_data + right_data - node.data;
			if (diff > 0) {
				node.data += diff;
			}
			if (diff < 0) {
				increment(node, -diff);
			}
		}

	}

	private static void increment(Node node, int diff) {
		if (node.lchild != null) {
			node.lchild.data += diff;
			increment(node.lchild, diff);
		} else if (node.rchild != null) {
			node.rchild.data += diff;
			increment(node.rchild, diff);
		}

	}

	private static boolean isLeaf(Node node) {
		if (node.lchild == null && node.rchild == null) {
			return true;
		}
		return false;
	}

	private static boolean is_sum_tree(Node node) {
		if (node == null)
			return true;
		if (is_sum_tree(node.lchild) && is_sum_tree(node.rchild)) {

			int left_data = 0;
			if (node.lchild == null) {
				left_data = 0;
			} else if (isLeaf(node.lchild)) {
				left_data = node.lchild.data;
			} else {
				left_data = node.lchild.data * 2;
			}

			int right_data = 0;
			if (node.rchild == null) {
				right_data = 0;
			} else if (isLeaf(node.rchild)) {
				right_data = node.rchild.data;
			} else {
				right_data = node.rchild.data * 2;
			}

			return (node.data == left_data + right_data);
		}
		return false;
	}

	static HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

	private static void vertical_sum(Node node, int hd) {
		if (hm.containsKey(hd)) {
			hm.replace(hd, hm.get(hd) + node.data);
		} else {
			hm.put(hd, node.data);
		}
		vertical_sum(node.lchild, hd - 1);
		vertical_sum(node.rchild, hd + 1);
	}

	private static boolean areMirror(Node a, Node b) {
		if (a == null && b == null)
			return true;
		if (a == null || b == null)
			return false;
		return (a.data == b.data && areMirror(a.lchild, b.rchild) && areMirror(a.rchild, b.lchild));
	}

	static HashMap<Integer, ArrayList<Integer>> diag = new HashMap<Integer, ArrayList<Integer>>();

	private static void diagonal_traversal(Node node, int slope) {
		if (node == null)
			return;
		if (diag.containsKey(slope)) {
			ArrayList<Integer> temp = diag.get(slope);
			temp.add(node.data);
			diag.replace(slope, temp);
		} else {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(node.data);
			diag.put(slope, temp);
		}
		diagonal_traversal(node.lchild, slope + 1);
		diagonal_traversal(node.rchild, slope);
	}

	private static void print_diag() {
		diagonal_traversal(root, 0);
		for (int k : diag.keySet()) {
			ArrayList<Integer> temp = diag.get(k);
			for (int i = 0; i < temp.size(); i++) {
				System.out.print(temp.get(i) + " ");
			}
			System.out.println();
		}
	}
}
