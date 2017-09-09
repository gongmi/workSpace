package collection;

public class MySort {
	public static void insertSort(int[] a) {
		int j = 0;
		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			for (j = i; j >= 1 && a[j - 1] > temp; j--)
				a[j] = a[j - 1];
			a[j] = temp;
		}

	}

	
	public static void shellSort(int[] a) {
		int j = 0;
		for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
			for (int i = gap; i < a.length; i++) {
				int temp = a[i];
				for (j = i; j >= gap && a[j - gap] > temp; j = j - gap)
					a[j] = a[j - gap];
				a[j] = temp;
			}
		}
	}

	public static void mergeSort(int[] a) {
		int[] copy = new int[a.length];
		mergeSort(a, copy, 0, a.length - 1);
	}

	private static void mergeSort(int[] a, int[] copy, int left, int right) {
		if (left >= right)
			return;
		int mid = (left + right) >> 1;
		mergeSort(a, copy, left, mid);
		mergeSort(a, copy, mid + 1, right);
		merge(a, copy, left, mid, right);
	}

	private static void merge(int[] a, int[] copy, int leftPos, int leftEnd, int rightEnd) {
		int rightPos = leftEnd + 1;
		int copyPos = leftPos;
		int start = leftPos;
		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (a[leftPos] < a[rightPos])
				copy[copyPos++] = a[leftPos++];
			else
				copy[copyPos++] = a[rightPos++];
		}
		while (leftPos <= leftEnd)
			copy[copyPos++] = a[leftPos++];
		while (rightPos <= rightEnd)
			copy[copyPos++] = a[rightPos++];
		for (int i = start; i <= rightEnd; i++)
			a[i] = copy[i];
	}

	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}

	private static void quickSort(int[] a, int left, int right) {
		if (left >= right)
			return;
		int i = left;
		int j = right;
		median3(a, left, right);
		int pivot = a[left];
		while (i < j) {
			while (i < j && a[j] >= pivot)
				j--;
			a[i] = a[j];
			while (i < j && a[i] <= pivot)
				i++;
			a[j] = a[i];
		}
		a[i] = pivot;
		quickSort(a, left, i - 1);
		quickSort(a, i + 1, right);
	}

	private static void median3(int[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[right] < a[left])
			swapReferences(a, left, right);
		if (a[right] < a[center])
			swapReferences(a, center, right);
		if (a[left] < a[center])
			swapReferences(a, center, left); // a[left]就是中数
	}

	public static void swapReferences(int[] a, int index1, int index2) {
		int tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	public static void heapSort(int[] a) {
		for (int i = a.length / 2; i >= 0; i--)
			percDown(a, i, a.length);
		for (int i = a.length - 1; i >= 1; i--) {
			swapReferences(a, 0, i);
			percDown(a, 0, i);
		}
	}

	private static void percDown(int[] a, int i, int n) {
		int temp;
		int child;
		for (temp = a[i]; i * 2 + 1 < n; i = child) {
			child = i * 2 + 1;
			if (child + 1 < n && a[child] < a[child + 1])
				child++;
			if (temp < a[child])//曾经写错成了if (a[i] < a[child]) 其实就是插入排序的思路！！
				a[i] = a[child];
			else
				break;
		}
		a[i]=temp;
	}
	
	 
}