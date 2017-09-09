package didi;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
	private static int j = 0;

	private static boolean test1(int k) {
		j += k;
		return true;

	}

	private static boolean test2(int i) {
		boolean b;
		b = i < 100 | test1(3);
		b = i < 010 || test1(6);
		return b;
	}

	public static void main(String[] args) {
		test2(0);
		System.out.println(j);
	}
}
