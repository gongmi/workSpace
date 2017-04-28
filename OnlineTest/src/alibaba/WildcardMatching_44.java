package alibaba;

import java.util.Scanner;

public class WildcardMatching_44 {
	public static void main(String[] args) {

		 Scanner in = new Scanner(System.in);
		 String s1 = in.nextLine();
		 String s2 = in.nextLine();
		System.out.println(filter(s1, s2));
	}

	public static int filter(String s, String p) {
		int s_len=s.length();
		int p_len=p.length();
		int[][] dp = new int[s_len + 1][p_len + 1];
		dp[s_len][p_len] = 1;
		for (int i = p_len - 1; i >= 0; i--) {
			if (p.charAt(i) != '*')
				break;
			else
				dp[s_len][i] = 1;
		}
		for (int i = s_len - 1; i >= 0; i--) {
			for (int j = p_len - 1; j >= 0; j--) {
				if (p.charAt(j) == '*')
					dp[i][j] = (dp[i + 1][j] | dp[i][j + 1]);
				else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
					dp[i][j] = dp[i + 1][j + 1];
				else
					dp[i][j] = 0;
			}
		}
		return dp[0][0];
	}
}
