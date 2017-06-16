package WAP;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int m;
	static int sol = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		m = in.nextInt();
		List<Integer> a = new LinkedList<>();
		for (int i = 0; i < n; i++)
			a.add(in.nextInt() % m);
		String s = res(n, a);
		System.out.println(s);
	}

	public static void findsol(List<Integer> a, int sum) {
		if (sol > 0)
			return;
		System.out.println(a);
		HashMap<Integer, Boolean> map = new HashMap<>();
		List<Integer> temp;
		int left;
		for (int i = 0; i < a.size(); ++i) {
			left = a.get(i);
			map.put(left, true);
		}
		left = sum % m;
		if (map.get(left) != null) {
			sol++;
			return;
		} else {
			for (int i = 0; i < a.size(); ++i) {
				temp = new LinkedList<>(a);
				if (!pre_exist(i, temp)) {
					temp.remove(i);
					sum = Sum(temp);
					if (sum > m) {
						findsol(temp, sum);
					}
					if (sum == m) {
						sol++;
						break;
					}
				}
			}
		}
	}

	public static String res(int n, List<Integer> a) {
		int sum;
		sum = Sum(a);
		findsol(a, sum);
		if (sol == 0) {
			return "No";
		} else {
			return "Yes";
		}
	}

	public static boolean pre_exist(int i, List<Integer> temp) {
		for (int k = 0; k < i; ++k)
			if (temp.get(i) == temp.get(k))
				return true;
		return false;
	}

	public static int Sum(List<Integer> a) {
		int res = 0;
		for (int i : a)
			res = res + i;
		return res;
	}
}