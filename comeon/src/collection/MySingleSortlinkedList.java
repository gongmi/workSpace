package collection;

//java”Ô—‘√Ë ˆ 3.11
public class MySingleSortlinkedList<T extends Comparable<? super T>> {

	private static class Node<T> {
		public Node<T> next;
		public T data;

		public Node(T d, Node<T> n) {
			data = d;
			next = n;
		}
	}

	private Node<T> head;
	private int size;

	public MySingleSortlinkedList() {
		size = 0;
		head = new Node<T>(null, null);
	}

	public boolean add(T data) {
		if (!contain(data)) {
			Node<T> p = head.next;
			Node<T> trailer = head;
			Node<T> node = new Node<T>(data, null);

			while (p != null && data.compareTo(p.data) > 0) {
				p = p.next;
				trailer = trailer.next;
			}
			node.next = p;
			trailer.next = node;
			size++;
			return true;
		} else
			return false;
	}

	public boolean remove(T data) {
		if (contain(data)) {
			size--;

			Node<T> node = head;
			while (!node.next.data.equals(data))
				node = node.next;
			node.next = node.next.next;
			return true;

		} else {
			return false;
		}

	}

	public boolean contain(T data) {
		Node<T> node = head.next;
		while (node != null && data.compareTo(node.data) > 0) {
			if (node.data.equals(data))
				return true;
			else
				node = node.next;
		}
		return false;
	}

	public void print() {

		String s = size + "  ";
		Node<T> node = head.next;
		while (node != null) {
			s = s + node.data + "   ";
			node = node.next;
		}
		System.out.println(s);
	}

	public int getSize() {
		return size;
	}

	public void swapWithNext(Node<T> beforep) {
		Node<T> p = beforep.next;
		Node<T> afterp = p.next;

		beforep.next = afterp;
		p.next = afterp.next;
		afterp.next = p;
	}
}
