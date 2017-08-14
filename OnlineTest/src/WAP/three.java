package WAP;

import java.util.Scanner;

public class three {
	public static long maximumProduct(long[] nums) {
		long min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		long max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
		for (long n : nums) {
			if (n <= min1) {
				min2 = min1;
				min1 = n;
			} else if (n <= min2) {
				min2 = n;
			}
			if (n >= max1) {
				max3 = max2;
				max2 = max1;
				max1 = n;
			} else if (n >= max2) {
				max3 = max2;
				max2 = n;
			} else if (n >= max3) {
				max3 = n;
			}
		}
		return Math.max(min1 * min2 * max1, max1 * max2 * max3);
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long[] h = new long[n];
		for (int i = 0; i < n; i++) {
			h[i] = in.nextLong();
		}
		System.out.println(maximumProduct(h));
	}
}
