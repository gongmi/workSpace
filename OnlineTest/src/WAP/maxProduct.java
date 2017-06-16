package WAP;

import java.util.*;

public class maxProduct {
	public int maxProduct(String[] words) {
		int n = words.length;
		int[] bit_words = new int[n];
		for (int i = 0; i < n; i++)
			bit_words[i] = strToInt(words[i]);

		int max = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if ((bit_words[i] & bit_words[j]) == 0)
					max = Math.max(max, words[i].length() * words[j].length());

		return max;
	}

	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int n = jin.nextInt();
		String[] words = new String[n];
		for (int i = 0; i < n; i++) {
			words[i] = jin.next();
		}

		System.out.println(new maxProduct().maxProduct(words));
	}

	private int strToInt(String str) {
		int res = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			res = res | 1 << (c - 'a');
		}
		return res;
	}

}
