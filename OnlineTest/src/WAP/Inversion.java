package WAP;

import java.util.Scanner;

public class Inversion {
	static int mod = 10000;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		System.out.println(Inversions(n, k));
	}

	public static int Inversions(int n, int k) {
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++)
			dp[i][0] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i * (i - 1) / 2 && j <= k; j++) {
				int tmp1 = 0, tmp2 = 0, tmp3 = 0;
				tmp1 = dp[i - 1][j];
				tmp2 = dp[i][j - 1];
				if (j - i >= 0)
					tmp3 = dp[i - 1][j - i];
				dp[i][j] = ((tmp1 + tmp2 - tmp3) % mod + mod) % mod;
			}
		}
		return dp[n][k];
	}
}
