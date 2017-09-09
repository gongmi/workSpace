package pdd;

import java.util.*;
//10
//my-app -1
//src 0
//main 1
//java 2
//resources 2
//webapp 2
//test 1
//java 6
//resources 6
//pom.xml 0

//就是用一个node数组来存放所有的node
//并且通过children指出他们的父子关系
public class LinuxTree {

	static class Node implements Comparable<Node> {
		String name;
		PriorityQueue<Node> children = new PriorityQueue<>();

		@Override
		public int compareTo(Node o) {
			return name.compareTo(o.name);
		}

		public Node(String name) {
			super();
			this.name = name;
		}

		@Override
		public String toString() {
			return "Node [name=" + name + ", children=" + children + "]";
		}
	}

	private static Node[] array;
	private static int n;
	static Node orignalRoot;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();
		array = new Node[n];
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			String[] a = s.split(" ");
			int parent = Integer.parseInt(a[1]);

			array[i] = new Node(a[0]);
			if (parent != -1)
				array[parent].children.offer(array[i]);
		}
		System.out.println(Arrays.toString(array));
		orignalRoot = array[0];
		print(array[0], "");
	}

	private static void print(Node root, String string) {
		if (orignalRoot == root) {
			System.out.println(string + root.name);
		} else {
			System.out.println(string + "-- " + root.name);
		}
		string = string.replace('`', ' ');

		PriorityQueue<Node> children = root.children;
		while (!children.isEmpty()) {
			Node child = children.poll();
			String string2;
			if (children.isEmpty()) {// 可以不加上|
				string2 = root == orignalRoot ? string + "`" : string + "   `";
				print(child, string2);
			} else {
				string2 = root == orignalRoot ? string + "|" : string + "   |";
				print(child, string2);
			}
		}
	}

}
