package collection;

import java.util.Arrays;
import java.util.Random;
public final class MySortCheck<T extends Comparable<? super T>> {
	
	private static final int NUM_ITEMS = 1000;
	private static int theSeed = 1;

	private static void checkSort(Integer[] a) {
		for (int i = 0; i < a.length; i++)
			if (a[i] != i)
				System.out.println("Error at " + i);
		System.out.println("Finished checksort");
	}
}
