package WAP;

import java.util.*;

public class RotateList {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] Args) {
		Scanner in = new Scanner(System.in);
		int len = in.nextInt();
		int k = in.nextInt();

		ListNode prev = null;
		ListNode head = null;
		for (int i = 0; i < len; i++) {
			ListNode node = new ListNode(in.nextInt());
			if (prev == null)
				head = node;
			else
				prev.next = node;
			prev = node;
		}
		if (head == null)
			return;
		ListNode fast = head;
		ListNode slow = head;
		k = k % len;
		for (int i = len - k; i > 1; i--)
			slow = slow.next;
		while (fast.next != null)
			fast = fast.next;

		fast.next = head;
		head = slow.next;
		slow.next = null;

		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
}