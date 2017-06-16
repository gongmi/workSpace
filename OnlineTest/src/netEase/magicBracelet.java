package netEase;

import java.util.Arrays;
import java.util.Scanner;
//小易拥有一个拥有魔力的手环上面有n个数字(构成一个环),
//当这个魔力手环每次使用魔力的时候就会发生一种奇特的变化：
//每个数字会变成自己跟后面一个数字的和(最后一个数字的后面一个数字是第一个),
//一旦某个位置的数字大于等于100就马上对100取模(比如某个位置变为103,就会自动变为3).
//现在给出这个魔力手环的构成，请你计算出使用k次魔力之后魔力手环的状态。 

//如输入A = [1, 2, 3], k = 2。
//我们可以构造一个这样的矩阵x（代码中的x矩阵）

//[1, 1, 0] 
//[0, 1, 1]
//[1, 0, 1]
// 使得B^k*A相当于A转换k次后的样子。
//于是原问题就变成求矩阵快速幂。
//快速幂取余中，x^k % c =  (x % c)^k % c
public class magicBracelet {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = 0;
		int k = 0;
		int[] a = null;
		n = in.nextInt();
		k = in.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}
		// afterK(n, a, k);
		int[][] matrix = afterK2(n, k);
		int[] b = new int[n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				b[i] += matrix[i][j] * a[j];
				b[i] %= 100;
			}
		String res = "";
		for (int i = 0; i < n; i++)
			res = res + b[i] + " ";
		res = res.trim();
		System.out.println(res);
	}

	// 暴力求解法 即时间复杂度为 k*n  测试用例都是k很大n很小 如7 192347
	public static void afterK(int n, int[] a, int k) {
		for (int i = 0; i < k; i++) {
			int temp = a[0];
			for (int cur = 0; cur < n; cur++) {
				if (cur < n - 1)
					a[cur] += a[cur + 1];
				else
					a[cur] += temp;
				if (a[cur] >= 100)
					a[cur] %= 100;
			}
		}
	}

	// 时间复杂度是n^3*logk
//	其实这个函数相似与Pow_50 就是x的exp次方 可以分而治之 时间复杂度为logk  k=exp
	public static int[][] afterK2(int n, int exp) {
		int[][] res = new int[n][n];// 把结果矩阵初始化为单位阵
		int[][] x = new int[n][n]; // 即将计算幂的矩阵
		for (int i = 0; i < n; i++) {
			x[i][i] = 1;
			res[i][i] = 1;
		}
		for (int i = 0; i < n - 1; i++)
			x[i][i + 1] = 1;
		x[n - 1][0] = 1;
		while (exp != 0) {
			if ((exp & 1) == 1)
				res = multiply(res, x);
			x = multiply(x, x);
			exp = exp >> 1;
		}
		return res;

	}

	// 时间复杂度为n*n*n
	public static int[][] multiply(int[][] a, int[][] b) {
		int resRow = a.length;
		int resCol = b[0].length;
		int mid = a[0].length;
		int[][] res = new int[resRow][resCol];
		for (int i = 0; i < resRow; i++)
			for (int j = 0; j < resCol; j++)
				for (int k = 0; k < mid; k++) {
					res[i][j] += a[i][k] * b[k][j];
					res[i][j] = res[i][j] % 100;
				}
		return res;

	}

}
