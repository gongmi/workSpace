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

	// ������������ܵĽ�ĺ���
	private void Sum(BinaryNode root, int number, String s) {
		if (root == null)
			return;

		int n = root.element;

		// �����û����Ҫ�� �ͽ��������� �ݹ� �õ�ǰ��ֵ��ȥ����ڵ��ֵ������
		if (n < number) {
			Sum(root.left, number - n, s + n + ',');// �ѽڵ��ֵ��string�ķ�ʽ��������
			Sum(root.right, number - n, s + n + ',');
		}

		// ���뱣֤��Ҷ�ӽڵ� ���������Ҫ�� �����
		if (n == number && root.left == null && root.right == null) {
			s = s + n;
			System.out.println(s);
			example++;
		}

	}

	// ������ܵĽ�ĺ���
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
		HLX_tree Tree = new HLX_tree(); // ����һ��ʵ�� Ϊ�˵��ú���
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
