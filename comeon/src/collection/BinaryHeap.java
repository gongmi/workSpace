package collection;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize; // Number of elements in heap
	private AnyType[] array; // The heap array

	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public BinaryHeap(int capacity) {
		currentSize = 0;
		array = (AnyType[]) new Comparable[capacity + 1];
	}

	@SuppressWarnings("unchecked")
	public BinaryHeap(AnyType[] items) {
		currentSize = items.length;
		array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];

		int i = 1;
		for (AnyType item : items)
			array[i++] = item;
		buildHeap();
	}

	public void insert(AnyType x) {
		if (currentSize == array.length - 1)
			enlargeArray(array.length * 2 + 1);

		// Percolate up
		int hole = ++currentSize;
		for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;
	}

	private void enlargeArray(int newSize) {
		AnyType[] old = array;
		array = (AnyType[]) new Comparable[newSize];
		for (int i = 0; i < old.length; i++)
			array[i] = old[i];
	}

	public AnyType findMin() {
		return array[1];
	}

	public AnyType deleteMin() {
		AnyType minItem = findMin();
		array[1] = array[currentSize--];
		percolateDown(1);

		return minItem;
	}

	/**
	 * Establish heap order property from an arbitrary arrangement of items.
	 * Runs in linear time.
	 */
	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public void makeEmpty() {
		currentSize = 0;
	}

	/**
	 * Internal method to percolate down in the heap.
	 * 
	 * @param hole
	 *            the index at which the percolate begins.
	 */
	private void percolateDown(int hole) {
		int child;
		AnyType tmp = array[hole];

		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
				child++;
			if (array[child].compareTo(tmp) < 0)
				array[hole] = array[child];
			else
				break;
		}
		array[hole] = tmp;
	}

	// Test program
	public static void main(String[] args) {
		int numItems = 100;
		BinaryHeap<Integer> h = new BinaryHeap<>();
		int i = 37;
		for (i = 37; i != 0; i = (i + 37) % numItems) {
			System.out.println(i);
			h.insert(i);// �����ַ����������1-numItems����
		}
		for (i = 1; i < numItems; i++)
			System.out.println(h.deleteMin());
	}
}
