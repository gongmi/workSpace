package paypal;

import java.util.*;

public class Daffodils {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<String> list = new LinkedList<>();

		while (in.hasNextLine()) {
			String ss = in.nextLine();
			list.add(ss);
		}
		int[] water = new int[] { 1, 153, 370, 371, 407 };
		for (String s : list) {
			String[] arr = s.split(" ");
			int left = Integer.parseInt(arr[0]);
			int right = Integer.parseInt(arr[1]);
			String res = "";
			for (int i : water)
				if (i >= left && i <= right)
					res = res + i + " ";

			if (res.equals(""))
				System.out.println("no");
			else
				System.out.println(res.trim());
		}
	}
}