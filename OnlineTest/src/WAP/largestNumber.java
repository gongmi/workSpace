package WAP;

import java.util.Scanner;

public class largestNumber {
	static int[] max = new int[200001];
	static int size = 0;
	static int prev = 0;
	static int mod = 0;

	public static void add(int x) {
		size++;
		int val = (x + prev) % mod;
		for (int i = size - 1; i >= 1; i--)
			if (val > max[i])
				max[i] = val;
			else
				break;
		max[size] = val;
		prev = val;
		for (int i=0;i<10;i++)
		System.out.println(max[i]);
	}

	public static int query(int x) {

		return max[size - x + 1];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		mod = in.nextInt();
		while (m-- > 0) {
			String ch = in.next();
			int x = in.nextInt();

			if (ch.equals("I"))
				add(x);
			else
				System.out.println(query(x));
		}
	}
}
