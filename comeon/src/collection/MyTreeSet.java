package collection;

import java.util.Iterator;

//�����������ݽṹ���㷨����java ϰ��4.11
//��������� �и��ڵ�
//ʹ����Ȼ���� 

public class MyTreeSet<T extends Comparable<? super T>> implements Iterable<T> {

	private static class BinaryNode<T> {
		// Friendly data; accessible by other package routines
		T element; // The data in the node
		BinaryNode<T> left; // Left child
		BinaryNode<T> right; // Right child
		BinaryNode<T> parent; // Right child

		BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt, BinaryNode<T> pt) {
			element = theElement;
			left = lt;
			right = rt;
			parent = pt;
		}
	}

	private BinaryNode<T> root;

	public MyTreeSet() {
		root = null;
	}

	public void insert(T x) {
		root = insert(x, root, null);
	}

	public void remove(T x) {
		root = remove(x, root);
	}

	public T findMin() {
		return elementAt(findMin(root));
	}

	public T findMax() {
		return elementAt(findMax(root));
	}

	public T find(T x) {
		return elementAt(find(x, root));
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	private T elementAt(BinaryNode<T> t) {
		return t == null ? null : t.element;
	}

	private BinaryNode<T> insert(T x, BinaryNode<T> t, BinaryNode<T> pt) {
		if (t == null)
			t = new BinaryNode<>(x, null, null, pt);
		else if (x.compareTo(t.element) < 0)
			t.left = insert(x, t.left, t);
		else if (x.compareTo(t.element) > 0)
			t.right = insert(x, t.right, t);
		else
			; // Duplicate; do nothing
		return t;
	}

	private BinaryNode<T> remove(T x, BinaryNode<T> t) {
		if (t == null)
			return t; // Item not found; do nothing
		if (x.compareTo(t.element) < 0)
			t.left = remove(x, t.left);
		else if (x.compareTo(t.element) > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else if (t.left != null && t.right == null) {
			BinaryNode<T> onechild = t.left;
			onechild.parent = t.parent;
			t = onechild;
		} else if (t.left == null && t.right != null) {
			BinaryNode<T> onechild = t.right;
			onechild.parent = t.parent;
			t = onechild;
		} else
			t = null;

		return t;
	}

	private BinaryNode<T> findMin(BinaryNode<T> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	private BinaryNode<T> findMax(BinaryNode<T> t) {
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	private BinaryNode<T> find(T x, BinaryNode<T> t) {
		if (t == null)
			return null;
		if (x.compareTo(t.element) < 0)
			return find(x, t.left);
		else if (x.compareTo(t.element) > 0)
			return find(x, t.right);
		else
			return t; // Match
	}

	private void printTree(BinaryNode<T> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	public Iterator<T> iterator() {
		return new MyIterator();

	}

	private class MyIterator implements Iterator<T> {
		private BinaryNode<T> current = findMin(root);
		private BinaryNode<T> previous;// ��previous �� hold סcurrent��prev��node
		private boolean atEnd = false;

		public boolean hasNext() {
			return (!atEnd);
		}

		public T next() {
			previous = current;
			if (current.right != null)
				current = findMin(current.right);
			else // �����ǰcurrent��node������������ ��ֻ��������
			{
				BinaryNode<T> child = current;
				current = current.parent;
				// �����ǰchild��parent��current���������� ����������
				while (current != null && current.right == child) {
					child = current;
					current = current.parent;
				}
				// ֱ���ҵ�һ����ǰchild��parent��current����������
				// ���ҵ�����һ��current~~��
				if (current == null)
					atEnd = true;
			}
			return previous.element;
		}

		// ֻ���ڵ�����next������ſ��Ե���remove
		public void remove() {// ע������ɾ��Ӧ����ɾ��prev ��Ϊ���ڵ�current�Ѿ�ָ���һ����
			MyTreeSet.this.remove(previous.element);
		}

	}

}
