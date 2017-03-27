package collection;

import java.util.LinkedList;
import java.util.List;

//分离链表hashmap 我改写的
//就是把java语言描述中 SeparateChainingHashTable里面的元素<T>改为Entry<K, V>

//与JDK中真实的HashMap的区别
//1.这里用了List<Entry<K, V>>[] hashmap 1.8中用的是node 继承了Entry的node 有next

public class SeparateChainingHashMap<K, V> {

	private static final int DEFAULT_TABLE_SIZE = 5;
	private List<Entry<K, V>>[] theLists;// 一个链表数组 长度为质数 数组内容为质数个链表
	private int currentSize;

	public int getCurrentSize() {
		return currentSize;
	}

	private static class Entry<K, V> {
		K key;
		V value;

		public Entry(K k, V v) {
			key = k;
			value = v;
		}
	}

	public SeparateChainingHashMap() {
		this(DEFAULT_TABLE_SIZE);
	}

	public SeparateChainingHashMap(int size) {
		theLists = new LinkedList[nextPrime(size)];
		// 这里没有办法只能这样 不能创建带泛型的数组对象 只能声明
		for (int i = 0; i < theLists.length; i++)
			theLists[i] = new LinkedList<>();
	}

	public void put(K k, V v) {

		put(new Entry<K, V>(k, v));

	}

	public V get(K k) {
		List<Entry<K, V>> whichList = theLists[myhash(k)];//先找到对应的链表
		for (int i = 0; i < whichList.size(); i++)//再循环链表找
			if (whichList.get(i).key.equals(k))
				return whichList.get(i).value;
			else
				System.out.println("不包含这个东西");
		return null;
	}

	private void put(Entry<K, V> e) {
		List<Entry<K, V>> whichList = theLists[myhash(e.key)];//先找到对应的链表
		for (int i = 0; i < whichList.size(); i++)//再循环链表找
			if (whichList.get(i).key.equals(e.key)) {
				whichList.set(i, e);// 一样就覆盖
				return;
			}

		whichList.add(e);// 不一样就往链表里添加一个新的entry
		if (++currentSize > theLists.length)
			// 说明负载因子为1
			// 如果元素个数大于数组长度 那么说明要扩容了 要不然就会有相同的元素映射到同一个链表上
			rehash();
	}

	public void makeEmpty() {
		for (int i = 0; i < theLists.length; i++)
			theLists[i].clear();
		currentSize = 0;
	}

	private void rehash() {
		List<Entry<K, V>>[] oldLists = theLists;

		// Create new double-sized, empty table
		theLists = new LinkedList[nextPrime(2 * theLists.length)];
		// 扩容的话 找到原来那个质数的两倍往后的第一个质数作为数组长度
		for (int j = 0; j < theLists.length; j++)
			theLists[j] = new LinkedList<>();

		// Copy table over
		currentSize = 0;
		for (List<Entry<K, V>> list : oldLists)
			for (Entry<K, V> item : list)
				put(item);
	}

	private int myhash(K k) {
		int hashVal = k.hashCode();

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
