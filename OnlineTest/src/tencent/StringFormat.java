package tencent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StringFormat {
	public static void main(String[] args) {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s;
			s = br.readLine();
			char[] arr = s.toCharArray();
			int len = arr.length;
			int lines = len / 16;
			for (int i = 0; i <= lines; i++)

			{
				System.out.printf("%08x", (int) i*16);
				System.out.print("  ");
				for (char c : s.substring(i*16, (i*16 + 16)>s.length()?s.length():(i*16 + 16)).toCharArray())

				{
					System.out.format("%x", (int) c);
					System.out.print("  ");
				}

				System.out.print(s.substring(i*16, (i*16 + 16)>s.length()?s.length():(i*16 + 16)));
				System.out.println();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}