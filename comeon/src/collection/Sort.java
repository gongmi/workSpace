package collection;

import java.util.Arrays;
import java.util.Random;

public final class Sort<T extends Comparable<? super T>> {
	/**
	 * Simple insertion sort.
	 */
	public void insertionSort(T[] a) {
		int j;

		for (int p = 1; p < a.length; p++) {
			T tmp = a[p];
			for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	/**
	 * Shellsort, using Shell's (poor) increments.
	 */
	public void shellsort(T[] a) {
		int j;

		for (int gap = a.length / 2; gap > 0; gap /= 2)
			for (int i = gap; i < a.length; i++) {
				T tmp = a[i];
				for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
					a[j] = a[j - gap];
				a[j] = tmp;
			}
	}

	/**
	 * Internal method for heapsort.
	 * 
	 * @param i
	 *            the index of an item in the heap.
	 * @return the index of the left child.
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * Internal method for heapsort that is used in deleteMax and buildHeap.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @index i the position from which to percolate down.
	 * @int n the logical size of the binary heap.
	 */
	private void percDown(T[] a, int i, int n) {
		int child;
		T tmp;

		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			//如果当前的左孩子不为最后一个node并且右孩子大于左孩子 那么选右孩子 即选最大的那个孩子
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
				child++;
			if (tmp.compareTo(a[child]) < 0)//如果这个node小于最大的孩子 说明要换 用替换不用交换 因为node的值存起来了
				a[i] = a[child];
			else
				break;
		}
		a[i] = tmp;
	}

	/**
	 * Standard heapsort.
	 */
	public void heapsort(T[] a) {
		for (int i = a.length / 2 - 1; i >= 0; i--)
			/* buildHeap 从len的一半处开始下滤 一直到根节点 完成构建大顶堆*/
			percDown(a, i, a.length);
		for (int i = a.length - 1; i > 0; i--) {
			swapReferences(a, 0, i); /* 将大顶堆的根节点也就是最大值与i交换  i从len到1 */
			percDown(a, 0, i);//然后再把 0-i的数组重建堆 其实在这个建堆 只需要下滤根节点就可以了 因为其他结点已经满足要求了
		}
	}

	/**
	 * Mergesort algorithm.
	 */
	public void mergeSort(T[] a) {
		T[] tmpArray = (T[]) new Comparable[a.length];

		mergeSort(a, tmpArray, 0, a.length - 1);
		// mergeSort1(a, tmpArray, 0, a.length - 1);
	}

	// 使用递归实现的mergeSort
	private void mergeSort(T[] a, T[] tmpArray, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	// 使用迭代实现的mergeSort
	private void mergeSort1(T[] a, T[] tmpArray, int left, int right) {
		int n = a.length;
		for (int subListSize = 1; subListSize < n; subListSize *= 2) {
			int part1Start = 0;
			while (part1Start + subListSize < n) {
				int part2Start = part1Start + subListSize;
				int part2End = Math.min(n - 1, part2Start + subListSize - 1);
				merge(a, tmpArray, part1Start, part2Start, part2End);
				part1Start = part2End + 1;
			}
		}
	}

	private void merge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// 当左与右都有的时候
		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos].compareTo(a[rightPos]) <= 0)
				tmpArray[tmpPos++] = a[leftPos++];
			else
				tmpArray[tmpPos++] = a[rightPos++];
		// 当右已经完结 左还有的时候
		while (leftPos <= leftEnd)
			tmpArray[tmpPos++] = a[leftPos++];
		// 当左已经完结 右还有的时候
		while (rightPos <= rightEnd)
			tmpArray[tmpPos++] = a[rightPos++];
		//		把temp复制给a
		for (int i = 0; i < numElements; i++, rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}

	public void quicksort(T[] a) {
		quicksort(a, 0, a.length - 1);
	}

	// 当数组长度小于CUTOFF 就用插入排序
	private static final int CUTOFF = 10;

	public static <T> void swapReferences(T[] a, int index1, int index2) {
		T tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	/**
	 * Return median of left, center, and right. Order these and hide the pivot.
	 */
	private T median3(T[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[center].compareTo(a[left]) < 0)
			swapReferences(a, left, center);
		if (a[right].compareTo(a[left]) < 0)
			swapReferences(a, left, right);
		if (a[right].compareTo(a[center]) < 0)
			swapReferences(a, center, right);

		// Place pivot at position right - 1
		swapReferences(a, center, right - 1);
		return a[right - 1];
	}

	private void quicksort(T[] a, int left, int right) {
		if (left + CUTOFF <= right) {
			T pivot = median3(a, left, right);

			int i = left, j = right - 1;
			for (;;) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(a, i, j);
				else
					break;
			}

			swapReferences(a, i, right - 1); // Restore pivot

			quicksort(a, left, i - 1); // Sort small elements
			quicksort(a, i + 1, right); // Sort large elements
		} else
			insertionSort(a, left, right);
	}

	private void insertionSort(T[] a, int left, int right) {
		for (int p = left + 1; p <= right; p++) {
			T tmp = a[p];
			int j;

			for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	/**
	 * Quick selection algorithm. Places the kth smallest item in a[k-1].
	 * @param k
	 * the desired rank (1 is minimum) in the entire array.
	 */
	public void quickSelect(T[] a, int k) {
		quickSelect(a, 0, a.length - 1, k);
	}

	private void quickSelect(T[] a, int left, int right, int k) {
		if (left + CUTOFF <= right) {
			T pivot = median3(a, left, right);

			// Begin partitioning
			int i = left, j = right - 1;
			for (;;) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(a, i, j);
				else
					break;
			}

			swapReferences(a, i, right - 1); // Restore pivot

			if (k <= i)
				quickSelect(a, left, i - 1, k);
			else if (k > i + 1)
				quickSelect(a, i + 1, right, k);
		} else
			// Do an insertion sort on the subarray
			insertionSort(a, left, right);
	}


}
