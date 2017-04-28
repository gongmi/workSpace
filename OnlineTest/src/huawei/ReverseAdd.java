package huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Scanner;

public class ReverseAdd {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		try {
			s = br.readLine();
			String[] nums = s.split(",");
			int sum = 0;
			StringBuilder sb1 = new StringBuilder(nums[0]);
			StringBuilder sb2 = new StringBuilder(nums[1]);
			sum = Integer.parseInt(sb1.reverse().toString()) + Integer.parseInt(sb2.reverse().toString());
			System.out.println(sum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
