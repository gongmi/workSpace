package baidu;

import java.util.*;

public class Permutations_46 {
	static int res = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[][] dp = new int[1002][1002];
		for (int i = 0; i <= 1001; i++) {
			dp[i][0] = 1;
			dp[0][i] = 0;
		}
		for (int i = 1; i <= 1001; i++)
			for (int j = 1; j <= 1001; j++) {
				dp[i][j] = (dp[i - 1][j] * (j + 1) + dp[i - 1][j - 1] * (i - j)) % 2017;
			}
		System.out.println(dp[n][k]);
	}
}
