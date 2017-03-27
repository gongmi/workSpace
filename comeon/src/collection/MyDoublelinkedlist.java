package collection;

import java.util.*;

public class MyDoublelinkedlist<T> implements Iterable<T> {
	@Override
	public String toString() {
		Iterator<T> iterator = iterator();
		String s = "";
		while (iterator.hasNext())
			s = s + iterator.next();
		return s;
	}

	private static class Node<T> {
		// 静态内部类 如果不加static 那么不能用泛型
		// 因为如果不加static 那么这个内部类是属于MyDoublelinkedlist 实例的
		// 那么 这个T就和MyDoublelinkedlist T 不一样
		// 只有加了 static 这个 T才和上面那个T是同一个T
		public Node<T> prev;
		public Node<T> next;
		public T data;

		public Node(T d, Node<T> p, Node<T> n) {
			prev = p;
			next = n;
			data = d;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public MyDoublelinkedlist() {
		size = 0;
		head = new Node<T>(null, null, null);
		tail = new Node<T>(null, head, null);
		head.next = tail;

	}

	public void add(T data) {
		add(size, data);
	}

	public void add(int index, T data) {
		size++;
		Node<T> node = getNode(index);

		node.prev = node.prev.next = new Node<T>(data, node.prev, node);

	}

	public void set(int index, T data) {
		Node<T> node = getNode(index);
		node.data = data;
	}

	public void remove(int index) {
		Node<T> node = getNode(index);
		remove(node);
	}

	public T get(int index) {
		return getNode(index).data;

	}

	public boolean contain(T data) {
		Iterator<T> it = iterator();
		while (it.hasNext())
			if (it.next().equals(data))
				return true;
		return false;

	}

	private void addbefore(Node<T> p, T data) {
		Node<T> node = new Node<T>(data, p.prev, p);
		p.prev.next = node;
		p.prev = node;
		size++;
	}

	private void remove(Node<T> node) {
		node.next.prev = node.prev;
		node.prev.next = node.next;
		size--;
	}

	private Node<T> getNode(int index) {
		Node<T> node;
		if (index > size - 1)
			throw new IndexOutOfBoundsException();
		if (index <= (size / 2)) {
			node = head.next;
			for (int i = 0; i < index; i++)
				node = node.next;
		} else {
			node = tail.prev;
			for (int i = size; i > index; i--)
				node = node.prev;
		}
		return node;

	}

	public void removeAll(Iterable<? extends T> items) {
		Iterator<? extends T> ititems = items.iterator();

		while (ititems.hasNext()) {
			T data = ititems.next();
			Iterator<? extends T> it = this.iterator();
			while (it.hasNext()) {
				if (it.next().equals(data))
					it.remove();
			}
		}
	}

	public Iterator<T> iterator() {
		return new MyIterator();
	}

	public ListIterator<T> listIterator() {
		return new MyListIterator();
	}

	public class MyIterator implements Iterator<T> {
		protected Node<T> current = head;

		public boolean hasNext() {
			return current.next != tail;
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current = current.next;
			return current.data;
		}

		public void remove() {
			MyDoublelinkedlist.this.remove(current.prev);
		}

	}

	public class MyListIterator extends MyIterator implements ListIterator<T> {
		public boolean hasPrevious() {
			return current.prev != head;
		}

		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			current = current.prev;
			return current.data;
		}

		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		public void set(T e) {//和答案不一样 为什么答案要set( current.next, newVal );
			current.data = e;
		}

		public void add(T e) {
			addbefore(current.next,e);
		}

	}
}
