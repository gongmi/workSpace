package huawei;

import java.util.Arrays;
import java.util.Scanner;

public class magicBracelet {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = 0;
		int k = 0;
		int[] test = null;
		while (in.hasNextInt()) {
			n = in.nextInt();
			k = in.nextInt();
			test = new int[n];
			for (int i = 0; i < n; i++) {
				test[i] = in.nextInt();
			}
			break;
		}
		for (int i = 0; i < k; i++) {
			int temp = test[0];
			for (int cur = 0; cur < n; cur++) {
				if (cur < n - 1)
					test[cur] += test[cur + 1];
				else
					test[cur] += temp;
				if (test[cur] >= 100)
					test[cur] %= 100;

			}
		}
		System.out.println(String.valueOf(test));
		System.out.println(Arrays.toString(test));

	}
}
