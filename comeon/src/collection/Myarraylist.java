package collection;

import java.util.*;

public class Myarraylist<T> implements Iterable<T> {
	private int size;

	public int getSize() {
		return size;
	}

	private T[] array;
	private static final int DEFAULT_CAPACITY = 10;

	public Myarraylist()

	{
		size = 0;
		ensurecapacity(DEFAULT_CAPACITY);

	}

	@SuppressWarnings("unchecked")
	private void ensurecapacity(int newcapacity) {
		if (newcapacity < size)
			return;
		T[] new_array = (T[]) new Object[newcapacity];
		// 这里没有办法只能这样 不能创建带泛型的数组对象 只能声明
		for (int i = 0; i < size; i++)
			new_array[i] = array[i];
		array = new_array;
		// System.out.println(array.length);
	}

	public void add(int index, T item) {
		if (size == array.length)
			ensurecapacity(array.length * 2 + 1);
		for (int i = size; i > index; i--)
			array[i] = array[i - 1];
		array[index] = item;
		size++;
	}

	public void add(T item) {
		add(size, item);
	}

	public void addAll(Iterable<? extends T> items) {
		Iterator<? extends T> it = items.iterator();
		while (it.hasNext()) {
			add(it.next());
		}
	}

	public void removeAll(Iterable<? extends T> items) {
		Iterator<? extends T> ititems = items.iterator();

		while (ititems.hasNext()) {
			T data = ititems.next();
			Iterator<? extends T> itarray = this.iterator();
			while (itarray.hasNext()) {
				if (itarray.next().equals(data))
					itarray.remove();
			}
		}
	}

	// public int contain(T data) {
	// for(int i=0;i<size;i++)
	// { if (array[i].equals(data))
	// {
	// System.out.println("happy");
	// return i;}
	// }
	// return -1;
	// }
	public T get(int index) {
		if (index < 0 || index > size - 1)
			throw new ArrayIndexOutOfBoundsException();
		return array[index];
	}

	public void set(int index, T item) {
		if (index < 0 || index > size - 1)
			throw new ArrayIndexOutOfBoundsException();
		array[index] = item;
	}

	public void remove(int index) {
		if (index < 0 || index > size - 1)
			throw new ArrayIndexOutOfBoundsException();
		for (int i = index; i < size - 1; i++)
			array[i] = array[i + 1];
		size--;
	}

	public Iterator<T> iterator() {
		return new MyIterator();
	}

	public ListIterator<T> listIterator() {
		return new MyListIterator();
	}

	public class MyIterator implements Iterator<T> {
		private int current = 0;

		// 这是一个内部类 可以直接用外部类的成员变量size array[]
		public boolean hasNext() {
			return current < size;
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[current++];
		}

		public void remove() {
			// 这是一个内部类 要调用外部类的remove方法这样用：不然会混淆
			Myarraylist.this.remove(--current);
		}
	}

	public class MyListIterator implements ListIterator<T> {// 内部类 private
		int current = 0;

		public boolean hasNext() {
			return current < size;
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[current++];
		}

		public void remove() {
			Myarraylist.this.remove(--current);
		}

		public boolean hasPrevious() {
			return current > 0;
		}

		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			return array[--current];
		}

		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		public void set(T e) {
			Myarraylist.this.set(current, e);

		}

		public void add(T e) {
			Myarraylist.this.add(current++, e);

		}

	}

	@Override
	public String toString() {
		return "Myarraylist [size=" + size + ", array=" + Arrays.toString(array) + "]";
	}

}
