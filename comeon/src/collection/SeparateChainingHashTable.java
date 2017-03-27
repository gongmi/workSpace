package collection;

import java.util.LinkedList;
import java.util.List;

// 分离链表hashtable 
public class SeparateChainingHashTable<T> {
	private static final int DEFAULT_TABLE_SIZE = 101;
	private List<T>[] theLists;
	private int currentSize;

	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++)
			theLists[i] = new LinkedList<>();
	}

	public void insert(T x) {
		List<T> whichList = theLists[myhash(x)];
		if (!whichList.contains(x)) {
			whichList.add(x);

			// Rehash; see Section 5.5
			if (++currentSize > theLists.length)
				rehash();
		}
	}

	public void remove(T x) {
		List<T> whichList = theLists[myhash(x)];
		if (whichList.contains(x)) {
			whichList.remove(x);
			currentSize--;
		}
	}

	public boolean contains(T x) {
		List<T> whichList = theLists[myhash(x)];
		return whichList.contains(x);
	}

	/**
	 * Make the hash table logically empty.
	 */
	public void makeEmpty() {
		for (int i = 0; i < theLists.length; i++)
			theLists[i].clear();
		currentSize = 0;
	}

	public static int hash(String key, int tableSize)// string 的hash方法
	{
		int hashVal = 0;

		for (int i = 0; i < key.length(); i++)
			hashVal = 37 * hashVal + key.charAt(i);

		hashVal %= tableSize;
		if (hashVal < 0)
			hashVal += tableSize;

		return hashVal;
	}

	private void rehash() {//当装填因子为1时 扩大表的长度theLists.length
		List<T>[] oldLists = theLists;

		// Create new double-sized, empty table
		theLists = new List[nextPrime(2 * theLists.length)];
		for (int j = 0; j < theLists.length; j++)
			theLists[j] = new LinkedList<>();

		// 把旧的链表数组里面所有的数都拿出来 重新放进新的链表数组里
		currentSize = 0;
		for (List<T> list : oldLists)
			for (T item : list)
				insert(item);
	}

	private int myhash(T x) // 随便一个对象怎么hash 除留余数法
	{
		int hashVal = x.hashCode();

		hashVal %= theLists.length;
		if (hashVal < 0)
			hashVal += theLists.length;

		return hashVal;
	}

	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

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
