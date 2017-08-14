package netEase;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class shulie {
	static int res=0;
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		backtrack( new LinkedList<Integer>(), k, n, 1);
		System.out.println(res++);
	}

	public static void backtrack( LinkedList<Integer> tempList, int k, int n, int start) {
		if (tempList.size() == n) {
			res++;
			return;
		}
		for (int i = 1; i <= k; i++) {
			if (tempList.size()==0||(i >= tempList.getLast() || tempList.getLast() % i != 0)) {
				tempList.add(i);
			} else {
				continue;
			}
			backtrack( tempList, k, n, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
}
