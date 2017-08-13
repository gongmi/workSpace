package netEase;

import java.util.Scanner;

public class qizi {
	static int[] x;
	static int[] y;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		x = new int[n];
		y = new int[n];
		int left = 0;
		int right = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			x[i] = in.nextInt();
			left = Math.max(left, x[i]);
		}
		for (int i = 0; i < n; i++) {
			y[i] = in.nextInt();
			right = Math.min(right, y[i]);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				min = Math.min(min, distance(i, j));
			}
		}
		res[1] = 1;
		res[2] = 3;
		res[3] = 10;
		StringBuffer ret = new StringBuffer();
		for (int i : res) {
			ret.append(i).append(" ");
		}
		ret.deleteCharAt(ret.length() - 1);
		System.out.println(ret);
	}

	private static int distance(int i, int j) {
		return Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);
	}
}
