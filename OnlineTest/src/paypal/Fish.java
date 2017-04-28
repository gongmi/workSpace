package paypal;

import java.util.*;

public class Fish {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		String d = in.nextLine();
		String[] Size = s.split(" ");
		String[] Dire = d.split(" ");
		int len = Size.length;
		int[] size = new int[len];
		int[] dire = new int[len];
		for (int i = 0; i < len; i++) {
			size[i] = Integer.parseInt(Size[i]);
			dire[i] = Integer.parseInt(Dire[i]);
		}
		int count = len;
		for (int i = 0; i < len; i++) {
			if (dire[i] == 1) {
				int wTOe = size[i];
				for (int j = i + 1; j < len; j++)
					if (dire[j] == 1) {
						if (size[j] > wTOe)
							wTOe = size[j];
					} else if (size[j] > wTOe) {
						count--;
						break;
					}
			} else {
				int eTOw = size[i];
				for (int j = i - 1; j >= 0; j--)
					if (dire[j] == -1) {
						if (size[j] > eTOw)
							eTOw = size[j];
					} else if (size[j] > eTOw) {
						count--;
						break;
					}
			}
		}
		System.out.println(count);
	}
}