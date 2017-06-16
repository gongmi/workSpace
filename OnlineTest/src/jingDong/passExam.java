package jingDong;

import java.util.List;
//小明同学要参加一场考试，考试一共有n道题目，
//小明必须做对至少60%的题目才能通过考试。
//考试结束后，小明估算出每题做对的概率，
//p1,p2,...,pn。你能帮他算出他通过考试的概率吗？
//样例输入 : 4    [50 50 50 50]
//样例输出: 0.31250
//这道题 我当时想错了 我想成了用backTracking因为这道题就是组合嘛
//在n个题目中选k（k=n*0.6）个做对 n-k个做错，然后我就在backTracking构造组合的过程中
//count他作对的题目数 如果小于k 就直接return
//就像baidu 的Permutations一样 一开始我就想着先把排列的各种情况backTracking出来
//然后在backTracking的过程中 count小于符号的个数
//其实 回溯法是最傻逼的一种方法 因为它其实没有用到任何高级的算法
//只是一味地去枚举 当不满足要求的时候 就return
//仔细观察发现用backTracking的题目通常要求返回一个List<List<Integer>>
//也就是说 回溯法需要求的是一个个具体的满足要求的结果
//而dp通常求的只是一个int
//比如
//CombinationSum4_377	返回	int	 					用dp
//CombinationSum_39    	返回	List<List<Integer>> 	用backTracking

public class passExam {
	public double probability(int n, int[] a) {
		double[][] dp = new double[n + 1][n + 1];
		// 前i个题目对j个的概率
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++)
			dp[i][0] = (1 - a[i - 1] / 100.0) * dp[i - 1][0];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= i; j++)
				dp[i][j] = dp[i - 1][j - 1] * a[i - 1] / 100.0 + dp[i - 1][j] * (100 - a[i - 1])
						/ 100.0;

		int count = (int) Math.ceil(n * 0.6);
		double res = 0;
		for (int i = count; i <= n; i++)
			res += dp[n][i];
		return res;
	}
}
