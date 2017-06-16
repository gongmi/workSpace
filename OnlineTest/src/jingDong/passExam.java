package jingDong;

import java.util.List;
//С��ͬѧҪ�μ�һ�����ԣ�����һ����n����Ŀ��
//С��������������60%����Ŀ����ͨ�����ԡ�
//���Խ�����С�������ÿ�����Եĸ��ʣ�
//p1,p2,...,pn�����ܰ��������ͨ�����Եĸ�����
//�������� : 4    [50 50 50 50]
//�������: 0.31250
//����� �ҵ�ʱ����� ���������backTracking��Ϊ�������������
//��n����Ŀ��ѡk��k=n*0.6�������� n-k������Ȼ���Ҿ���backTracking������ϵĹ�����
//count�����Ե���Ŀ�� ���С��k ��ֱ��return
//����baidu ��Permutationsһ�� һ��ʼ�Ҿ������Ȱ����еĸ������backTracking����
//Ȼ����backTracking�Ĺ����� countС�ڷ��ŵĸ���
//��ʵ ���ݷ�����ɵ�Ƶ�һ�ַ��� ��Ϊ����ʵû���õ��κθ߼����㷨
//ֻ��һζ��ȥö�� ��������Ҫ���ʱ�� ��return
//��ϸ�۲췢����backTracking����Ŀͨ��Ҫ�󷵻�һ��List<List<Integer>>
//Ҳ����˵ ���ݷ���Ҫ�����һ�������������Ҫ��Ľ��
//��dpͨ�����ֻ��һ��int
//����
//CombinationSum4_377	����	int	 					��dp
//CombinationSum_39    	����	List<List<Integer>> 	��backTracking

public class passExam {
	public double probability(int n, int[] a) {
		double[][] dp = new double[n + 1][n + 1];
		// ǰi����Ŀ��j���ĸ���
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
