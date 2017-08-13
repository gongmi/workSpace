package netEase;

import java.util.Scanner;
public class fruit {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x, f, d, p;
		x = in.nextInt();
		f = in.nextInt();
		d = in.nextInt();
		p = in.nextInt();

		if (d < x * f) {
			System.out.println(d / x);
		} else {
			int res = (d - x * f) / (p + x) + f;
			System.out.println(res);
		}
	}
}
