package baidu;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class third {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		HashSet<Integer> set = new HashSet<>();
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int i = 0; i < n; i++)
			set.add(in.nextInt());
		for (int i : set)
			heap.add(i);
		if (heap.size() < 3)
			System.out.println(-1);
		else {
			heap.poll();
			heap.poll();
			System.out.println(heap.peek());
		}
	}
}
