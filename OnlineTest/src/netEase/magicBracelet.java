package netEase;

import java.util.Arrays;
import java.util.Scanner;
//С��ӵ��һ��ӵ��ħ�����ֻ�������n������(����һ����),
//�����ħ���ֻ�ÿ��ʹ��ħ����ʱ��ͻᷢ��һ�����صı仯��
//ÿ�����ֻ����Լ�������һ�����ֵĺ�(���һ�����ֵĺ���һ�������ǵ�һ��),
//һ��ĳ��λ�õ����ִ��ڵ���100�����϶�100ȡģ(����ĳ��λ�ñ�Ϊ103,�ͻ��Զ���Ϊ3).
//���ڸ������ħ���ֻ��Ĺ��ɣ���������ʹ��k��ħ��֮��ħ���ֻ���״̬�� 

//������A = [1, 2, 3], k = 2��
//���ǿ��Թ���һ�������ľ���x�������е�x����

//[1, 1, 0] 
//[0, 1, 1]
//[1, 0, 1]
// ʹ��B^k*A�൱��Aת��k�κ�����ӡ�
//����ԭ����ͱ�����������ݡ�
//������ȡ���У�x^k % c =  (x % c)^k % c
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

	// ������ⷨ ��ʱ�临�Ӷ�Ϊ k*n  ������������k�ܴ�n��С ��7 192347
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

	// ʱ�临�Ӷ���n^3*logk
//	��ʵ�������������Pow_50 ����x��exp�η� ���Էֶ���֮ ʱ�临�Ӷ�Ϊlogk  k=exp
	public static int[][] afterK2(int n, int exp) {
		int[][] res = new int[n][n];// �ѽ�������ʼ��Ϊ��λ��
		int[][] x = new int[n][n]; // ���������ݵľ���
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

	// ʱ�临�Ӷ�Ϊn*n*n
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
