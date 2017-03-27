package collection;

public class QuadraticProbingHashTable<T> {
	private static class HashEntry<T> {
		public T element; // the element
		public boolean isActive; // false if marked deleted

		public HashEntry(T e, boolean i) {
			element = e;
			isActive = i;
		}
	}

	private static final int DEFAULT_TABLE_SIZE = 101;

	private HashEntry<T>[] array; // The array of elements
	private int occupied; // The number of occupied cells
	private int theSize; // Current size

	public QuadraticProbingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public QuadraticProbingHashTable(int size) {
		allocateArray(size);
		doClear();
	}


	public boolean insert(T x) {
		// Insert x as active
		int currentPos = findPos(x);
		if (isActive(currentPos))
			return false;

		array[currentPos] = new HashEntry<>(x, true);
		theSize++;

		// Rehash; see Section 5.5
		if (++occupied > array.length / 2)
			rehash();

		return true;
	}

	/**
	 * Expand the hash table.
	 */
	private void rehash() {
		HashEntry<T>[] oldArray = array;

		// Create a new double-sized, empty table
		allocateArray(2 * oldArray.length);
		occupied = 0;
		theSize = 0;

		// Copy table over
		for (HashEntry<T> entry : oldArray)
			if (entry != null && entry.isActive)
				insert(entry.element);
	}

	private int findPos(T x)// 平方探测法
	{
		int offset = 1;
		int currentPos = myhash(x);

		while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
			currentPos += offset; // Compute ith probe
			offset += 2;
			if (currentPos >= array.length)
				currentPos -= array.length;
		}

		return currentPos;
	}

	/**
	 * Remove from the hash table.
	 * 
	 * @param x
	 *            the item to remove.
	 * @return true if item removed
	 */
	public boolean remove(T x) {
		int currentPos = findPos(x);
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;
			theSize--;
			return true;
		} else
			return false;
	}

	/**
	 * Get current size.
	 * 
	 * @return the size.
	 */
	public int size() {
		return theSize;
	}

	/**
	 * Get length of internal table.
	 * 
	 * @return the size.
	 */
	public int capacity() {
		return array.length;
	}

	/**
	 * Find an item in the hash table.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the matching item.
	 */
	public boolean contains(T x) {
		int currentPos = findPos(x);
		return isActive(currentPos);
	}

	/**
	 * Return true if currentPos exists and is active.
	 * 
	 * @param currentPos
	 *            the result of a call to findPos.
	 * @return true if currentPos is active.
	 */
	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}

	/**
	 * Make the hash table logically empty.
	 */
	public void makeEmpty() {
		doClear();
	}

	private void doClear() { //一开始 每一个 HashEntry都指向null
		occupied = 0;
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}

	private int myhash(T x) {
		int hashVal = x.hashCode();

		hashVal %= array.length;
		if (hashVal < 0)
			hashVal += array.length;

		return hashVal;
	}

	/**
	 * Internal method to allocate array.
	 * 
	 * @param arraySize
	 *            the size of the array.
	 */
	private void allocateArray(int arraySize) {
		array = new HashEntry[nextPrime(arraySize)];
	}

	/**
	 * Internal method to find a prime number at least as large as n.
	 * 
	 * @param n
	 *            the starting number (must be positive).
	 * @return a prime number larger than or equal to n.
	 */
	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	/**
	 * Internal method to test if a number is prime. Not an efficient algorithm.
	 * 
	 * @param n
	 *            the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}
}
