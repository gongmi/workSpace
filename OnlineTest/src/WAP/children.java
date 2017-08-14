package WAP;

import java.util.Arrays;
import java.util.Scanner;

public class children {
	public static int findChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int i = 0, j = 0, sum = 0;
		while (j < s.length && i < g.length) {
			if (g[i] <= s[j]) {
				i++;
				sum++;
			}
			j++;
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] h = new int[n];
		for (int i = 0; i < n; i++) {
			h[i] = in.nextInt();
		}
		int m = in.nextInt();
		int[] w = new int[m];
		for (int i = 0; i < m; i++) {
			w[i] = in.nextInt();
		}

		System.out.println(findChildren(h, w));
	}
}
