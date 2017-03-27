package collection;

import java.util.Scanner;

public class HLX_tree {
	class BinaryNode {

		// Friendly data; accessible by other package routines
		int element; // The data in the node
		BinaryNode left; // Left child
		BinaryNode right; // Right child

		BinaryNode(int theElement, BinaryNode lt, BinaryNode rt) {
			element = theElement;
			left = lt;
			right = rt;

		}

	}

	private BinaryNode root;
	private int example = 0;

	public void insert(int x) {
		root = insert(x, root);
	}

	private BinaryNode insert(int x, BinaryNode t) {
		if (t == null)
			t = new BinaryNode(x, null, null);
		else if (x < t.element)
			t.left = insert(x, t.left);
		else if (x > t.element)
			t.right = insert(x, t.right);
		else
			;
		return t;
	}

	// 真正的输出可能的解的函数
	private void Sum(BinaryNode root, int number, String s) {
		if (root == null)
			return;

		int n = root.element;

		// 如果还没满足要求 就接着往下找 递归 用当前的值减去这个节点的值往下找
		if (n < number) {
			Sum(root.left, number - n, s + n + ',');// 把节点的值用string的方式保存起来
			Sum(root.right, number - n, s + n + ',');
		}

		// 必须保证是叶子节点 如果满足了要求 就输出
		if (n == number && root.left == null && root.right == null) {
			s = s + n;
			System.out.println(s);
			example++;
		}

	}

	// 输出可能的解的函数
	public void Sum(int number) {
		Sum(root, number, "");
		if (example == 0)
			System.out.println("error");
	}

	// Test program
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();//
		sc.nextLine();//
		HLX_tree Tree = new HLX_tree(); // 创建一个实例 为了调用函数
		String s = sc.nextLine();//
		if (s!="")
		{sc.close();
		String[] number = s.split(",");
		for (int i = 0; i < number.length; i++)
			Tree.insert(Integer.parseInt(number[i]));
		Tree.Sum(num);
		}
		else
			System.out.println("error");

		
	}
}
