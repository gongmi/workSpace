package collection;

import java.util.LinkedList;
import java.util.List;

//��������hashmap �Ҹ�д��
//���ǰ�java���������� SeparateChainingHashTable�����Ԫ��<T>��ΪEntry<K, V>

//��JDK����ʵ��HashMap������
//1.��������List<Entry<K, V>>[] hashmap 1.8���õ���node �̳���Entry��node ��next

public class SeparateChainingHashMap<K, V> {

	private static final int DEFAULT_TABLE_SIZE = 5;
	private List<Entry<K, V>>[] theLists;// һ���������� ����Ϊ���� ��������Ϊ����������
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
		// ����û�а취ֻ������ ���ܴ��������͵�������� ֻ������
		for (int i = 0; i < theLists.length; i++)
			theLists[i] = new LinkedList<>();
	}

	public void put(K k, V v) {

		put(new Entry<K, V>(k, v));

	}

	public V get(K k) {
		List<Entry<K, V>> whichList = theLists[myhash(k)];//���ҵ���Ӧ������
		for (int i = 0; i < whichList.size(); i++)//��ѭ��������
			if (whichList.get(i).key.equals(k))
				return whichList.get(i).value;
			else
				System.out.println("�������������");
		return null;
	}

	private void put(Entry<K, V> e) {
		List<Entry<K, V>> whichList = theLists[myhash(e.key)];//���ҵ���Ӧ������
		for (int i = 0; i < whichList.size(); i++)//��ѭ��������
			if (whichList.get(i).key.equals(e.key)) {
				whichList.set(i, e);// һ���͸���
				return;
			}

		whichList.add(e);// ��һ���������������һ���µ�entry
		if (++currentSize > theLists.length)
			// ˵����������Ϊ1
			// ���Ԫ�ظ����������鳤�� ��ô˵��Ҫ������ Ҫ��Ȼ�ͻ�����ͬ��Ԫ��ӳ�䵽ͬһ��������
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
		// ���ݵĻ� �ҵ�ԭ���Ǹ���������������ĵ�һ��������Ϊ���鳤��
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
