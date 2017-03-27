package collection;

//3.32 使用link 实现的队列
public class MyLinkedQueue<T> {
	
	private Node<T> front, rear;

	private static class Node<T> {

		public Node<T> next;
		public T data;

		public Node(T d, Node<T> n) {
			data = d;
			next = n;
		}
	}

	public MyLinkedQueue() {
	}

	public void enqueue(T data) {
		Node<T> node = new Node<T>(data, null);
		if (rear != null) {
			rear.next = node;
			rear = node;
		} else {
			rear = node;
			front = node;
		}

	}

	public T dequeue() {

		if (front == null) // no node
		{
			System.out.println("空");
			return null;
		}
		T d = front.data;
		if (front == rear) // only 1 node
			front = rear = null;
		else
			front = front.next;
		return d;
	}

	public void print() {
		Node<T> node = front;
		while (node != null) {
			System.out.println(node.data);
			node = node.next;
		}
	}
}
