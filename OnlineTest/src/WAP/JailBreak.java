package WAP;

import java.util.Scanner;

public class JailBreak {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		System.out.println(solve(m, n));
	}

	public static int solve(int m, int n) {
		int res = (int) (pow(m, n, 100003) - (pow(m - 1, n - 1, 100003) * m) % 100003);
		return res < 0 ? res + 100003 : res;
	}

	public static int pow(int a, int b, int m) {
		int result = 1;
		while (b > 0) {
			if ((b & 1) == 1)
				result = (result * a) % m;

			a = (a * a) % m;
			b >>= 1;
		}
		return result;
	}
}
