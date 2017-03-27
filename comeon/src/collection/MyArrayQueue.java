package collection;

import java.util.ArrayList;

//3.33 使用循环数组实现的队列
public class MyArrayQueue<T> {

	private int front, rear;
	private int maxSize;
	private ArrayList<T> elements;
	
	MyArrayQueue() {
		this(101);
	} // note: actually holds one less than given size

	MyArrayQueue(int s) {
		maxSize = s;
		front = 0;
		rear = 0;
		elements = new ArrayList<T>(maxSize);
	}

	void enqueue(T x) {
		if (!full()) {
			if (elements.size() < maxSize) // add elements until size is reached
				elements.add(x);
			else
				elements.set(rear, x); // after size is reached, use set
			rear = (rear + 1) % maxSize;
		}
	}

	T dequeue() {
		T temp = null;
		if (!empty()) {
			temp = elements.get(front);
			front = (front + 1) % maxSize;
		}
		return temp;
	}

	boolean empty() {
		return front == rear;
	}

	boolean full() {//队列满的条件
		return (rear + 1) % maxSize == front; 
	}

}